package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="access_module")
public class AccessModule {
	private long id;
	private String mainModule;
	private String subModule;
	private String role;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMainModule() {
		return mainModule;
	}
	public void setMainModule(String mainModule) {
		this.mainModule = mainModule;
	}
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
