package com.vsc.business.gerd.service.work;

import java.util.ArrayList;
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
import org.springside.modules.utils.Collections3;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.Org;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.repository.work.OrgDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 用户分组逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class OrgService extends BaseService<Org> {
	private static Logger logger = LoggerFactory
			.getLogger(OrgService.class);

	@Autowired
	private OrgDao orgDao;
	
	@Autowired
	private ParkingLotService parkingLotService;

	@Override
	public PagingAndSortingRepository<Org, Long> getPagingAndSortingRepositoryDao() {
		return this.orgDao;
	}

	@Override
	public JpaSpecificationExecutor<Org> getJpaSpecificationExecutorDao() {
		return this.orgDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<Org> findList(Map<String, Object> filterParams) {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<Org> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	
	/**
	 * 根据编码查询权限 ，未删除的
	 * @param code
	 * @return
	 */
	public Org getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("EQ_code", code);
		return find(searchParams);
	}
	
	public Org save(Org entity,String[] ids) {
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);
		if(ids!=null){
			List<ParkingLot> list=new ArrayList<ParkingLot>();
			for(String id:ids){
				ParkingLot parkingLot=parkingLotService.findUniqueBy("id",id);
				list.add(parkingLot);
			}
			entity.setParkingLots(list);
		}
		return super.save(entity);
	}

	public void deleteUpdateById(Long id) {
		Org entity=getObjectById(id);
		entity.setIsDelete(true);
		entity.setParkingLots(null);
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
