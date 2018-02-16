package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lob_config")
public class LobConfig {

	private long id;
	private String lob;
	private String app;
	private String role;
	private String soeId;
	private String configData;
	
	public String getSoeId() {
		return soeId;
	}
	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}
	
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConfigData() {
		return configData;
	}
	public void setConfigData(String configData) {
		this.configData = configData;
	}
	
	
	
}
