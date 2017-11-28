package com.vsc.business.core.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.repository.security.AuthorityDao;
import com.vsc.business.core.repository.security.RoleDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class RoleService extends BaseService<Role> {
	private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	private RoleDao roleDao;
	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public PagingAndSortingRepository<Role, Long> getPagingAndSortingRepositoryDao() {
		return this.roleDao;
	}

	@Override
	public JpaSpecificationExecutor<Role> getJpaSpecificationExecutorDao() {
		return this.roleDao;
	}

	public void save(Role role, Long[] authorityIds) {
		role.setAuthorityList(this.findIds(authorityIds, authorityDao));
		this.roleDao.save(role);
	}

}
