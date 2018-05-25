package com.vsc.business.core.service.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.repository.security.RoleDao;
import com.vsc.business.gerd.entity.work.Company;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;

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
	 * 根据条件查询，不包含admin
	 * @throws Exception 
	 */
	@Override
	public Page<Role> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("EQ_createUser.id",user.getId());
		filterParams.put("NOTEQ_code","admin");
		return super.findPage(filterParams, pageRequest);
	}
	/**
	 * 根据条件查询，不包含admin
	 * @throws Exception 
	 */
	public List<Role> findList(Map<String, Object> filterParams) throws Exception{
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("EQ_createUser.id",user.getId());
		filterParams.put("NOTEQ_code","admin");
		return super.findList(filterParams);
	}
	
	@Override
	public Role save(Role entity) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		if(entity.getId()==null){
			entity.setCreateUser(user);	
		}
		return super.save(entity);
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
