package com.vsc.business.gerd.service.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.Yuding;
import com.vsc.business.gerd.repository.work.YudingDao;
import com.vsc.modules.service.BaseService;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class YudingService extends BaseService<Yuding> {

	@Autowired
	private YudingDao yudingDao;

	@Override
	public PagingAndSortingRepository<Yuding, Long> getPagingAndSortingRepositoryDao() {
		return this.yudingDao;
	}

	@Override
	public JpaSpecificationExecutor<Yuding> getJpaSpecificationExecutorDao() {
		return this.yudingDao;
	}

	
	public List<Yuding> findByWxUser(String weixinId){
		Map<String,Object> filterParams=new HashMap<String, Object>();
		filterParams.put("EQ_isDelete", 0);
		filterParams.put("EQ_wxUser.weixinId", weixinId);
		try {
			return this.findList(filterParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
