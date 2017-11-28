package com.vsc.business.core.service.security;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.security.Authority;
import com.vsc.business.core.entity.security.Resource;
import com.vsc.business.core.repository.security.AuthorityDao;
import com.vsc.business.core.repository.security.ResourceDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class AuthorityService extends BaseService<Authority> {
	private static Logger logger = LoggerFactory.getLogger(AuthorityService.class);
	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public PagingAndSortingRepository<Authority, Long> getPagingAndSortingRepositoryDao() {
		return this.authorityDao;
	}

	@Override
	public JpaSpecificationExecutor<Authority> getJpaSpecificationExecutorDao() {
		return this.authorityDao;
	}

	public void save(Authority authority, Long[] resourceIds) { 
		authority.setResourceList(this.findIds(resourceIds, resourceDao));
		this.authorityDao.save(authority);
	}

}
