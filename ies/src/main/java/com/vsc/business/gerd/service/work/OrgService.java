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
	 * 根据编码查询权限 ，未删除的
	 * @param code
	 * @return
	 */
	public Org getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("code", code);
		return find(searchParams);
	}
	
	public Org save(Org entity,String[] codes) {
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);
		if(codes!=null){
			List<ParkingLot> list=new ArrayList<ParkingLot>();
			for(String code:codes){
				ParkingLot parkingLot=parkingLotService.findUniqueBy("code",code);
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
