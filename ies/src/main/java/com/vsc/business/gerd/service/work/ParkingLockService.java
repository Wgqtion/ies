package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.repository.work.ParkingLockDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.modules.tcp.core.ClientMap;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;
import com.vsc.util.HexUtils;
import com.vsc.util.Log4jUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * 地锁逻辑操作
 * 
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingLockService extends BaseService<ParkingLock> {

	@Autowired
	private ParkingLockDao parkingLockDao;

	@Autowired
	private ParkingGarageService parkingGarageService;

	// 微信用户
	@Autowired
	private WxUserService wxUserService;

	@Autowired
	private ParkingLockOperationEventService parkingLockOperationEventService;
	
	@Autowired
	private WxCoreService wxCoreService;

	@Autowired
	private ParkingLockEventLogService parkingLockEventLogService;
	
	private EntityManagerFactory entityManagerFactory;  
    @PersistenceUnit  
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {  
        this.entityManagerFactory = entityManagerFactory;  
    } 
	
	@Override
	public PagingAndSortingRepository<ParkingLock, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLockDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLock> getJpaSpecificationExecutorDao() {
		return this.parkingLockDao;
	}

	/**
	 * 根据ipAddress与lockNum查询，未删除的
	 */
	public ParkingLock getByCode(String ipAddress, String lockNum) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("EQ_ipAddress", ipAddress);
		orderMap.put("EQ_lockNum", lockNum);
		orderMap.put("EQ_isDelete", 0);
		return find(orderMap);
	}

	/**
	 * 根据parkingGarage查询，未删除的
	 */
	public ParkingLock getByGarageId(Long parkingGarageId) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("EQ_parkingGarage.id", parkingGarageId);
		orderMap.put("EQ_isDelete", 0);
		return find(orderMap);
	}

	/**
	 * 根据条件查询，未删除的
	 * 
	 * @throws Exception
	 */
	@Override
	public List<ParkingLock> findList(Map<String, Object> filterParams) throws Exception {
		User user = ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除的
	 * 
	 * @throws Exception
	 */
	public List<ParkingLock> findAllList(Map<String, Object> filterParams) throws Exception {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * 
	 * @throws Exception
	 */
	@Override
	public Page<ParkingLock> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user = ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findPage(filterParams, pageRequest);
	}

	public Page<ParkingLock> findPageTongji(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		filterParams.put("RLIKE_parkingGarage.parkingLot.companyCode", "0004");
		filterParams.put("EQ_isDelete", 0);
		return super.findPage(filterParams, pageRequest);
	}

	public void deleteUpdateById(Long id) throws Exception {
		ParkingLock entity = getObjectById(id);
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

	public ParkingLock save(ParkingLock entity) throws Exception {
		if (entity.getParkingGarage() != null && entity.getParkingGarage().getId() != null) {
			ParkingGarage parkingGarage = parkingGarageService.getObjectById(entity.getParkingGarage().getId());
			entity.setParkingGarage(parkingGarage);
		} else {
			entity.setParkingGarage(null);
		}

		User user =ShiroUserUtils.GetCurrentUser();
		Date now = CoreUtils.nowtime();
		if (entity.getId() == null) {
			entity.setCreateDate(now);
			entity.setCreateUser(user);

			String code = null;
			boolean flag = true;
			int i = 0;
			while (flag) {
				code = CodeUtils.GenerateCode(this.getMaxCode() + i, 5);
				ParkingLock p = getByCode(code);
				if (p == null) {
					flag = false;
				}
				i++;
			}
			entity.setCode(code);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);
		return super.save(entity);
	}

	/**
	 * 更新，super.save
	 */
	public ParkingLock update(ParkingLock entity){
		try {
			super.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	
	/**
	 * 根据code查询，未删除的
	 * 
	 * @param code
	 * @return
	 */
	public ParkingLock getByCode(String code) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete", 0);
		searchParams.put("EQ_code", code);
		return this.find(searchParams);
	}

	/**
	 * 查询当前最大编码
	 * 
	 * @return
	 */
	public int getMaxCode() {
		int i = 0;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		List<ParkingLock> list = this.findAll(searchParams, "code", "desc");
		if (list != null && list.size() > 0) {
			ParkingLock c = list.get(0);
			if (c.getCode() != null)
				i = Integer.valueOf(c.getCode());
		}
		return i;
	}

	/**
	 * 发送地锁控制指令
	 */
	public String reverse(Long id, String state, String weixinId, int sourceType){
		String message = new String();
		ParkingLock lock=getObjectById(id);
		
		String lockNum = lock.getLockNum();
		String ipAddress = lock.getIpAddress();

		ParkingLockOperationEvent lockEvent = new ParkingLockOperationEvent();
		lockEvent.setEventType(state);
		lockEvent.setSourceType(sourceType);
		lockEvent.setStatus(0);
		lockEvent.setParkingLock(lock);
		
		String head="区域编号：" + ipAddress + "，地锁编号：" + lockNum + "，";
		
		if (sourceType == ParkingLockOperationEvent.SOURCETYPE_PHONE) {
			lockEvent.setWxUser(wxUserService.getByWeixinId(weixinId));
			head="";
		}

		// 判断当前地锁在线状态
		if (!lock.getIsOnline()) {
			lockEvent.setStatus(2);// 当前地锁离线
			parkingLockOperationEventService.save(lockEvent);
			message = head+"地锁状态不在线，请稍后再试";
			return message;
		}
		// 判断当前地锁状态 true 02开启 false 01关闭
		if ((lock.getIsOpen() && "02".equals(state)) || ((!lock.getIsOpen()) && "01".equals(state))) {
			lockEvent.setStatus(3);// 当前状态已经是要控制的状态
			parkingLockOperationEventService.save(lockEvent);
			return message;
		}
		// 发送指令
		ChannelHandlerContext ctx=ClientMap.lockMap.get(ipAddress);
		if(ctx==null){
			this.parkingLockEventLogService.downlineAllBy(ipAddress);
			message = head+"地锁已断开连接，请稍后再试";
			return message;
		}
		ByteBuf msg = HexUtils.getClientByteBuf(ipAddress, lockNum, state);
		Log4jUtils.tcpLog.info("区域编号：" + ipAddress + "，地锁编号：" + lockNum + "，state：" + state
				+ "，sources:" + sourceType+",bytes:"+ByteBufUtil.hexDump(msg));
		ctx.channel().writeAndFlush(msg);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("id", lockEvent.getId());
		message = head+"地锁请求超时，请重试";
		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("id",id);
		try {
			Thread.sleep(3000);
			for (int j = 0; j < 20; j++) {
				Thread.sleep(1000);
				EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	 			ParkingLock pl=entityManager.find(ParkingLock.class,lock.getId());
	 			entityManager.close();
	 			if ((pl.getIsOpen() && "02".equals(state))
						||(!pl.getIsOpen() && "01".equals(state))) {
					message = "";
					lockEvent.setStatus(1);
					break;
				}
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parkingLockOperationEventService.save(lockEvent);
		return message;
	}
	/**
	 * 批量发送指令
	 */
	public String reverse(Long[] ids, String state, String weixinId, int sourceType) {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			sb.append(reverse(ids[i], state, weixinId, sourceType));
		}
		return sb.toString();
	}

	/**
	 * 查询场区在线可用地锁
	 */
	public void findParkingLocks(ParkingLot parkingLot, Long parkingLotId, int index, String weixinId)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("RLIKE_parkingGarage.parkingLot.code", parkingLot.getCode());
		params.put("EQ_parkingGarage.isDelete", false);
		params.put("EQ_isEnabled", true);
		List<ParkingLock> parkingLocks = this.findAllList(params);
		if (parkingLocks != null) {
			parkingLot.setGarageNum(parkingLocks.size());
			RemoveNoSurplus(parkingLocks, weixinId);
			parkingLot.setSurplusNum(parkingLocks.size());
		}
		if (parkingLotId != null && index == 0) {
			params.remove("RLIKE_parkingGarage.parkingLot.code");
			params.put("EQ_parkingGarage.parkingLot.id", parkingLotId);
			parkingLocks = this.findAllList(params);
			RemoveNoSurplus(parkingLocks, weixinId);
			parkingLot.setParkingLocks(parkingLocks);
		}
	}

	/**
	 * 不符合余位判断的剔除
	 */
	private void RemoveNoSurplus(List<ParkingLock> parkingLocks, String weixinId) throws Exception {
		for (int i = 0; i < parkingLocks.size();) {
			WxCore wxCore=new WxCore();
			wxCore.setWeixinId(weixinId);
			wxCore.setParkingLockCode(parkingLocks.get(i).getCode());
			int status=wxCoreService.getCoreStatus(wxCore);
			if(status!=0||!parkingLocks.get(i).getIsSurplus()||!parkingLocks.get(i).getIsOnline()){
				parkingLocks.remove(i);
				i--;
			}
			i++;
		}
	}
}
