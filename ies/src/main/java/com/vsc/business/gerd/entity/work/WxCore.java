package com.vsc.business.gerd.entity.work;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	 * 是否免费
	 */
	private Boolean isFree=false;
	
	/**
	 * 费用
	 */
	private BigDecimal amount;
	
	private WxOrder wxOrder;
	
	@ManyToOne
    @JoinColumn(name="WX_ORDER_CODE",referencedColumnName="CODE",insertable=false,updatable=false)
	public WxOrder getWxOrder() {
		return wxOrder;
	}

	public void setWxOrder(WxOrder wxOrder) {
		this.wxOrder = wxOrder;
	}

	@Column(name = "IS_CANCEL")
	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	@Column(name = "IS_FREE")
	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
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
	
	/**
	 * 返回类型Str
	 */
	@Transient
	public String getTypeStr(){
		if(type==null){
			return "";
		}else if(type==1){
			return "预约";
		}else if(type==2){
			return "停车";
		}
		return "";
	}
}
