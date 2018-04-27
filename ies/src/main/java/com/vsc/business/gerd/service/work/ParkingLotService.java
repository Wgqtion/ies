package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.business.gerd.entity.work.Org;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.repository.work.ParkingLotDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.shiro.ShiroUserUtils;
import com.vsc.util.CodeUtils;
import com.vsc.util.CoreUtils;

/**
 * 停车场逻辑操作
 * @author XiangXiaoLin
 *
 */
@Service
@Transactional
public class ParkingLotService extends BaseService<ParkingLot> {

	@Autowired
	private ParkingLotDao parkingLotDao;
	
	@Autowired
	private AttachDao attachDao;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private ParkingLockService parkingLockService;

	@Override
	public PagingAndSortingRepository<ParkingLot, Long> getPagingAndSortingRepositoryDao() {
		return this.parkingLotDao;
	}

	@Override
	public JpaSpecificationExecutor<ParkingLot> getJpaSpecificationExecutorDao() {
		return this.parkingLotDao;
	}
	
	/**
	 * 获取树
	 * @throws Exception 
	 */
	public List<ParkingLot> findTree() throws Exception {
		Map<String, Object> searchParams = Maps.newHashMap();
		return this.findList(searchParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 * @throws Exception 
	 */
	@Override
	public List<ParkingLot> findList(Map<String, Object> filterParams) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}
	
	/**
	 * 根据条件查询，未删除的
	 * @throws Exception 
	 */
	public List<ParkingLot> findAllList(Map<String, Object> filterParams) throws Exception {
		filterParams.put("EQ_isDelete", 0);
		return super.findList(filterParams);
	}

	/**
	 * 根据条件查询，未删除，like 用户公司code%
	 * @throws Exception 
	 */
	@Override
	public Page<ParkingLot> findPage(Map<String, Object> filterParams, PageRequest pageRequest) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0); 
		return super.findPage(filterParams, pageRequest);
	}
	/**
	 * 小程序查询场区及地锁车位
	 * @param userId
	 * @param parkingLotId
	 * @return
	 * @throws Exception 
	 */
	public Set<ParkingLot> findParkingLots(Long userId,Long parkingLotId) throws Exception{
		Map<String, Object> orgParams = new HashMap<String, Object>();
		orgParams.put("EQ_isEnabled", false);
		List<Org> orgs = this.orgService.findList(orgParams);
		orgParams.remove("EQ_isEnabled");
		orgParams.put("EQ_users.id", userId);
		orgs.addAll(this.orgService.findList(orgParams));

		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isEnabled", true);
		if(parkingLotId==null){
			searchParams.put("ISNULL_parent", null);	
		}else{
			searchParams.put("EQ_parent.id", parkingLotId);
		}
		Set<ParkingLot> parkingLots = new LinkedHashSet<ParkingLot>();
		searchParams.put("ISNULL_orgCode", null);
		parkingLots.addAll(this.findAllList(searchParams));
		searchParams.remove("ISNULL_orgCode");
		if (orgs != null) {
			searchParams.put("EQ_org.isDelete", 0);
			for (Org org : orgs) {
				searchParams.put("EQ_org.code", org.getCode());
				parkingLots.addAll(this.findAllList(searchParams));
			}
		}
		if(parkingLotId!=null&&parkingLots.size()==0){
			ParkingLot parkingLot=this.getObjectById(parkingLotId);
			parkingLot.setIsLast(true);
			parkingLots.add(parkingLot);
		}
		int i=0;
		for (ParkingLot p : parkingLots) {
			this.parkingLockService.findParkingLocks(p,parkingLotId,i,userId);
			i++;
		}
		return parkingLots;
	}

	public ParkingLot save(ParkingLot entity, Long photoAttachId) throws Exception {
		if(entity.getParent()!=null&&entity.getParent().getId()!=null){
			ParkingLot parent=this.getObjectById(entity.getParent().getId());
			entity.setParentCode(parent.getCode());
			entity.setCompanyCode(parent.getCompanyCode());
			entity.setParent(null);
		}else{
			entity.setParent(null);
		}
		User user=ShiroUserUtils.GetCurrentUser();
		Date now=CoreUtils.nowtime();
		if(entity.getId()==null){
			entity.setCreateDate(now);
			entity.setCreateUser(user);	
			String code="";
			boolean flag=true;
			int i=0;
			while(flag){
				if(entity.getParentCode()!=null){
					code=entity.getParentCode();
				}
				code=code+CodeUtils.GenerateCode(this.getMaxCode(code)+i,4);
				ParkingLot c=getByCode(code);
				if(c==null){
					flag=false;
				}
				i++;
			}
			entity.setCode(code);
		}
		entity.setUpdateUser(user);
		entity.setUpdateDate(now);
		
		
		if (photoAttachId != null) {
			entity.setPhotoAttach(attachDao.findOne(photoAttachId));
		}else{
			entity.setPhotoAttach(null);
		}

		return this.parkingLotDao.save(entity);
	}
	
	/**
	 * 查询父节点下的当前最大编码
	 * @return
	 * @throws Exception 
	 */
	public int getMaxCode(String parentCode) throws Exception{
		int i=0;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		if(CoreUtils.isEmpty(parentCode)){
			searchParams.put("ISNULL_parentCode",parentCode);	
		}else{
			searchParams.put("EQ_parentCode",parentCode);
		}
		List<ParkingLot> list=this.findAll(searchParams, "code","desc");
		if(list!=null&&list.size()>0){
			ParkingLot c=list.get(0);
			String code=c.getCode();
			if(code!=null)
			{
				if(parentCode!=null){
					code=code.substring(code.indexOf(parentCode),4);
				}
				i=Integer.valueOf(code);	
				if(i>9998){
					throw new Exception("超出限制9999层");
				}
			}
		}
		return i;
	}
	
	/**
	 * 根据code查询，未删除的
	 * @param code
	 * @return
	 */
	public ParkingLot getByCode(String code){
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_isDelete",0);
		searchParams.put("EQ_code",code);
		return this.find(searchParams);
	}
	
	public void deleteUpdateById(Long id) throws Exception {
		ParkingLot entity=getObjectById(id);
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
