package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Countries {

	private long id;
	private String countryCode;
	private String countryName;
	private String region;
	private String countryDlist;
	
	
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getCountryDlist() {
		return countryDlist;
	}
	public void setCountryDlist(String countryDlist) {
		this.countryDlist = countryDlist;
	}
}
