package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
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

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ParkingCamera;
import com.vsc.business.gerd.entity.work.ParkingCameraLog;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.repository.work.ParkingCameraDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;

/**
 * 全视频相机数据操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingCameraService extends BaseService<ParkingCamera> {

	@Autowired
	private ParkingCameraDao parkingGarageDao;
	
	@Autowired
	private ParkingGarageService parkingGarageService;
	
	@Override
	public PagingAndSortingRepository<ParkingCamera, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingGarageDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingCamera> getJpaSpecificationExecutorDao() {
		return this.parkingGarageDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 * @throws Exception 
	 */
	public List<ParkingCamera> findAllList(Map<String, Object> filterParams) throws Exception {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * @throws Exception 
	 */
	@Override
	public Page<ParkingCamera> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public ParkingCamera save(ParkingCamera entity) throws Exception {
		if (entity.getParkingGarage() != null && entity.getParkingGarage().getId() != null) {
			ParkingGarage parkingGarage = parkingGarageService.getObjectById(entity.getParkingGarage().getId());
			entity.setParkingGarage(parkingGarage);
		} else {
			entity.setParkingGarage(null);
		}
		
		User user=null;
		try {
			user = ShiroUserUtils.GetCurrentUser();
		} catch (Exception e) {
		}
		if(user!=null){
			Date now=CoreUtils.nowtime();
			if(entity.getId()==null){
				entity.setCreateDate(now);
				entity.setCreateUser(user);	
				String code=null;
				boolean flag=true;
				int i=0;
				while(flag){
					code=CodeUtils.GenerateCode(this.getMaxCode()+i,5);
					ParkingCamera p=getByCode(code);
					if(p==null){
						flag=false;
					}
					i++;
				}
				entity.setCode(code);
			}
			entity.setUpdateUser(user);
			entity.setUpdateDate(now);
		}

		return super.save(entity);
	}
	/**
	 * 保存，接口用
	 * @param entity
	 * @return
	 */
	public ParkingCamera update(ParkingCamera entity){
		try {
			return super.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据code查询，未删除的
	 * @param code
	 * @return
	 */
	public ParkingCamera getByCode(String code){
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
		List<ParkingCamera> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			ParkingCamera c=list.get(0);
			if(c.getCode()!=null)
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
	public void deleteUpdateById(Long id) throws Exception {
		ParkingCamera entity=getObjectById(id);
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
	
	/**
	 * 根据ParkingCameraLog查询，未删除的
	 * @param ParkingCameraLog
	 * @return
	 */
	public ParkingCamera getByParkingCameraLog(ParkingCameraLog entity){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("EQ_cameraIp",entity.getCameraIp());
		searchParams.put("RLIKE_parkingGarage.parkingLotCode",entity.getParkingLotCode());
		return this.find(searchParams);
	}
}
