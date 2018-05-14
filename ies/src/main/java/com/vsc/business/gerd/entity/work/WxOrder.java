package com.vsc.business.gerd.entity.work;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 小程序订单 实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "wx_order")
public class WxOrder extends IdEntity{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1258441321485479388L;
	private WxUser wxUser;
	private String weixinId;

	private String code;
	
	/**
	 * 开始时间
	 */
	private Date createTime;
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 状态，0未支付,1已支付
	 */
	private Integer status=Integer.valueOf(0);
	
	private List<WxCore> wxCores=new ArrayList<WxCore>();
	
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

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "PAY_TIME")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@OneToMany
	@JoinColumn(name = "WX_ORDER_CODE",referencedColumnName="CODE")
	public List<WxCore> getWxCores() {
		return wxCores;
	}

	public void setWxCores(List<WxCore> wxCores) {
		this.wxCores = wxCores;
	}
	
	/**
	 * 返回总额 
	 */
	@Transient
	public BigDecimal getTotalFee(){
		BigDecimal totalFee=new BigDecimal(0);
		if(wxCores!=null){
			for (WxCore wxCore : wxCores) {
				totalFee=totalFee.add(wxCore.getAmount());
			}
		}
		return totalFee;
	}
}
