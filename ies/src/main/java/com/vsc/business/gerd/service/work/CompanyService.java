package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.UserService;
import com.vsc.business.gerd.entity.work.Company;
import com.vsc.business.gerd.repository.work.CompanyDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;

/**
 * 公司逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class CompanyService extends BaseService<Company> {
	private static Logger logger = LoggerFactory
			.getLogger(CompanyService.class);

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	protected UserService userService;
	
	@Override
	public PagingAndSortingRepository<Company, Long> getPagingAndSortingRepositoryDao() {
		return this.companyDao;
	}

	@Override
	public JpaSpecificationExecutor<Company> getJpaSpecificationExecutorDao() {
		return this.companyDao;
	}
	/**
	 * 未删除的
	 * @return
	 */
	public List<Company> getList(){
		Map<String, Object> filterParams = new HashMap<String, Object>();
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_code", user.getCompany().getCode());
		filterParams.put("EQ_isDelete",0);
		return findList(filterParams);
	}
	
	/**
	 * 根据code查询，未删除的
	 * @param code
	 * @return
	 */
	public Company getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("EQ_code",code);
		return this.find(searchParams);
	}
	
	@Override
	public Company save(Company entity) {
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);	
			String code=null;
			boolean flag=true;
			int i=0;
			while(flag){
				code="0"+CodeUtils.GenerateCode(this.getMaxCode()+i,3);
				Company c=getByCode(code);
				if(c==null){
					flag=false;
				}
				i++;
			}
			entity.setCode(code);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);
		return super.save(entity);
	}

	/**
	 * 查询当前最大编码
	 * @return
	 */
	public int getMaxCode(){
		int i=0;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		List<Company> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			Company c=list.get(0);
			if(c.getCode()!=null)
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
	
	public void deleteUpdateById(Long id) {
		Company entity=getObjectById(id);
		entity.setIsDelete(true);
		save(entity);
	}

	public void deleteUpdateByIds(Long[] ids) {
		if (ArrayUtils.isNotEmpty(ids)) {
			for (int i = 0; i < ids.length; i++) {
				deleteUpdateById(ids[i]);
			}
		}
	}

	public void deleteUpdateByIds(List<Long> ids) {
		if (Collections3.isNotEmpty(ids)) {
			for (Long id : ids) {
				if (id != null) {
					deleteUpdateById(id);
				}
			}
		}
	}
	
}
