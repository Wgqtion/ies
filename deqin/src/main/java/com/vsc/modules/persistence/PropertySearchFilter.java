package com.vsc.modules.persistence;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PropertySearchFilter {
	/**
	 * 多个属性间OR关系的分隔符.
	 */
	private static final String OR_SEPARATOR = "_OR_";

	private static final Character CHILD_SEPARATOR = ':';

	private static final Character CHILD_PROPERTY = '.';

	public enum Operator {
		//等于
		EQ,
		//不等于
		NOTEQ,
		//左右模糊
		LIKE,
		//前缀模糊
		LLIKE,
		//后缀模糊
		RLIKE,
		//大于
		GT,
		//小于
		LT,
		//大于等于 
		GTE,
		//小于等于
		LTE,
		//等于空
		ISNULL,
		//不等于空
		NOTNULL,
		//IN查询条件只能使用String,且必须用','隔开各个目标值 
		IN;
	}

	public String fieldName;
	public Object value;
	public Operator operator = Operator.EQ;
	private List<PropertySearchFilter> orDisjunctionPropertyFilter = Lists.newArrayList();

	public PropertySearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public PropertySearchFilter(String fieldExpression, Object value) {
		String operatorTypeStr = StringUtils.substringBefore(fieldExpression, "_");
		Operator tempOperator = Operator.valueOf(operatorTypeStr);
		String propertyNameStr = StringUtils.substringAfter(fieldExpression, "_");

		String[] propertyNamesStr = StringUtils.splitByWholeSeparator(propertyNameStr,
				PropertySearchFilter.OR_SEPARATOR);
		Assert.isTrue(propertyNamesStr.length > 0, "filter名称" + fieldExpression + "没有按规则编写,无法得到属性名称.");

		for (int i = 0; i < propertyNamesStr.length; i++) {
			//有时候收取[.]做分割不方便,多扩展一个[:]做分割
			propertyNamesStr[i] = propertyNamesStr[i].replace(PropertySearchFilter.CHILD_SEPARATOR,
					PropertySearchFilter.CHILD_PROPERTY);
			//第一个参数归自己，其它参数属于子条件
			if (i == 0) {
				this.value = value;
				this.operator = tempOperator;
				this.fieldName = propertyNamesStr[i];
			} else {
				if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(propertyNamesStr[i],"_")) {
					orDisjunctionPropertyFilter.add(new PropertySearchFilter(propertyNamesStr[i], tempOperator, value));
				} else {
					orDisjunctionPropertyFilter.add(new PropertySearchFilter(propertyNamesStr[i], value));
				}

			}

		}

	}

	public static Map<String, PropertySearchFilter> parse(Map<String, Object> filterParams) {
		Map<String, PropertySearchFilter> filters = Maps.newHashMap();
		for (Entry<String, Object> entry : filterParams.entrySet()) {
			PropertySearchFilter filter = new PropertySearchFilter(entry.getKey(), entry.getValue());
			filters.put(entry.getKey(), filter);
		}

		return filters;
	}

	/**
	 * 获取与当前PropertyFilter同时生效的OR查询条件
	 * @return 获取OR查询条件列表
	 */
	public List<PropertySearchFilter> getOrDisjunctionPropertyFilter() {
		return orDisjunctionPropertyFilter;
	}

	/**
	 * 是否有多个属性.
	 */
	public boolean isMultiProperty() {
		return Collections3.isNotEmpty(orDisjunctionPropertyFilter);
	}

}
