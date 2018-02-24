package com.psa.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="daily_inc_act")
public class DailyIncAct {
	
	private long id;
	private String incNum;
	private String date;
	private String oldPriority;
	private String newPriority;
	private String impact;
	private String actTaken;
	private String actCategory;
	private String ctry;
	private String tlSoeid;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIncNum() {
		return incNum;
	}
	public void setIncNum(String incNum) {
		this.incNum = incNum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOldPriority() {
		return oldPriority;
	}
	public void setOldPriority(String oldPriority) {
		this.oldPriority = oldPriority;
	}
	public String getNewPriority() {
		return newPriority;
	}
	public void setNewPriority(String newPriority) {
		this.newPriority = newPriority;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getActTaken() {
		return actTaken;
	}
	public void setActTaken(String actTaken) {
		this.actTaken = actTaken;
	}
	public String getActCategory() {
		return actCategory;
	}
	public void setActCategory(String actCategory) {
		this.actCategory = actCategory;
	}
	public String getCtry() {
		return ctry;
	}
	public void setCtry(String ctry) {
		this.ctry = ctry;
	}
	public String getTlSoeid() {
		return tlSoeid;
	}
	public void setTlSoeid(String tlSoeid) {
		this.tlSoeid = tlSoeid;
	}
	
	

}
