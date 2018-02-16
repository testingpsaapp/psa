package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cla_access_config")
public class CLAAcessConfig {

	private long id;
	private String soeId;
	private String accessType;
	private String accessRole;
	private String accessData;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSoeId() {
		return soeId;
	}
	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getAccessRole() {
		return accessRole;
	}
	public void setAccessRole(String accessRole) {
		this.accessRole = accessRole;
	}
	public String getAccessData() {
		return accessData;
	}
	public void setAccessData(String accessData) {
		this.accessData = accessData;
	}
	
	
}
