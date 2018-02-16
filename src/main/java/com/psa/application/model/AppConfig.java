package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_config")
public class AppConfig {

	private long id;
	private String appName;
	private String lob;
	private String dList;
	private String snowDlist;
	private String lobLead;
	private String psm;
	private String pssm;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String getdList() {
		return dList;
	}
	public void setdList(String dList) {
		this.dList = dList;
	}
	public String getSnowDlist() {
		return snowDlist;
	}
	public void setSnowDlist(String snowDlist) {
		this.snowDlist = snowDlist;
	}
	public String getLobLead() {
		return lobLead;
	}
	public void setLobLead(String lobLead) {
		this.lobLead = lobLead;
	}
	public String getPsm() {
		return psm;
	}
	public void setPsm(String psm) {
		this.psm = psm;
	}
	public String getPssm() {
		return pssm;
	}
	public void setPssm(String pssm) {
		this.pssm = pssm;
	}
	
	
	
	
}
