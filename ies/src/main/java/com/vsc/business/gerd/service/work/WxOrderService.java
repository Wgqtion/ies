package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.repository.work.WxOrderDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CodeUtils;

/**
 * 小程序订单 逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class WxOrderService extends BaseService<WxOrder> {

	@Autowired
	private WxOrderDao wxOrderDao;

	@Override
	public PagingAndSortingRepository<WxOrder, Long> getPagingAndSortingRepositoryDao() {
		return this.wxOrderDao;
	}

	@Override
	public JpaSpecificationExecutor<WxOrder> getJpaSpecificationExecutorDao() {
		return this.wxOrderDao;
	}
	/**
	 * 根据weixinId查询状态0的订单
	 */
	public WxOrder getByWeixinId(String weixinId){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_status",0);
		searchParams.put("EQ_weixinId",weixinId);
		return this.find(searchParams);
	}
	
	/**
	 * 保存订单
	 */
	public WxOrder save(WxCore wc){
		WxOrder entity=this.getByWeixinId(wc.getWeixinId());
		if(entity==null){
			entity=new WxOrder();
			entity.setCreateTime(new Date());
			entity.setStatus(0);
			String code=null;
			boolean flag=true;
			int i=0;
			while(flag){
				code=CodeUtils.GenerateCode(this.getMaxCode()+i,5);
				WxOrder p=getByCode(code);
				if(p==null){
					flag=false;
				}
				i++;
			}
			entity.setCode(code);
		}
		entity.setWeixinId(wc.getWeixinId());
		entity.getWxCores().add(wc);
		try {
			entity=super.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	/**
	 * 支付订单
	 */
	public int payOrder(WxOrder entity){
		entity=this.getByWeixinId(entity.getWeixinId());
		if(entity==null){
			return 1;
		}
		entity.setPayTime(new Date());
		entity.setStatus(1);
		try {
			entity=super.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 根据code查询，未删除的
	 * @param code
	 * @return
	 */
	public WxOrder getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String, Object>();
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
		List<WxOrder> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			WxOrder c=list.get(0);
			if(c.getCode()!=null)
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
}
