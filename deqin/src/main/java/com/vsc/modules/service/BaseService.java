package com.vsc.modules.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Reflections;

import com.google.common.collect.Lists;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.ibatis.IbatisQueryDao;
import com.vsc.modules.persistence.DynamicSpecifications;
import com.vsc.modules.persistence.PropertySearchFilter;
import com.vsc.modules.persistence.PropertySearchFilter.Operator;
import com.vsc.modules.repository.BaseDao;
import com.vsc.util.CoreUtils;

/**
 * 基础业务类，实现基于摸个实体类的基本数据操作
 * @author 付翀 
 * @param <T> 默认操作的实体类型
 */
public abstract class BaseService<T> {
	@Autowired
	protected IbatisQueryDao ibatisQueryDao;
	@Autowired
	private ResourceBundleMessageSource _rms;

	/**
	 * 根据 ID值，获取实体对象
	 * @param id
	 * @return
	 */
	public T getObjectById(Long id) {
		return this.getPagingAndSortingRepositoryDao().findOne(id);
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue))
			return true;
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Class<T> clazz = Reflections.getClassGenricType(this.getClass());
		PropertySearchFilter filter = new PropertySearchFilter(propertyName, Operator.EQ, value);
		List<PropertySearchFilter> filters = Lists.newArrayList();
		filters.add(filter);
		return (T) this.getJpaSpecificationExecutorDao().findOne(
				DynamicSpecifications.byPropertySearchFilter(filters, clazz));
	}

	public Iterable<T> findAllByIds(Iterable<Long> ids) {
		return this.getPagingAndSortingRepositoryDao().findAll(ids);
	}

	public boolean exists(Long id) {
		return this.getPagingAndSortingRepositoryDao().exists(id);
	}

	public T save(T entity) {
		return this.getPagingAndSortingRepositoryDao().save(entity);
	}

	public Iterable<T> save(Iterable<T> entitys) {
		return this.getPagingAndSortingRepositoryDao().save(entitys);
	}

	public void deleteById(Long id) {
		this.getPagingAndSortingRepositoryDao().delete(id);
	}

	public void deleteByIds(Long[] ids) {
		if (ArrayUtils.isNotEmpty(ids)) {
			for (int i = 0; i < ids.length; i++) {
				this.getPagingAndSortingRepositoryDao().delete(ids[i]);
			}
		}
	}

	public void deleteByIds(List<Long> ids) {
		if (Collections3.isNotEmpty(ids)) {
			for (Long id : ids) {
				if (id != null) {
					this.getPagingAndSortingRepositoryDao().delete(id);
				}
			}
		}
	}

	/**
	 * 获取某个表的所有数据
	 * @return
	 */
	public List<T> getAllList() {
		return (List<T>) this.getPagingAndSortingRepositoryDao().findAll();
	}

	/**
	 * 获取摸个查询条件的所有数据
	 * @param filterParams 查询条件
	 * @return
	 */
	public List<T> findList(Map<String, Object> filterParams) {
		return this.findAll(filterParams, null);
	}

	/**
	 * 获取某个查询条件的所有数据，并按要求排序
	 * @param filterParams 查询条件
	 * @param sort  排序规则
	 * @return
	 */
	public List<T> findAll(Map<String, Object> filterParams, Sort sort) {
		Specification<T> spec = buildSpecification(filterParams);
		return this.getJpaSpecificationExecutorDao().findAll(spec, sort);
	}

	public List<T> findAll(Map<String, Object> filterParams, String[] sortOrderBy, String[] sortOrderDesc) {
		return this.findAll(filterParams, CoreUtils.buildSort(sortOrderBy, sortOrderDesc));
	}

	public List<T> findAll(Map<String, Object> filterParams, String sortOrderBy, String sortOrderDesc) {
		return this.findAll(filterParams, CoreUtils.buildSort(sortOrderBy, sortOrderDesc));
	}

	/**
	 * 获取某个查询条件的 分页查询
	 * @param filterParams 查询条件
	 * @param pageRequest  分页参数(包括排序)
	 * @return
	 */
	public Page<T> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		Specification<T> spec = buildSpecification(filterParams);
		return this.getJpaSpecificationExecutorDao().findAll(spec, pageRequest);
	}

	public Page<T> findPageByPropertySearchFilter(Map<String, PropertySearchFilter> filterParams,
			PageRequest pageRequest) {
		Specification<T> spec = buildSpecificationByPropertySearchFilter(filterParams);
		return this.getJpaSpecificationExecutorDao().findAll(spec, pageRequest);
	}

	public abstract PagingAndSortingRepository<T, Long> getPagingAndSortingRepositoryDao();

	public abstract JpaSpecificationExecutor<T> getJpaSpecificationExecutorDao();

	/**
	 * 创建动态查询条件组合.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Specification<T> buildSpecification(Map<String, Object> filterParams, final Class clazz) {
		Map<String, PropertySearchFilter> filters = PropertySearchFilter.parse(filterParams);

		return buildSpecificationByPropertySearchFilter(filters, clazz);
	}

	protected Specification<T> buildSpecification(Map<String, Object> filterParams) {
		Class<T> clazz = Reflections.getClassGenricType(this.getClass());
		return this.buildSpecification(filterParams, clazz);

	}

	protected Specification<T> buildSpecificationByPropertySearchFilter(Map<String, PropertySearchFilter> filters,
			final Class clazz) {
		Specification<T> spec = DynamicSpecifications.byPropertySearchFilter(filters.values(), clazz);
		return spec;
	}

	protected Specification<T> buildSpecificationByPropertySearchFilter(Map<String, PropertySearchFilter> filters) {
		Class<T> clazz = Reflections.getClassGenricType(this.getClass());
		return buildSpecificationByPropertySearchFilter(filters, clazz);
	}

	protected <S> List<S> findIds(Object[] idsObj, BaseDao<? extends S> dao) {
		List<S> list = Lists.newArrayList();
		Long[] ids = null;
		if (ArrayUtils.isNotEmpty(idsObj)) {
			ids = new Long[idsObj.length];
			if (idsObj instanceof Long[]) {
				ids = (Long[]) idsObj;
			} else {
				String[] values = (String[]) idsObj;
				for (int i = 0; i < values.length; i++) {
					ids[i] = Long.parseLong(values[i]);
				}
			}
			List<Long> idList = Lists.newArrayList(ArrayUtils.removeElement(ids, null));
			list = Lists.newArrayList(dao.findAll(idList));
		}
		return list;
	}

	public List<MapBean<String, Object>> findIbatisQuery(String sqlId, List<PropertySearchFilter> filters) {
		return ibatisQueryDao.findAll(sqlId, filters);
	}

	public List<MapBean<String, Object>> findIbatisQuery(String sqlId, Map<String, Object> filters) {
		return ibatisQueryDao.findAll(sqlId, filters);
	}

	public List<MapBean<String, Object>> findAll(String sqlId, List<PropertySearchFilter> filters, Sort order) {
		return ibatisQueryDao.findAll(sqlId, filters, order);
	}

	public String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}

	public String getMessage(String code, Object arg0) {
		return getMessage(code, new Object[] { arg0 }, null);
	}

	public String getMessage(String code, Object arg0, Object arg1) {
		return getMessage(code, new Object[] { arg0, arg1 }, null);
	}

	public String getMessage(String code, Object arg0, Object arg1, Object arg2) {
		return getMessage(code, new Object[] { arg0, arg1, arg2 }, null);
	}

	public String getMessage(String code, Object arg0, Object arg1, Object arg2, Object arg3) {
		return getMessage(code, new Object[] { arg0, arg1, arg2, arg3 }, null);
	}

	public String getMessage(String code, Object[] args, Locale locale) {
		return _rms.getMessage(code, args, null);
	}
}
