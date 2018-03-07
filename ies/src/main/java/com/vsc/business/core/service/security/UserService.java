package com.vsc.business.core.service.security;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.repository.security.RoleDao;
import com.vsc.business.core.repository.security.UserDao;
import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class UserService extends BaseService<User> {
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public PagingAndSortingRepository<User, Long> getPagingAndSortingRepositoryDao() {
		return this.userDao;
	}

	@Override
	public JpaSpecificationExecutor<User> getJpaSpecificationExecutorDao() {
		return this.userDao;
	}

	public User save(User entity, Long photoAttachId, Long roleId) {

		entity.getRoleList().clear();
		entity.getRoleList().add(roleDao.findOne(roleId));


		this.userDao.save(entity);

		return entity;

	}

	public User save(User entity, Long adminUserId, Long photoAttachId, Long roleId, Long[] hospitalIds) {

		entity.getRoleList().clear();
		entity.getRoleList().add(roleDao.findOne(roleId));

		return this.userDao.save(entity);

	}

	public List<User> findAllAdminUser() {
		Map<String, Object> filterParams = Maps.newHashMap();
		filterParams.put("EQ_userType", 2);
		return this.findList(filterParams);
	}
	
	
}
