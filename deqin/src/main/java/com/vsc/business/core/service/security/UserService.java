package com.vsc.business.core.service.security;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.repository.security.RoleDao;
import com.vsc.business.core.repository.security.UserDao;
import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

@Service
@Transactional
public class UserService extends BaseService<User> {
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AttachDao attachDao;

	@Override
	public PagingAndSortingRepository<User, Long> getPagingAndSortingRepositoryDao() {
		return this.userDao;
	}

	@Override
	public JpaSpecificationExecutor<User> getJpaSpecificationExecutorDao() {
		return this.userDao;
	}

	public User save(User entity, Long photoAttachId, Long roleId) {

		entity.getRoleList().clear();
		entity.getRoleList().add(roleDao.findOne(roleId));

		if (photoAttachId != null) {
			entity.setPhotoAttach(attachDao.findOne(photoAttachId));
		}
		

		this.userDao.save(entity);

		return entity;

	}

	public User save(User entity, Long adminUserId, Long photoAttachId, Long roleId, Long[] hospitalIds) {

		entity.getRoleList().clear();
		entity.getRoleList().add(roleDao.findOne(roleId));

		if (photoAttachId != null) {
			entity.setPhotoAttach(attachDao.findOne(photoAttachId));
		}

		return this.userDao.save(entity);

	}

	public List<User> findAllAdminUser() {
		Map<String, Object> filterParams = Maps.newHashMap();
		filterParams.put("EQ_userType", 2);
		return this.findList(filterParams);
	}
	
	
	public void synchronizationUser(){
		//获取第三方系统的数据
		//新增到本系统中
		
		List<MapBean<String, Object>> newvl = this.ibatisQueryDao.findAll("find.t_user.new");
		System.out.println("###########################newvl:" + newvl.size());
		Date now = CoreUtils.nowtime();
		for (MapBean<String, Object> mapBean : newvl) {
			User entity = new User();
			entity.setLoginName(MapUtils.getString(mapBean, "USER_NO"));//登录名
			entity.setName(MapUtils.getString(mapBean, "NAME"));//姓名
			
			//身份证号码后六位作为密码,如果身份证为空,默认密码为123456
			//如果最后一个为字母,则为小写字母
			entity.setPassword(CoreUtils.creteDefaultPwd(MapUtils.getString(mapBean, "ID_CARD")));//密码
			entity.setCreateTime(now);//创建时间
			entity.setUserType(Long.valueOf(1));//用户类型
			entity.setNIsEnable(MapUtils.getString(mapBean, "N_IS_ENABLE"));//N_人员状态
			entity.setIsEnabled(CoreUtils.getBoolean(MapUtils.getString(mapBean, "N_IS_ENABLE")));	//人员状态
			entity.setUpdateTime(now);
			entity.setIdNumber(MapUtils.getString(mapBean, "ID_CARD"));//身份证号码
			entity.setMobilePhone(MapUtils.getString(mapBean, "MOBILE"));//手机号码
			
			entity.setnUserType(MapUtils.getString(mapBean, "N_USER_TYPE"));
			entity.setCardTypeCode(MapUtils.getString(mapBean, "CARD_TPYE_CODE"));
			entity.setCardTypeName(MapUtils.getString(mapBean, "CARD_TYPE_NAME"));
			entity.setNIsEnable(MapUtils.getString(mapBean, "N_IS_ENABLE"));
			entity.setDept(MapUtils.getString(mapBean, "DEPT"));
			entity.setDeptName(MapUtils.getString(mapBean, "DEPT_NAME"));
			
			
			this.userDao.save(entity);
		}
	}
}
