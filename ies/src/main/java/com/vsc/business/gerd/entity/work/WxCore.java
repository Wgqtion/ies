package com.vsc.business.gerd.entity.work;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 小程序核心 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "wx_core")
public class WxCore extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 702588900414241660L;

	private WxUser wxUser;
	private String weixinId;
	
	/**
	 * 类型，1预约，2解锁
	 */
	private Integer type;
	
	/**
	 * 地锁
	 */
	private ParkingLock parkingLock;
	private String parkingLockCode;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 状态，0结束，1使用中
	 */
	private Integer status;
	/**
	 * 是否点击取消预约
	 */
	private Boolean isCancel=false;
	/**
	 * 总价
	 */
	private BigDecimal amount;
	
	private WxOrder wxCore;
	
	
	@ManyToOne
    @JoinColumn(name="WX_ORDER_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public WxOrder getWxCore() {
		return wxCore;
	}

	public void setWxCore(WxOrder wxCore) {
		this.wxCore = wxCore;
	}

	@Column(name = "IS_CANCEL")
	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@ManyToOne
    @JoinColumn(name = "WEIXIN_ID",referencedColumnName="WEIXIN_ID",insertable=false,updatable=false)
    public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}
	
	@Column(name = "WEIXIN_ID")
	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	@Column(name = "PARKING_LOCK_CODE")
	public String getParkingLockCode() {
		return parkingLockCode;
	}

	public void setParkingLockCode(String parkingLockCode) {
		this.parkingLockCode = parkingLockCode;
	}

	/**
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "PARKING_LOCK_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public ParkingLock getParkingLock() {
		return this.parkingLock;
	}

	public void setParkingLock(ParkingLock value) {
		this.parkingLock = value;
	}

	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
