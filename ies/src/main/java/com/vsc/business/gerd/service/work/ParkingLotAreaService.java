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
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.repository.work.ParkingLotAreaDao;
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
public class ParkingLotAreaService extends BaseService<ParkingLotArea> {
	private static Logger logger = LoggerFactory.getLogger(ParkingLotAreaService.class);

	@Autowired
	private ParkingLotAreaDao parkingLotAreaDao;
	@Autowired
	private AttachDao attachDao;

	@Override
	public PagingAndSortingRepository<ParkingLotArea, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLotAreaDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLotArea> getJpaSpecificationExecutorDao() {
		return this.parkingLotAreaDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<ParkingLotArea> findList(Map<String, Object> filterParams) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	public List<ParkingLotArea> findAllList(Map<String, Object> filterParams) {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<ParkingLotArea> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public ParkingLotArea save(ParkingLotArea entity, Long photoAttachId) {
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

		entity.setFullIndexName(entity.getFullName());
		return this.parkingLotAreaDao.save(entity);
	}
	
	public void deleteUpdateById(Long id) {
		ParkingLotArea entity=getObjectById(id);
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
