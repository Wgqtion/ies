package com.vsc.business.gerd.service.work;

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
import com.vsc.business.gerd.entity.work.CarInfo;
import com.vsc.business.gerd.repository.work.CarInfoDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;

/**
 * 车辆 逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class CarInfoService extends BaseService<CarInfo> {

	@Autowired
	private CarInfoDao carInfoDao;

	@Override
	public PagingAndSortingRepository<CarInfo, Long> getPagingAndSortingRepositoryDao() {
		return this.carInfoDao;
	}

	@Override
	public JpaSpecificationExecutor<CarInfo> getJpaSpecificationExecutorDao() {
		return this.carInfoDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 * @throws Exception 
	 */
	@Override
	public List<CarInfo> findList(Map<String, Object> filterParams) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * @throws Exception 
	 */
	@Override
	public Page<CarInfo> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public void deleteUpdateById(Long id) throws Exception {
		CarInfo entity=getObjectById(id);
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
}
