package com.vsc.business.core.service.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import com.vsc.business.core.entity.security.Resource;
import com.vsc.business.core.repository.security.ResourceDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class ResourceService extends BaseService<Resource> {
	private static Logger logger = LoggerFactory.getLogger(ResourceService.class);

	private ResourceDao resourceDao;
	
	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	@Override
	public PagingAndSortingRepository<Resource, Long> getPagingAndSortingRepositoryDao() {
		return this.resourceDao;
	}

	@Override
	public JpaSpecificationExecutor<Resource> getJpaSpecificationExecutorDao() {
		return this.resourceDao;
	}
	
	/**
	 * 刷新系统中所有配置好的RequestMapping
	 * 如果配置的RequestMapping地址已经存在，更新数据库记录，不存在就创建新记录.
	 * 不会删除urls中不存在的记录
	 * @param urls 所有url资源
	 */
	public void resetRequestMappingResource(Collection<String> urls){
		if(Collections3.isNotEmpty(urls)){
			//this.resourceDao.deleteAll();
			//this.resourceDao.flush();
			double n=0;
			for (String value : urls) {
				Resource entity=this.findUniqueBy("value", value);
				if(entity==null){
					entity=new Resource();
				}
				entity.setPosition(Double.valueOf(++n));
				entity.setResourceType("url");
				entity.setValue(value);	
				
				this.resourceDao.save(entity);
			} 
		} 
	}

}
