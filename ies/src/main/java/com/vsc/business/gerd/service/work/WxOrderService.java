package com.vsc.business.gerd.service.work;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.WriterException;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.repository.work.WxOrderDao;
import com.vsc.constants.Constants;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CoreUtils;
import com.vsc.util.QrCodeCreateUtil;
import com.vsc.util.RandomUtil;

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
	 * 根据条件查询，like 用户公司code%
	 */
	@Override
	public Page<WxOrder> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_wxCores.parkingLock.parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		return super.findPage(filterParams, pageRequest);
	}
	
	
	public List<WxOrder> getListByWeixinId(String weixinId){
		try {
			Map<String, Object> searchParams = new HashMap<String, Object>();
			searchParams.put("EQ_status",1);
			searchParams.put("EQ_weixinId",weixinId);
			return super.findAll(searchParams, "createTime","DESC");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据weixinId查询状态0的订单
	 */
	public WxOrder getByWeixinId(String weixinId){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_status",0);
		searchParams.put("EQ_weixinId",weixinId);
		return super.find(searchParams);
	}
	
	/**
	 * 保存订单
	 */
	public WxOrder save(WxCore wc){
		WxOrder entity=this.getByWeixinId(wc.getWeixinId());
		if(entity==null){
			entity=new WxOrder();
			entity.setCreateTime(new Date());
			entity.setStatus(wc.getStatus());
			String code=null;
			boolean flag=true;
			while(flag){
				code=RandomUtil.getRandomFileName();
				WxOrder p=getByCode(code);
				if(p==null){
					flag=false;
				}
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
		entity.setQrcodePath(Constants.QRCODE_ROOT_FOLDER+CoreUtils.getStoragePath()+Constants.SPT+entity.getCode()+".jpg");
		try {
			QrCodeCreateUtil.createQrCode(FileUtils.openOutputStream(new File(entity.getQrcodePath())), entity.getCode(), 900, "JPEG");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	 * 根据code查询
	 * @param code
	 * @return
	 */
	public WxOrder getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_code",code);
		return this.find(searchParams);
	}
	
	/**
	 * 根据weixinId查询，最新的一条
	 */
	public WxOrder getLastByWeixinId(String weixinId){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_weixinId",weixinId);
		PageRequest pageRequest=CoreUtils.buildPageRequest(1,1, "createTime", "DESC");
		Page<WxOrder> page=null;
		try {
			page=super.findPage(searchParams, pageRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.getContent().size()>0){
			return page.getContent().get(0);
		}
		return null;
	}
}
