package com.psa.application.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="brief_pap_pub")
public class BriefPapPub 
{
	private long id;
	private String mimNum;
	private Date mimDate;
	private Date mimStartDate;
	private Date mimEndDate;
	private String impactedLob;
	private String title;
	private String description;
	private String impactedCountry;
	private String impactedDepartment;
	private String impact;
	private String fix;
	private String preventiveAct;
	private String chronology;
	private String preparedBy;
	private String firstReviewer;
	private String secondReviewer;
	private String status;
	private String impactedRegion;
	private String impactedSector;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMimNum() {
		return mimNum;
	}
	public void setMimNum(String mimNum) {
		this.mimNum = mimNum;
	}
	public Date getMimDate() {
		return mimDate;
	}
	public void setMimDate(Date mimDate) {
		this.mimDate = mimDate;
	}
	public Date getMimStartDate() {
		return mimStartDate;
	}
	public void setMimStartDate(Date mimStartDate) {
		this.mimStartDate = mimStartDate;
	}
	public Date getMimEndDate() {
		return mimEndDate;
	}
	public void setMimEndDate(Date mimEndDate) {
		this.mimEndDate = mimEndDate;
	}
	public String getImpactedLob() {
		return impactedLob;
	}
	public void setImpactedLob(String impactedLob) {
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
	public String getImpactedCountry() {
		return impactedCountry;
	}
	public void setImpactedCountry(String impactedCountry) {
		this.impactedCountry = impactedCountry;
	}
	public String getImpactedDepartment() {
		return impactedDepartment;
	}
	public void setImpactedDepartment(String impactedDepartment) {
		this.impactedDepartment = impactedDepartment;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	public String getPreventiveAct() {
		return preventiveAct;
	}
	public void setPreventiveAct(String preventiveAct) {
		this.preventiveAct = preventiveAct;
	}
	public String getChronology() {
		return chronology;
	}
	public void setChronology(String chronology) {
		this.chronology = chronology;
	}
	public String getPreparedBy() {
		return preparedBy;
	}
	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	public String getFirstReviewer() {
		return firstReviewer;
	}
	public void setFirstReviewer(String firstReviewer) {
		this.firstReviewer = firstReviewer;
	}
	public String getSecondReviewer() {
		return secondReviewer;
	}
	public void setSecondReviewer(String secondReviewer) {
		this.secondReviewer = secondReviewer;
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
	public String getImpactedSector() {
		return impactedSector;
	}
	public void setImpactedSector(String impactedSector) {
		this.impactedSector = impactedSector;
	}
	
	
}
