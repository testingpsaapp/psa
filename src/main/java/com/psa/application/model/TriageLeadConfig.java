package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="triage_lead_config")
public class TriageLeadConfig 
{
	private long id;
	private String tlSoeId;
	private String region;
	private String country;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTlSoeId() {
		return tlSoeId;
	}
	public void setTlSoeId(String tlSoeId) {
		this.tlSoeId = tlSoeId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
