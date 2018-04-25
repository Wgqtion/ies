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
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.ParkingPassages;
import com.vsc.business.gerd.repository.work.ParkingPassagesDao;
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
public class ParkingPassagesService extends BaseService<ParkingPassages>{
  
	@Autowired
	private ParkingPassagesDao parkingPassagesDao;

	@Autowired
	private ParkingLotService parkingLotService;
  	
	@Override
	public PagingAndSortingRepository<ParkingPassages, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingPassagesDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingPassages> getJpaSpecificationExecutorDao() {
		return this.parkingPassagesDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<ParkingPassages> findList(Map<String, Object> filterParams) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	public List<ParkingPassages> findAllList(Map<String, Object> filterParams) {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<ParkingPassages> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public ParkingPassages save(ParkingPassages entity) {
		if(entity.getParkingLot()!=null&&entity.getParkingLot().getId()!=null){
			ParkingLot parkingLot=parkingLotService.getObjectById(entity.getParkingLot().getId());
			entity.setParkingLot(parkingLot);
		}else{
			entity.setParkingLot(null);
		}
		
		
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
				ParkingPassages p=getByCode(code);
				if(p==null){
					flag=false;
				}
				i++;
			}
			entity.setCode(code);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);

		return this.parkingPassagesDao.save(entity);
	}
	
	public void deleteUpdateById(Long id) {
		ParkingPassages entity=getObjectById(id);
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
	public ParkingPassages getByCode(String code){
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
		List<ParkingPassages> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			ParkingPassages c=list.get(0);
			if(c.getCode()!=null)
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
}
