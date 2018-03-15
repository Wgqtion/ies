package com.vsc.business.gerd.service.work;

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
import com.vsc.business.gerd.entity.work.Car;
import com.vsc.business.gerd.repository.work.CarDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;

/**
 * 车辆 逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class CarService extends BaseService<Car> {
	private static Logger logger = LoggerFactory
			.getLogger(CarService.class);

	@Autowired
	private CarDao carDao;

	@Override
	public PagingAndSortingRepository<Car, Long> getPagingAndSortingRepositoryDao() {
		return this.carDao;
	}

	@Override
	public JpaSpecificationExecutor<Car> getJpaSpecificationExecutorDao() {
		return this.carDao;
	}
	
	/**
	 * 根据条件查询，未删除的
	 */
	@Override
	public List<Car> findList(Map<String, Object> filterParams) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 */
	@Override
	public Page<Car> findPage(Map<String, Object> filterParams, PageRequest pageRequest) {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}

	public void deleteUpdateById(Long id) {
		Car entity=getObjectById(id);
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
