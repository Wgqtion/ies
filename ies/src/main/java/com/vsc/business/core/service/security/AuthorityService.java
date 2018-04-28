package com.vsc.business.core.service.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.Authority;
import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.repository.security.AuthorityDao;
import com.vsc.modules.service.BaseService;

/**
 * 数据权限逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class AuthorityService extends BaseService<Authority> {
	private static Logger logger = LoggerFactory.getLogger(AuthorityService.class);

	private AuthorityDao authorityDao;
	

	@Autowired
	private RoleService roleService;
	
	@Autowired
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
	
	@Override
	public PagingAndSortingRepository<Authority, Long> getPagingAndSortingRepositoryDao() {
		return this.authorityDao;
	}

	@Override
	public JpaSpecificationExecutor<Authority> getJpaSpecificationExecutorDao() {
		return this.authorityDao;
	}
	
	/**
	 * 根据用户id，类型查询菜单资源
	 * parentCode，sort排序
	 * @param userId 用户id
	 * @param resourceType 类型
	 * @return 菜单资源
	 */
	public List<Authority> getMenus(Long userId,Integer resourceType){
		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("EQ_roleList.userList.id", userId);
		filterParams.put("EQ_roleList.userList.isDelete", 0);
		filterParams.put("EQ_resourceType", resourceType);
		return findAll(filterParams,"parentCode,sort","ASC,ASC");
	}
	
	/**
	 * 保存角色的权限集合
	 * @param roleId
	 * @param codes
	 * @throws Exception 
	 */
	public void save(Long roleId,String codes) throws Exception{
		Role role=roleService.getObjectById(roleId);
		String[] Acodes=codes.split(",");
		List<Authority> authorityList=new ArrayList<Authority>();
		for(String code:Acodes){
			authorityList.add(this.findUniqueBy("code", code));
		}
		role.setAuthorityList(authorityList);
		this.roleService.save(role);
	}
}
