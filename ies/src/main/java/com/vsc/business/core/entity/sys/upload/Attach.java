package com.vsc.business.core.entity.sys.upload;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.business.core.entity.security.User;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;
import com.vsc.util.CoreUtils;

/**
 * 系统附件
 * @author 付翀
 * @version 2013-01-26
 */

@Entity
@Table(name = Constants.TABLE_PREFIX+"attach")
public class Attach extends IdEntity {
	//附件名称
	private java.lang.String name;
	//创建时间
	private java.util.Date createTime = new Date();
	//附件的上传路径
	private java.lang.String urlPath;
	//文件类型
	private java.lang.String fileType;
	//附件大小
	private java.lang.Long fileSize;

	private java.lang.String uploadSessionId;

	private java.lang.String fileKey;

	private User user;

	/**
	 * 
	 * @return 文件类型
	 */
	@Column(name = "file_type")
	public java.lang.String getFileType() {
		return fileType;
	}

	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 
	 * @return 附件大小
	 */
	@Column(name = "file_size")
	public java.lang.Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(java.lang.Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 
	 * @return 附件名称
	 */
	@Column(name = "name")
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	/**
	 * 
	 * @return 创建时间
	 */
	@Column(name = "create_time")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	/**
	 * 
	 * @return 附件的存放路径，不包括文件名部分
	 */
	@Column(name = "url_path")
	public java.lang.String getUrlPath() {
		return this.urlPath;
	}

	public void setUrlPath(java.lang.String value) {
		this.urlPath = value;
	}

	@Column(name = "upload_session_id")
	public java.lang.String getUploadSessionId() {
		return uploadSessionId;
	}

	public void setUploadSessionId(java.lang.String uploadSessionId) {
		this.uploadSessionId = uploadSessionId;
	}

	/**
	 * 无规则的文件唯一标识
	 * @return
	 */
	@Column(name = "file_key", unique = true)
	public java.lang.String getFileKey() {
		return fileKey;
	}

	public void setFileKey(java.lang.String fileKey) {
		this.fileKey = fileKey;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取当前附件的直接下载链接，当附件直接存放在web可下载目录是有效
	 * @return
	 */
	@Transient
	public String getDownloadPath() {
		return this.getUrlPath() + this.getFileKey() + Constants.SPT + this.getName();
	}
	
	/**
	 * 范围文件大小的字符串表示形式 KB MB GB
	 */
	@Transient
	public String getDisplaySize(){
		return FileUtils.byteCountToDisplaySize(this.fileSize);
	}
	
	/**
	 * 判断附件是否属于图片
	 * @return
	 */
	@Transient
	public boolean getIsImage(){ 
		return  CoreUtils.IsImage(this.fileType);
	}
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
