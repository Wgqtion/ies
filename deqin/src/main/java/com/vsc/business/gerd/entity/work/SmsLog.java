package com.vsc.business.gerd.entity.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.IdEntity;

/**
 * 
 * @author jerry
 *
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "sms_log")
public class SmsLog extends IdEntity {

	private String patientName;
	private java.util.Date sendTime;
	private Boolean sendState;
	private String content;
	private String results;

	/**
	 * @return
	 */
	@Column(name = "patient_name")
	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String value) {
		this.patientName = value;
	}

	/**
	 * @return
	 */
	@Column(name = "send_time")
	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}

	/**
	 * @return
	 */
	@Column(name = "send_state")
	public Boolean getSendState() {
		return this.sendState;
	}

	public void setSendState(Boolean value) {
		this.sendState = value;
	}

	/**
	 * @return
	 */
	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * @return
	 */
	@Column(name = "results")
	public String getResults() {
		return this.results;
	}

	public void setResults(String value) {
		this.results = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
