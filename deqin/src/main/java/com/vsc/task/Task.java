package com.vsc.task;

import java.util.Date;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.AccountService;
import com.vsc.business.core.service.security.UserService;
import com.vsc.business.gerd.service.work.CampusService;
import com.vsc.business.gerd.service.work.CardInfoService;
import com.vsc.business.gerd.service.work.CardTypeService;
import com.vsc.business.gerd.service.work.JinXiaoZhengService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ShoufeiService;
import com.vsc.business.gerd.service.work.SyncLogService;

/**
 * 
 * @author AmberK 定时任务管理器
 */
public class Task {

	private final Log log = LogFactory.getLog(Task.class);

	private ParkingLotService parkingLotService;

	private CampusService campusService;

	private JinXiaoZhengService jinXiaoZhengService;

	private CardInfoService cardInfoService;

	private SyncLogService syncLogService;

	private ShoufeiService shoufeiService;

	private CardTypeService cardTypeService;

	private AccountService accountService;

	private UserService userService;

	public String loginName = "sys";

	/**
	 * 同步校区管理、校门管理 同步规则：每天凌晨执行一次
	 */
	public void execu1() {
		try {
			this.jinXiaoZhengService.synchronizationCampus();
			this.parkingLotService.synchronizationParkingLot();
			this.campusService.synchronizationCampus();
			this.syncLogService.addSyncLog("sys", "同步校区", loginName);

			log.info("######同步校区管理、校门管理######-end:" + new Date());
		} catch (Exception e) {
			log.error("######同步校区管理、校门管理######-失败:" + new Date() + "异常信息：" + e.getMessage());
			this.syncLogService.addSyncLog("sys", "同步校区-失败:" + e.getMessage(), loginName);
		}
	}

	/**
	 * 同步进校证类型、进校证、收费规则 同步规则：每十分钟执行一次
	 */
	public void execu2() {
		try {
			this.jinXiaoZhengService.synchronizationCardType();
			this.cardTypeService.synchronizationCardType();
			this.syncLogService.addSyncLog("sys", "同步进校证类型-成功", loginName);
			log.info("######同步进校证类型######-end:" + new Date());
		} catch (Exception e) {
			log.error("######同步进校证类型######-失败:" + new Date() + "-异常信息：" + e.getMessage());
			this.syncLogService.addSyncLog("sys", "同步进校证类型-失败:" + e.getMessage(), loginName);
		}
		try {

			this.jinXiaoZhengService.synchronizationCardInfo();
			this.jinXiaoZhengService.synchronizationCardTemp();
			this.cardInfoService.synchronizationCardInfo();
			this.syncLogService.addSyncLog("sys", "同步进校证-成功", loginName);
			log.info("######同步进校证######-end:" + new Date());
		} catch (Exception e) {
			log.error("######同步进校证######-失败:" + new Date() + "-异常信息：" + e.getMessage());
			this.syncLogService.addSyncLog("sys", "同步进校证-失败:" + e.getMessage(), loginName);
		}
		try {
			this.jinXiaoZhengService.synchronizationShoufei();
			this.shoufeiService.synchronizationShoufei();
			this.syncLogService.addSyncLog("sys", "同步收费规则-成功", loginName);
			log.info("######同步收费规则######-end:" + new Date());

		} catch (Exception e) {
			log.error("######同步收费规则######-失败:" + new Date() + "-异常信息：" + e.getMessage());
			this.syncLogService.addSyncLog("sys", "同步收费规则-失败:" + e.getMessage(), loginName);
		}
	}

	public void execu3() {
		try {

			this.parkingLotService.syncParkingLotAreaCarNumber();
			this.syncLogService.addSyncLog("sys", "同步校区片区停车数量", loginName);
			log.info("######同步校区片区停车数量######-end:" + new Date());

		} catch (Exception e) {
			this.syncLogService.addSyncLog("sys", "同步校区片区停车数量-失败", loginName);
			log.error("######同步校区片区停车数量######-失败:" + new Date() + "异常信息：" + e.getMessage());
		}

		try {

			this.parkingLotService.syncParkingLotCarNumber();
			this.syncLogService.addSyncLog("sys", "同步校区停车数量", loginName);
			log.info("######同步校区停车数量######-end:" + new Date());

		} catch (Exception e) {
			this.syncLogService.addSyncLog("sys", "同步校区停车数量-失败", loginName);
			log.error("######同步校区停车数量######-失败:" + new Date() + "异常信息：" + e.getMessage());
		}
	}

	/**
	 * 同步会员信息:每次同步1000条数据 同步规则：每十分钟执行一次
	 */
	public void execu4() {
		try {
			log.info("######同步同步会员######-end:" + new Date());
			this.userService.synchronizationUser();
			this.syncLogService.addSyncLog("sys", "同步会员", loginName);

			log.info("######同步同步会员######-end:" + new Date());
		} catch (Exception e) {
			log.error("######同步会员######-失败:" + new Date() + "异常信息：" + e.getMessage());
			this.syncLogService.addSyncLog("sys", "同步会员-失败:" + e.getMessage(), loginName);
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ParkingLotService getParkingLotService() {
		return parkingLotService;
	}

	public void setParkingLotService(ParkingLotService parkingLotService) {
		this.parkingLotService = parkingLotService;
	}

	public CampusService getCampusService() {
		return campusService;
	}

	public void setCampusService(CampusService campusService) {
		this.campusService = campusService;
	}

	public JinXiaoZhengService getJinXiaoZhengService() {
		return jinXiaoZhengService;
	}

	public void setJinXiaoZhengService(JinXiaoZhengService jinXiaoZhengService) {
		this.jinXiaoZhengService = jinXiaoZhengService;
	}

	public CardInfoService getCardInfoService() {
		return cardInfoService;
	}

	public void setCardInfoService(CardInfoService cardInfoService) {
		this.cardInfoService = cardInfoService;
	}

	public SyncLogService getSyncLogService() {
		return syncLogService;
	}

	public void setSyncLogService(SyncLogService syncLogService) {
		this.syncLogService = syncLogService;
	}

	public ShoufeiService getShoufeiService() {
		return shoufeiService;
	}

	public void setShoufeiService(ShoufeiService shoufeiService) {
		this.shoufeiService = shoufeiService;
	}

	public CardTypeService getCardTypeService() {
		return cardTypeService;
	}

	public void setCardTypeService(CardTypeService cardTypeService) {
		this.cardTypeService = cardTypeService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
