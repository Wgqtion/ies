package com.vsc.business.gerd.service.work;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.ibatis.IbatisQueryDao;

/**
 * 
 * @author jerry
 *
 */
@Service
@Transactional
public class JinXiaoZhengService {
	private static Logger logger = LoggerFactory.getLogger(JinXiaoZhengService.class);

	private JdbcTemplate jxzJdbcTemplate;
	private IbatisQueryDao ibatisQueryDao;
	private Map<String, String> sqlmap = Maps.newHashMap();

	public JdbcTemplate getJxzJdbcTemplate() {
		return jxzJdbcTemplate;
	}

	public void setJxzJdbcTemplate(JdbcTemplate jxzJdbcTemplate) {
		this.jxzJdbcTemplate = jxzJdbcTemplate;
	}

	public IbatisQueryDao getIbatisQueryDao() {
		return ibatisQueryDao;
	}

	public void setIbatisQueryDao(IbatisQueryDao ibatisQueryDao) {
		this.ibatisQueryDao = ibatisQueryDao;
	}

	public Map<String, String> getSqlmap() {
		return sqlmap;
	}

	public void setSqlmap(Map<String, String> sqlmap) {
		this.sqlmap = sqlmap;
	}

	public void synchronizationCampus() {

		List<MapBean<String, Object>> vl = this.ibatisQueryDao.findAll("c_campus.lasttime.max");
		List<Map<String, Object>> list = Lists.newArrayList();
		if (vl.isEmpty()) {
			return;
		}
		Integer lasttime = MapUtils.getInteger(vl.get(0), "lasttime");

		if (lasttime == null) {
			list = this.jxzJdbcTemplate.queryForList(this.sqlmap.get("find_all_campus"));
		} else {
			list = this.jxzJdbcTemplate.queryForList(this.sqlmap.get("find_campus_by_lasttime"), lasttime);
		}

		for (Map<String, Object> map : list) {
			this.ibatisQueryDao.insert("c_campus.insert", map);
		}
	}
	
	public void synchronizationCardType() {
	  this.ibatisQueryDao.deleteBySqlId("c_card_type.all.delete",null); 
	  List<Map<String, Object>> list = this.jxzJdbcTemplate.queryForList(this.sqlmap.get("find_all_c_card_type")); 
		for (Map<String, Object> map : list) {
			this.ibatisQueryDao.insert("c_card_type.insert", map);
		}
	}
	
	/**
	 * 同步收费规则
	 */
	public void synchronizationShoufei() {
		 this.ibatisQueryDao.deleteBySqlId("c_shoufei.all.delete",null); 
		  List<Map<String, Object>> list = this.jxzJdbcTemplate.queryForList(this.sqlmap.get("find_all_shoufei")); 
			for (Map<String, Object> map : list) {
				this.ibatisQueryDao.insert("c_shoufei.insert", map);
			}
	}
	
	/**
	 *
	 */
	public void synchronizationCardInfo() {
		 this.ibatisQueryDao.deleteBySqlId("c_card_info.all.delete",null); 
		  List<Map<String, Object>> list = this.jxzJdbcTemplate.queryForList(this.sqlmap.get("find_all_card_info")); 
			for (Map<String, Object> map : list) {
				this.ibatisQueryDao.insert("c_card_info.insert", map);
			}
	}
	/**
	 * 
	 */
	public void synchronizationCardTemp() {
		 this.ibatisQueryDao.deleteBySqlId("c_card_temp.all.delete",null); 
		  List<Map<String, Object>> list = this.jxzJdbcTemplate.queryForList(this.sqlmap.get("find_all_card_temp")); 
			for (Map<String, Object> map : list) {
				this.ibatisQueryDao.insert("c_card_temp.insert", map);
			}
	}
}
