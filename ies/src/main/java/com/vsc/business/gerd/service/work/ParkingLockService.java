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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.repository.work.ParkingLockDao;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.modules.tcp.TcpClient;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;
import com.vsc.util.HexUtils;

import io.netty.buffer.ByteBuf;

/**
 * 地锁逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingLockService extends BaseService<ParkingLock> {
	private static Logger logger = LoggerFactory.getLogger(ParkingLockService.class);

	@Autowired
	private ParkingLockDao parkingLockDao;

	@Autowired
	private ParkingGarageService parkingGarageService;
	
	//微信用户
    @Autowired
    private WxUserService wxUserService;
	
	@Autowired
	private ParkingLockOperationEventService parkingLockOperationEventService;

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
	public ParkingLock getByCode(String ipAddress,String lockNum){
		Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_ipAddress", ipAddress);
        orderMap.put("EQ_lockNum", lockNum);
        orderMap.put("EQ_isDelete",0);
        return find(orderMap);
	}
	
	/**
	 * 根据parkingGarage查询，未删除的
	 */
	public ParkingLock getByGarageId(Long parkingGarageId){
		Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("EQ_parkingGarage.id", parkingGarageId);
        orderMap.put("EQ_isDelete",0);
        return find(orderMap);
	}
	
	/**
	 * 根据条件查询，未删除的
	 * @throws Exception 
	 */
	@Override
	public List<ParkingLock> findList(Map<String, Object> filterParams) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 * @throws Exception 
	 */
	public List<ParkingLock> findAllList(Map<String, Object> filterParams) throws Exception {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * @throws Exception 
	 */
	@Override
	public Page<ParkingLock> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_parkingGarage.parkingLot.companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}
	
	
	public void deleteUpdateById(Long id) throws Exception {
		ParkingLock entity=getObjectById(id);
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
	
	public ParkingLock save(ParkingLock entity){
		if(entity.getParkingGarage()!=null&&entity.getParkingGarage().getId()!=null){
			ParkingGarage parkingGarage=parkingGarageService.getObjectById(entity.getParkingGarage().getId());
			entity.setParkingGarage(parkingGarage);
		}else{
			entity.setParkingGarage(null);
		}
		
		
		User user=null;
		try {
			user = ShiroUserUtils.GetCurrentUser();
		} catch (Exception e) {
			//logger.warn(e.getMessage());
		}
		if(user!=null){
			Date now=CoreUtils.nowtime();
			if(entity.getId()==null){
				entity.setCreateDate(now);
				entity.setCreateUser(user);	
				
				String code=null;
				boolean flag=true;
				int i=0;
				while(flag){
					code=CodeUtils.GenerateCode(this.getMaxCode()+i,5);
					ParkingLock p=getByCode(code);
					if(p==null){
						flag=false;
					}
					i++;
				}
				entity.setCode(code);
			}
			entity.setUpdateUser(user);
			entity.setUpdateDate(now);
			
		}
		return this.parkingLockDao.save(entity);
	}
	
	/**
	 * 根据code查询，未删除的
	 * @param code
	 * @return
	 */
	public ParkingLock getByCode(String code){
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
		List<ParkingLock> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			ParkingLock c=list.get(0);
			if(c.getCode()!=null)
			i=Integer.valueOf(c.getCode());
		}
		return i;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public String reverse(Long[] ids,String state, Long userId, int sourceType){
		String message=new String();
		
		
		List<ParkingLock> vl = this.findIds(ids,this.parkingLockDao);
		 
		String lockNumVl=CoreUtils.fetchElementPropertyToString(vl, "lockNum", ",");//地锁编号
		String ipAddressVl=CoreUtils.fetchElementPropertyToString(vl, "ipAddress", ",");//地锁区域编号
		
		String[] lockNums=lockNumVl.split(",");
		String[] ipAddresss=ipAddressVl.split(",");
		for(int i=0;i<ids.length;i++){
			ParkingLock lock=vl.get(i);
			String lockNum=lockNums[i];
			String ipAddress=ipAddresss[i];
			
			Date now = CoreUtils.nowtime();
			ParkingLockOperationEvent lockEvent = new ParkingLockOperationEvent();
			lockEvent.setReportedTime(now);
			lockEvent.setEventType(Integer.valueOf(state));
			lockEvent.setSourceType(sourceType);
			lockEvent.setStatus(0);
			lockEvent.setParkingLock(lock);
			if(userId!=null){
				lockEvent.setWxUser(wxUserService.getObjectById(userId));
			}
			//lockEvent.setParkingLock(vm);


			ParkingLock oldLock=vl.get(i);
			//判断当前地锁在线状态
			if(!oldLock.getIsOnline()){
				lockEvent.setStatus(2);//当前地锁离线
				parkingLockOperationEventService.save(lockEvent);
				message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，地锁状态不在线，请稍后再试";
				return message;
			}
			//判断当前地锁状态 true 02开启 false 01关闭 
			if((oldLock.getIsOpen()&&"02".equals(state))||((!oldLock.getIsOpen())&&"01".equals(state))){
				lockEvent.setStatus(3);//当前状态已经是要控制的状态
				parkingLockOperationEventService.save(lockEvent);
				return message;
			}
			
			parkingLockOperationEventService.save(lockEvent);
			
			//更新-start
//			String param2  = "lockArea=" + ipAddressVl + "&lockNum="+lockNumVl+"&eventType="+state;
//			String resultMsg = HttpRequestUtil.sendSocketGet("reverse", param2,"utf-8");
//			logger.info("更新地锁开关状态：" +param2+" 更新结果:"+ resultMsg);

			
			
			//发送指令
			ByteBuf msg=HexUtils.getByteBuf(userId.toString(), ipAddress, lockNum, state);
			logger.info("userId："+userId+"，区域编号："+ipAddress+"，地锁编号："+lockNum+"，state："+state);
			try {
				message=TcpClient.sendMsg(msg,parkingLockOperationEventService);
				if(message.length()>0){
					return message.toString();
				}
				Map<String, Object> filters=new HashMap<String, Object>();
				filters.put("id",lockEvent.getId());
				message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，指令请求超时，请重试";
				for(int j=0;j<10;j++){
					Thread.sleep(500);
					List<MapBean<String, Object>> ploeList = this.parkingLockOperationEventService.findIbatisQuery("t_parking_operation_event.getStatus", filters);
					if(ploeList==null){
						message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，系统操作日志异常";
						return message;
					}
					Integer status=null;
					if(ploeList!=null){
						status=(Integer) ploeList.get(0).get("status");
					}
					if(status==null){
						message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，系统操作日志异常";
						return message;
					}
					if(status.intValue()==2){
						message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，地锁数据错误";
						return message;
					}
					if(status.intValue()==3){
						message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，地锁已断开连接，请稍后再试";
						return message;
					}
					if(status.intValue()==1){
						message="";
						break;
					}
				}
				
				//判断地锁事件
				if(message.length()==0){
					Map<String, Object> filterParams=new HashMap<String, Object>();
					filterParams.put("id",ids[i]);
					message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，指令请求超时，请重试";
					for(int j=0;j<60;j++){
						Thread.sleep(500);
						ParkingLock pl=this.findUniqueBy("id",ids[i]);
						List<MapBean<String, Object>> parkingLocks=this.findIbatisQuery("t_parking_lock.get", filterParams);
						if(parkingLocks!=null&&parkingLocks.size()>0){
							MapBean<String, Object> parkingLock=parkingLocks.get(0);
							Integer isOpen=(Integer) parkingLock.get("IS_OPEN");
							if(isOpen==null){
								message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，地锁查询出错";
								continue;
							}
							if("1".equals(isOpen.toString())&&"02".equals(state)){
								message="";
								break;
							}else if("0".equals(isOpen.toString())&&"01".equals(state)){
								message="";
								break;
							}
						}else{
							message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，地锁查询出错";
							continue;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				message="区域编号："+ipAddress+"，地锁编号："+lockNum+"，系统发生异常";
				return message;
			}
			
			//更新-end
		}
		 
		return message;
	}

}
