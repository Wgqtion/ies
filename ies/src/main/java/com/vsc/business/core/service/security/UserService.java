package com.vsc.business.core.service.security;

import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.repository.security.UserDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

@Service
@Transactional
public class UserService extends BaseService<User> {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	@Override
	public PagingAndSortingRepository<User, Long> getPagingAndSortingRepositoryDao() {
		return this.userDao;
	}

	@Override
	public JpaSpecificationExecutor<User> getJpaSpecificationExecutorDao() {
		return this.userDao;
	}

	public User save(User entity, Long photoAttachId) throws Exception {

		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);		
		this.userDao.save(entity);

		return entity;

	}
	
	public void deleteUpdateById(Long id) throws Exception {
		User entity=getObjectById(id);
		entity.setIsDelete(true);
		save(entity);
	}

	public void deleteUpdateByIds(Long[] ids) throws Exception {
		if (ArrayUtils.isNotEmpty(ids)) {
			for (int i = 0; i < ids.length; i++) {
				deleteUpdateById(ids[i]);
			}
		}
	}
	
}
