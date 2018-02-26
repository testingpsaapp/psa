package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
@Table(name="change_comm")
public class ChangeComm {
	
	private long id;
	private String changNum;
	private String changDate;
	private String changStartTime;
	private String changeEndTime;
	private String[] impactedPp;
	private String[] impactedCountry;
	private String[] sanityScope;
	private String impactedRegion;
	private String changeTitle;
	private String changeDesc;
	private String changeOwner;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public String getChangDate() {
		return changDate;
	}
	public void setChangDate(String changDate) {
		this.changDate = changDate;
	}
	public String getChangStartTime() {
		return changStartTime;
	}
	public void setChangStartTime(String changStartTime) {
		this.changStartTime = changStartTime;
	}
	public String getChangeEndTime() {
		return changeEndTime;
	}
	public void setChangeEndTime(String changeEndTime) {
		this.changeEndTime = changeEndTime;
	}
	public String[] getImpactedPp() {
		return impactedPp;
	}
	public void setImpactedPp(String[] impactedPp) {
		this.impactedPp = impactedPp;
	}
	public String[] getImpactedCountry() {
		return impactedCountry;
	}
	public void setImpactedCountry(String[] impactedCountry) {
		this.impactedCountry = impactedCountry;
	}
	public String[] getSanityScope() {
		return sanityScope;
	}
	public void setSanityScope(String[] sanityScope) {
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
	
	public String getChangeOwner() {
		return changeOwner;
	}
	public void setChangeOwner(String changeOwner) {
		this.changeOwner = changeOwner;
	}
}
