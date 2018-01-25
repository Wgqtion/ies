package com.vsc.business.core.entity.sys;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

 
/**
 * 数据字典
 * @author 陈婷
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX+"dict")
public class Dict extends IdEntity { 
     private java.lang.String type;
     private java.lang.String name;
     private java.lang.String code;
     private java.lang.String description;
     private java.lang.Integer sort;
	 

	public java.lang.String getType() {
		return type;
	}

	@NotBlank
	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getName() {
		return name;
	}

	@NotBlank
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getCode() {
		return code;
	}
	
	@NotBlank
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getDescription() {
		return description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getSort() {
		return sort;
	}

	@NotBlank
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}	 
    
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}

