package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ParkingParam;
import com.vsc.business.gerd.repository.work.ParkingParamDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 停车参数逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingParamService extends BaseService<ParkingParam> {

	@Autowired
	private ParkingParamDao parkingParamDao;

	@Override
	public PagingAndSortingRepository<ParkingParam, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingParamDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingParam> getJpaSpecificationExecutorDao() {
		return this.parkingParamDao;
	}
	/**
	 * 根据场区编号查询，未删除
	 */
	public ParkingParam getByParkingLotCode(String parkingLotCode){
		Map<String, Object> filterParams=Maps.newHashMap();
		filterParams.put("EQ_isDelete", 0);
		filterParams.put("EQ_parkingLotCode",parkingLotCode);
		try {
			return super.find(filterParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * @throws Exception 
	 */
	@Override
	public Page<ParkingParam> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}
	
	public ParkingParam save(ParkingParam entity) throws Exception {
		
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);	
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);

		return super.save(entity);
	}
	

	public void deleteUpdateById(Long id) throws Exception {
		ParkingParam entity=getObjectById(id);
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

	public void deleteUpdateByIds(List<Long> ids) throws Exception {
		if (Collections3.isNotEmpty(ids)) {
			for (Long id : ids) {
				if (id != null) {
					deleteUpdateById(id);
				}
			}
		}
	}

	
	
}
