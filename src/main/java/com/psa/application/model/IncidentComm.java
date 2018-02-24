package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="incident_comm")
public class IncidentComm {

	private long id;
	private String incidentNum;
	private String incidentSeverity;
	private String commTyp;
	private String incidentDate;
	private String impactStartTime;
	private String impactEndTime;
	private String[] impactedLob;
	private String title;
	private String description;
	private String[] impactedCountry;
	private String impactedDepartment;
	private String[] impact;
	private String fix;
	private String preparedBy;
	private String reviewedBy;
	private String status;
	private String impactedRegion;
	private String[] impactedSector;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIncidentNum() {
		return incidentNum;
	}
	public void setIncidentNum(String incidentNum) {
		this.incidentNum = incidentNum;
	}
	public String getIncidentSeverity() {
		return incidentSeverity;
	}
	public void setIncidentSeverity(String incidentSeverity) {
		this.incidentSeverity = incidentSeverity;
	}
	public String getCommTyp() {
		return commTyp;
	}
	public void setCommTyp(String commTyp) {
		this.commTyp = commTyp;
	}
	public String getIncidentDate() {
		return incidentDate;
	}
	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}
	public String getImpactStartTime() {
		return impactStartTime;
	}
	public void setImpactStartTime(String impactStartTime) {
		this.impactStartTime = impactStartTime;
	}
	public String getImpactEndTime() {
		return impactEndTime;
	}
	public void setImpactEndTime(String impactEndTime) {
		this.impactEndTime = impactEndTime;
	}
	public String[] getImpactedLob() {
		return impactedLob;
	}
	public void setImpactedLob(String[] impactedLob) {
		this.impactedLob = impactedLob;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getImpactedCountry() {
		return impactedCountry;
	}
	public void setImpactedCountry(String[] impactedCountry) {
		this.impactedCountry = impactedCountry;
	}
	public String getImpactedDepartment() {
		return impactedDepartment;
	}
	public void setImpactedDepartment(String impactedDepartment) {
		this.impactedDepartment = impactedDepartment;
	}
	public String[] getImpact() {
		return impact;
	}
	public void setImpact(String[] impact) {
		this.impact = impact;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	public String getPreparedBy() {
		return preparedBy;
	}
	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	public String getReviewedBy() {
		return reviewedBy;
	}
	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImpactedRegion() {
		return impactedRegion;
	}
	public void setImpactedRegion(String impactedRegion) {
		this.impactedRegion = impactedRegion;
	}
	public String[] getImpactedSector() {
		return impactedSector;
	}
	public void setImpactedSector(String[] impactedSector) {
		this.impactedSector = impactedSector;
	}
	
	

}
