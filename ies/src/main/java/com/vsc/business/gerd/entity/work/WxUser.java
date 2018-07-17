package com.vsc.business.gerd.entity.work;

import java.io.Serializable;
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
 * 小程序用户实体类
 * @author XiangXiaoLin
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "wx_user")
public class WxUser extends IdEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3982556298642739617L;
	private String weixinId;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机号
     */
    private String telphone;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 车牌号
     */
    private String plateNo;
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
	
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

  
	@Column(name = "TELPHONE")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "PLATE_NO")
	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
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

	@Column(name = "SEX")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
