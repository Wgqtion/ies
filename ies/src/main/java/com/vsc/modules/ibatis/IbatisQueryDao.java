package com.vsc.modules.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.entity.ReportView;
import com.vsc.modules.persistence.PropertySearchFilter;

/**
 * 基于Ibatis2 的查询专用DAO. 
 * 虽然提供 insert update delete 方法 ，再老系统无实体类情况下可以使用，但是不推荐使用。  
 * @author 付翀
 * 
 */
public class IbatisQueryDao extends SqlMapClientDaoSupport {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public void setSqlMapClientSuper(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public int deleteBySqlId(String sqlId, Object value) {
		return getSqlMapClientTemplate().delete(sqlId, value);
	}

	public Object insert(String sqlId, Object value) {
		return getSqlMapClientTemplate().insert(sqlId, value);
	}

	public int update(String sqlId, Object value) {
		return getSqlMapClientTemplate().update(sqlId, value);
	}

	public String getCountStatementForPaging(String statementName) {
		return statementName + ".count";
	}

	public String getIbatisSqlMapNamespace() {
		throw new RuntimeException("not yet implement");
	}

	/**
	 * 分页查询， 根据 给定条件 获取页面查询结果
	 * @param statementName ibatis完整ID(SQLID)
	 * @param pageRequest 分页条件
	 * @param filters 查询条件
	 * @return 查询结果页
	 */
	@SuppressWarnings("unchecked")
	public <T, K> Page<MapBean<T, K>> pageQuery(String statementName, PageRequest pageRequest,
			List<PropertySearchFilter> filters) {
		return pageQuery(getSqlMapClientTemplate(), statementName, getCountStatementForPaging(statementName),
				pageRequest, filters);
	}

	/**
	 * 分页查询， 根据 给定条件 获取页面查询结果
	 * @param sqlMapClientTemplate ibatisClientTemplate
	 * @param statementName ibatis完整ID(SQLID)
	 * @param countStatementName 分页count() sql id
	 * @param pageRequest  分页条件
	 * @param filters 查询条件
	 * @return 查询结果页
	 */
	@SuppressWarnings("unchecked")
	public <T, K> Page<MapBean<T, K>> pageQuery(SqlMapClientTemplate sqlMapClientTemplate, String statementName,
			String countStatementName, PageRequest pageRequest, List<PropertySearchFilter> filters) {
		Assert.notNull(pageRequest, "page不能为空");
		Map<String, Object> otherFilters = new HashMap<String, Object>();

		//查询条件转化为MAP
		if (filters != null && !filters.isEmpty()) {
			for (PropertySearchFilter propertyFilter : filters) {
				otherFilters.put(propertyFilter.fieldName, propertyFilter.value);
			}
		}

		Long totalCount = (Long) sqlMapClientTemplate.queryForObject(countStatementName, otherFilters);
		if (totalCount == null || totalCount.longValue() <= 0) {
			PageImpl page = new PageImpl(Lists.newArrayList());
			return page;
		}

		//其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
		otherFilters.put("offset", pageRequest.getOffset());
		otherFilters.put("pageSize", pageRequest.getPageSize());
		otherFilters.put("lastRows", pageRequest.getOffset() + pageRequest.getPageSize());
		otherFilters.put("sortColumns", getOrderBy(pageRequest.getSort()));

		List list = sqlMapClientTemplate.queryForList(statementName, otherFilters, pageRequest.getOffset() - 1,
				pageRequest.getPageSize());

		return new PageImpl(list, pageRequest, totalCount);
	}

	/**
	 * 为防止sql注入 ，不允许直接使用
	 * @param sqlId
	 * @param query
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T, K> List<MapBean<T, K>> findAll(String sqlId, Map<String, Object> query, String order) {
		if (StringUtils.isNotEmpty(order)) {
			if (query == null) {
				query = new HashMap<String, Object>();
			}
			query.put("sortColumns", order);
		}
		return this.getSqlMapClientTemplate().queryForList(sqlId, query);
	}
	
	public <T, K> List<MapBean<T, K>> findAll(String sqlId){
		Map<String, Object> filters=Maps.newHashMap();
		return this.findAll(sqlId, filters);
	}
	
	public <T, K> List<MapBean<T, K>> findAll(String sqlId, Map<String, Object> query) {
		 
		return this.findAll(sqlId, query,null);
	}
	
	public <T, K> List<MapBean<T, K>> findAll(String sqlId,ReportView reportView) {
		return this.getSqlMapClientTemplate().queryForList(sqlId,reportView);
	}

	/**
	 * 防止SQL注入，不允许直接使用，请使用Sort排序
	 * @param sqlId
	 * @param filters
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T, K> List<MapBean<T, K>> findAll(String sqlId, List<PropertySearchFilter> filters, String order) {
		Map<String, Object> otherFilters = new HashMap<String, Object>();
		//查询条件转化为MAP
		if (filters != null && !filters.isEmpty()) {
			for (PropertySearchFilter propertyFilter : filters) {
				otherFilters.put(propertyFilter.fieldName, propertyFilter.value);
			}
		}
		return findAll(sqlId, otherFilters, order);
	}

	@SuppressWarnings("unchecked")
	public <T, K> List<MapBean<T, K>> findAll(String sqlId, List<PropertySearchFilter> filters, Sort order) {
		return findAll(sqlId, filters, getOrderBy(order));
	}

	@SuppressWarnings("unchecked")
	public <T, K> List<MapBean<T, K>> findAll(String sqlId, List<PropertySearchFilter> filters) {
		return findAll(sqlId, filters, "");
	}

	private String getOrderBy(Sort sort) {
		List<String> orderString = Lists.newArrayList();
		if (sort != null) {
			for (Order order : sort) {
				orderString.add(order.getProperty() + " " + order.getDirection());
			}
		}
		return StringUtils.join(orderString, ",");
	}

	public void flush() {
		//ignore
	}
}
