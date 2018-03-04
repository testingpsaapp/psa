package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User 
{
	private long id;
	private String soeId;
	private String firstName;
	private String lastName;
	private String[] region;
	private String lob;
	private String[] applications;
	private String password;
	private String confirmPassword;
	
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String[] getRegion() {
		return region;
	}
	public void setRegion(String[] region) {
		this.region = region;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String[] getApplications() {
		return applications;
	}
	public void setApplications(String[] applications) {
		this.applications = applications;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
