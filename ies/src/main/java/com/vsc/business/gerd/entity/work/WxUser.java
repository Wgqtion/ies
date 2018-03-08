package com.vsc.business.gerd.entity.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.business.core.entity.security.User;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 微信小程序用户实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "wx_user")
public class WxUser extends IdEntity {

    private String weixinId;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 车牌号
     */
    private String carNumber;
    private Date createDate;
    private Date updateDate;
    private User updateUser;
    /**
	 * 是否删除
	 */
	private Boolean isDelete=false;
    /**
     * 微信ID
     *
     * @return
     */
    @Column(name = "WEIXIN_ID")
    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    @Column(name = "NAME")
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "CREATE_TIME")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "UPDATE_TIME")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

  
	/**
     * @return
     */
    @Column(name = "CAR_NUMBER")
    public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	/**
     * @return
     */
    @Column(name = "TELEPHONE")
    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@ManyToOne
    @JoinColumn(name = "UPDATE_USER")
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	@Column(name = "IS_DELETE")
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
