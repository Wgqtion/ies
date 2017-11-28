package com.vsc.modules.shiro;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.ibatis.IbatisQueryDao;

public class DBDefinitionsService {
	private static Logger logger = LoggerFactory.getLogger(DBDefinitionsService.class);
	private String filterName = "permsany";
	private IbatisQueryDao ibatisQueryDao;
	private String urls;
	private String prefix = "";
	private String sqlId="listshiro.resource_authority";

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public IbatisQueryDao getIbatisQueryDao() {
		return ibatisQueryDao;
	}

	public void setIbatisQueryDao(IbatisQueryDao ibatisQueryDao) {
		this.ibatisQueryDao = ibatisQueryDao;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDefinitions() {
		StringBuffer strb = new StringBuffer();

		List<MapBean<String,Object>> resourceAuthorityList = ibatisQueryDao.findAll(sqlId);
		String url;
		String permission; 
		Map<String, Set<String>> map = Maps.newLinkedHashMap();
		for (MapBean<String,Object> resourceAuthority : resourceAuthorityList) {
			url = (String) resourceAuthority.get("url");
			permission = (String) resourceAuthority.get("permission");
			if (map.containsKey(url)) {
				map.get(url).add(permission);
			} else {
				Set<String> s = Sets.newHashSet(permission);
				map.put(url, s);
			}
		}
		for (Iterator<Map.Entry<String, Set<String>>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, Set<String>> entry = iterator.next();
			strb.append(prefix).append(entry.getKey()).append("=").append(filterName).append("[\"")
					.append(StringUtils.join(entry.getValue(), ",")).append("\"]\n");
		}

		if (StringUtils.isNotEmpty(urls)) {
			strb.append(urls);
		}
		logger.debug("安全框架URL权限配置:" + strb.toString());
		return strb.toString();
	}
}
