package com.vsc.business.gerd.service.work;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import com.vsc.business.gerd.entity.work.Org;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.repository.work.OrgDao;
import com.vsc.modules.service.BaseService;

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

	
	public Org save(Org entity,String[] codes) {
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
