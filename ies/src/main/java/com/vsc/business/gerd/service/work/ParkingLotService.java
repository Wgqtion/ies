package com.vsc.business.gerd.service.work;

import java.util.Date;
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
import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.repository.work.ParkingLotDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 停车场逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingLotService extends BaseService<ParkingLot> {
	private static Logger logger = LoggerFactory.getLogger(ParkingLotService.class);

	@Autowired
	private ParkingLotDao parkingLotDao;
	
	@Autowired
	private AttachDao attachDao;

	@Override
	public PagingAndSortingRepository<ParkingLot, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLotDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLot> getJpaSpecificationExecutorDao() {
		return this.parkingLotDao;
	}
	
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<ParkingLot> findList(Map<String, Object> filterParams) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	public List<ParkingLot> findAllList(Map<String, Object> filterParams) {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<ParkingLot> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public ParkingLot save(ParkingLot entity, Long photoAttachId) {
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);	
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);
		
		
		if (photoAttachId != null) {
			entity.setPhotoAttach(attachDao.findOne(photoAttachId));
		}else{
			entity.setPhotoAttach(null);
		}

		return this.parkingLotDao.save(entity);
	}
	
	public void deleteUpdateById(Long id) {
		ParkingLot entity=getObjectById(id);
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
}
