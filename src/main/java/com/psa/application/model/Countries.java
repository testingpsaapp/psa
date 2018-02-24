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
	private String techHead;
	private String ontHead;
	private String cbmHead;
	
	
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
	public String getTechHead() {
		return techHead;
	}
	public void setTechHead(String techHead) {
		this.techHead = techHead;
	}
	public String getOntHead() {
		return ontHead;
	}
	public void setOntHead(String ontHead) {
		this.ontHead = ontHead;
	}
	public String getCbmHead() {
		return cbmHead;
	}
	public void setCbmHead(String cbmHead) {
		this.cbmHead = cbmHead;
	}
}
