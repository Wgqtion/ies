package com.vsc.business.core.service.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.repository.security.RoleDao;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class RoleService extends BaseService<Role> {

	private RoleDao roleDao;

	@Autowired
	private UserService userService;
	
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
	
	/**
	 * 保存用户的角色集合
	 */
	public void save(Long userId,String codes) throws Exception{
		User user=userService.getObjectById(userId);
		String[] Rcodes=codes.split(",");
		List<Role> roleList=new ArrayList<Role>();
		for(String code:Rcodes){
			roleList.add(this.findUniqueBy("code", code));
		}
		user.setRoleList(roleList);
		this.userService.save(user);
	}
}
