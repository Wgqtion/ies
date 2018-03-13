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
import com.vsc.business.gerd.entity.work.Company;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.repository.work.ParkingGarageDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;

/**
 * 车位数据操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingGarageService extends BaseService<ParkingGarage> {
	private static Logger logger = LoggerFactory
			.getLogger(ParkingGarageService.class);

	@Autowired
	private ParkingGarageDao parkingGarageDao;

	@Override
	public PagingAndSortingRepository<ParkingGarage, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingGarageDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingGarage> getJpaSpecificationExecutorDao() {
		return this.parkingGarageDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<ParkingGarage> findList(Map<String, Object> filterParams) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLotArea.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	public List<ParkingGarage> findAllList(Map<String, Object> filterParams) {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<ParkingGarage> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingLotArea.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public ParkingGarage save(ParkingGarage entity) {
		User user=ShiroUserUtils.GetCurrentUser();
		if(user!=null){
			Date now=CoreUtils.nowtime();
			if(entity.getId()==null){
				entity.setCreateDate(now);
				entity.setCreateUser(user);	
				String code=null;
				boolean flag=true;
				while(flag){
					code=CodeUtils.GenerateCode(this.getMaxCode(),5);
					ParkingGarage p=getByCode(code);
					if(p==null){
						flag=false;
					}
				}
				entity.setCode(code);
			}
			entity.setUpdateUser(user);
			entity.setUpdateDate(now);
		}

		return this.parkingGarageDao.save(entity);
	}
	
	public void deleteUpdateById(Long id) {
		ParkingGarage entity=getObjectById(id);
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
	public ParkingGarage getByCode(String code){
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
		List<ParkingGarage> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			ParkingGarage c=list.get(0);
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
}
