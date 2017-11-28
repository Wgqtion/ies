package com.vsc.modules.persistence;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;
import com.vsc.modules.entity.IdEntity;
import com.vsc.modules.persistence.PropertySearchFilter.Operator;
import com.vsc.modules.spring.mvc.convert.StringToDateTimeConverter;

public class DynamicSpecifications {
	/**
	 * user.roles.name
	 */
	public static String FIELD_NAME_SEPARATOR=".";
	
	private static final DefaultConversionService conversionService = new DefaultConversionService();
	static {
		conversionService.addConverter(String.class, Date.class, new StringToDateTimeConverter());
	}

	public static <T> Specification<T> byPropertySearchFilter(final Collection<PropertySearchFilter> filters,
			final Class<T> clazz) {
		return new Specification<T>() {

			private Predicate[] createPredicatesByPropertySearchFilter(final PropertySearchFilter filter,
					CriteriaBuilder builder, Root<T> root) {
				List<Predicate> predicates = Lists.newArrayList();
				predicates.add(this.createPredicateByPropertySearchFilter(filter, builder, root));
				for (PropertySearchFilter propertySearchFilter : filter.getOrDisjunctionPropertyFilter()) {
					predicates.add(this.createPredicateByPropertySearchFilter(propertySearchFilter, builder, root));
				}
				Predicate[] array = new Predicate[predicates.size()];

				return predicates.toArray(array);
			}

			/**
			 * 根据节点路径，创建关联查询
			 * @param from 当前父join
			 * @param fieldName 相对字段名称
			 * @return 叶子节点的path
			 */
			private Path joinFieldNameToPath(From from,String fieldName){
				if(StringUtils.indexOf(fieldName, ".")==StringUtils.INDEX_NOT_FOUND){
					return from.get(fieldName);
				}else{
					String name=StringUtils.substringBefore(fieldName, FIELD_NAME_SEPARATOR); 
					for (Iterator iterator = from.getJoins().iterator(); iterator.hasNext();) {
						Join join = (Join) iterator.next();
						if(join.getAttribute().getName().equals(name)){
							return joinFieldNameToPath(join,StringUtils.substringAfter(fieldName, FIELD_NAME_SEPARATOR));
						}
					}
					
					Join join =from.join(name);
					return joinFieldNameToPath(join,StringUtils.substringAfter(fieldName, FIELD_NAME_SEPARATOR)); 
				}
			}
			
			private Predicate createPredicateByPropertySearchFilter(final PropertySearchFilter filter,
					CriteriaBuilder builder, Root<T> root) {
				// nested path translate 
				Predicate predicate = null;
				//String[] names = StringUtils.split(filter.fieldName, "."); 
				//Path expression = root.get(names[0]);
				 
				Path expression=joinFieldNameToPath(root,filter.fieldName); 
				Class attributeClass = expression.getJavaType();
				//类型转换，IN查询的时候在后面转换
				if (!Operator.IN.equals(filter.operator)) {

					if (!attributeClass.equals(String.class) && filter.value instanceof String
							&& conversionService.canConvert(String.class, attributeClass)) {
						filter.value = conversionService.convert(filter.value, attributeClass);
					}else if(IdEntity.class.isAssignableFrom(attributeClass)){
						filter.value = conversionService.convert(filter.value, Long.class);
					}
					
					
				}
				switch (filter.operator) {
				case EQ:
					predicate = builder.equal(expression, filter.value);
					break;
				case NOTEQ:
					predicate = builder.notEqual(expression, filter.value);
					break;
				case LIKE:
					predicate = builder.like(expression, "%" + filter.value + "%");
					break;
				case LLIKE:
					predicate = builder.like(expression, "%" + filter.value + "");
					break;
				case RLIKE:
					predicate = builder.like(expression, "" + filter.value + "%");
					break;
				case GT:
					predicate = builder.greaterThan(expression, (Comparable) filter.value);
					break;
				case LT:
					predicate = builder.lessThan(expression, (Comparable) filter.value);
					break;
				case GTE:
					predicate = builder.greaterThanOrEqualTo(expression, (Comparable) filter.value);
					break;
				case LTE:
					predicate = builder.lessThanOrEqualTo(expression, (Comparable) filter.value);
					break;
				case ISNULL:
					predicate = builder.isNull(expression);
					break;
				case NOTNULL:
					predicate = builder.isNotNull(expression);
					break;
				case IN:
					//IN查询只能是String类型的值
					if (filter.value != null) {
						Object values[] = filter.value.toString().split(",");
						Object objs[] = new Object[values.length];
						for (int i = 0; i < values.length; i++) {
							if(IdEntity.class.isAssignableFrom(attributeClass)){
								objs[i]= conversionService.convert(values[i],Long.class);
							}else{
								objs[i] = conversionService.convert(values[i], attributeClass);
							}
						}
						predicate = ((CriteriaBuilderImpl) builder).in(expression, Arrays.asList(objs));
					}

					break;
				}

				return predicate;
			}

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) { 
					List<Predicate> predicates = Lists.newArrayList();
					for (PropertySearchFilter filter : filters) {
						if (filter.isMultiProperty()) {
							predicates.add(builder.or(this
									.createPredicatesByPropertySearchFilter(filter, builder, root)));

						} else {
							predicates.add(this.createPredicateByPropertySearchFilter(filter, builder, root));
						}

					}

					if (predicates.size() > 0) {
						//如果包含额外链接的表，那么需要distinct保证不重复数据
						if(!root.getJoins().isEmpty()){
							query.distinct(true); 
						} 
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
}
