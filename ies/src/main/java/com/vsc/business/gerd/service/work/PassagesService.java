package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.Passages;
import com.vsc.business.gerd.repository.work.PassagesDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class PassagesService extends BaseService<Passages>{
	private static Logger logger = LoggerFactory.getLogger(PassagesService.class);
  
	@Autowired
	private PassagesDao passagesDao;

    
  	
	@Override
	public PagingAndSortingRepository<Passages, Long> getPagingAndSortingRepositoryDao() {
		return this.passagesDao;
	}

	@Override
	public JpaSpecificationExecutor<Passages> getJpaSpecificationExecutorDao() {
		return this.passagesDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<Passages> findList(Map<String, Object> filterParams) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	public List<Passages> findAllList(Map<String, Object> filterParams) {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<Passages> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public Passages save(Passages entity) {
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);	
			String code=null;
			boolean flag=true;
			int i=0;
			while(flag){
				code=CodeUtils.GenerateCode((this.getMaxCode()+i),5);
				Passages p=getByCode(code);
				if(p==null){
					flag=false;
				}
				i++;
			}
			entity.setCode(code);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);

		return this.passagesDao.save(entity);
	}
	
	public void deleteUpdateById(Long id) {
		Passages entity=getObjectById(id);
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
	
	/**
	 * 根据code查询，未删除的
	 * @param code
	 * @return
	 */
	public Passages getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("EQ_code",code);
		return this.find(searchParams);
	}
	
	/**
	 * 查询当前最大编码
	 * @return
	 */
	public int getMaxCode(){
		int i=0;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		List<Passages> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			Passages c=list.get(0);
			if(c.getCode()!=null)
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
}
