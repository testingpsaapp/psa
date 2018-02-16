package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comm_dlist_config")
public class CommDlistConfig {

	private long id;
	private String commType;
	private String incType;
	private String incPriority;
	private String impactedCtry;
	private String impactedLob;
	private String toIds;
	private String cc;
	private String bcc;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCommType() {
		return commType;
	}
	public void setCommType(String commType) {
		this.commType = commType;
	}
	public String getIncType() {
		return incType;
	}
	public void setIncType(String incType) {
		this.incType = incType;
	}
	public String getIncPriority() {
		return incPriority;
	}
	public void setIncPriority(String incPriority) {
		this.incPriority = incPriority;
	}
	public String getImpactedCtry() {
		return impactedCtry;
	}
	public void setImpactedCtry(String impactedCtry) {
		this.impactedCtry = impactedCtry;
	}
	public String getImpactedLob() {
		return impactedLob;
	}
	public void setImpactedLob(String impactedLob) {
		this.impactedLob = impactedLob;
	}
	public String getToIds() {
		return toIds;
	}
	public void setToIds(String toIds) {
		this.toIds = toIds;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	
}
