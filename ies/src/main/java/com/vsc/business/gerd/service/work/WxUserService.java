package com.vsc.business.gerd.service.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Collections3;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.WxUser;
import com.vsc.business.gerd.repository.work.WxUserDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;

/**
 * 小程序用户逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class WxUserService extends BaseService<WxUser> {

	@Autowired
	private WxUserDao wxUserDao;

	@Override
	public PagingAndSortingRepository<WxUser, Long> getPagingAndSortingRepositoryDao() {
		return this.wxUserDao;
	}

	@Override
	public JpaSpecificationExecutor<WxUser> getJpaSpecificationExecutorDao() {
		return this.wxUserDao;
	}

	/**
	 * 根据weixinId查询，未删除的
	 * @param weixinId
	 * @return
	 */
	public WxUser getByWeixinId(String weixinId){
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("EQ_weixinId", weixinId);
		return find(searchParams);
	}
	
	public WxUser save(WxUser entity,String[] codes) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		entity.setUpdateUser(user);
		entity.setUpdateDate(CoreUtils.nowtime());
		return super.save(entity);
	}

	public void deleteUpdateById(Long id) throws Exception {
		WxUser entity=getObjectById(id);
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

	public void deleteUpdateByIds(List<Long> ids) throws Exception {
		if (Collections3.isNotEmpty(ids)) {
			for (Long id : ids) {
				if (id != null) {
					deleteUpdateById(id);
				}
			}
		}
	}
	
}
