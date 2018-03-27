package com.vsc.business.core.service.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.repository.security.RoleDao;
import com.vsc.business.gerd.entity.work.Company;
import com.vsc.business.gerd.service.work.CompanyService;
import com.vsc.modules.service.BaseService;

@Service
@Transactional
public class RoleService extends BaseService<Role> {
	private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	private RoleDao roleDao;

	@Autowired
	private CompanyService companyService;
	
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
	 * 保存公司的角色集合
	 * @param companyId
	 * @param codes
	 */
	public void save(Long companyId,String codes){
		Company company=companyService.getObjectById(companyId);
		String[] Rcodes=codes.split(",");
		List<Role> roleList=new ArrayList<Role>();
		for(String code:Rcodes){
			roleList.add(this.findUniqueBy("code", code));
		}
		company.setRoleList(roleList);
		this.companyService.save(company);
	}
}
