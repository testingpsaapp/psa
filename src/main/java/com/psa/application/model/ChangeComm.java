package com.psa.application.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="change_comm")
public class ChangeComm {
	
	private long id;
	private String changNum;
	private Date changDate;
	private Date changStartTime;
	private Date changeEndTime;
	private String impactedPp;
	private String impactedCountry;
	private String sanityScope;
	private String impactedRegion;
	private String changeTitle;
	private String changeDesc;
	
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getChangNum() {
		return changNum;
	}
	public void setChangNum(String changNum) {
		this.changNum = changNum;
	}
	public Date getChangDate() {
		return changDate;
	}
	public void setChangDate(Date changDate) {
		this.changDate = changDate;
	}
	public Date getChangStartTime() {
		return changStartTime;
	}
	public void setChangStartTime(Date changStartTime) {
		this.changStartTime = changStartTime;
	}
	public Date getChangeEndTime() {
		return changeEndTime;
	}
	public void setChangeEndTime(Date changeEndTime) {
		this.changeEndTime = changeEndTime;
	}
	public String getImpactedPp() {
		return impactedPp;
	}
	public void setImpactedPp(String impactedPp) {
		this.impactedPp = impactedPp;
	}
	public String getImpactedCountry() {
		return impactedCountry;
	}
	public void setImpactedCountry(String impactedCountry) {
		this.impactedCountry = impactedCountry;
	}
	public String getSanityScope() {
		return sanityScope;
	}
	public void setSanityScope(String sanityScope) {
		this.sanityScope = sanityScope;
	}
	public String getImpactedRegion() {
		return impactedRegion;
	}
	public void setImpactedRegion(String impactedRegion) {
		this.impactedRegion = impactedRegion;
	}
	public String getChangeTitle() {
		return changeTitle;
	}
	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}
	public String getChangeDesc() {
		return changeDesc;
	}
	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}
}
