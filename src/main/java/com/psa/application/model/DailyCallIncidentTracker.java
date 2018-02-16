package com.psa.application.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="daily_call_incident_tracker")
public class DailyCallIncidentTracker {

	private long id;
	private String incNum;
	private String impactingReg;
	private String impactingCtry;
	private String highlightedBy;
	private Date highlightedDate;
	private Date closedDate;
	private String impactingDept;
	private String reasonOfCriticality;
	private String actionItem;
	private String incState;
	private String ppInvolved;
	private String currOwner;
	private String currOwnerApp;
	private String incidentPriority;
	private String impact;
	
	
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
	public String getImpactingReg() {
		return impactingReg;
	}
	public void setImpactingReg(String impactingReg) {
		this.impactingReg = impactingReg;
	}
	public String getImpactingCtry() {
		return impactingCtry;
	}
	public void setImpactingCtry(String impactingCtry) {
		this.impactingCtry = impactingCtry;
	}
	public String getHighlightedBy() {
		return highlightedBy;
	}
	public void setHighlightedBy(String highlightedBy) {
		this.highlightedBy = highlightedBy;
	}
	public Date getHighlightedDate() {
		return highlightedDate;
	}
	public void setHighlightedDate(Date highlightedDate) {
		this.highlightedDate = highlightedDate;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getImpactingDept() {
		return impactingDept;
	}
	public void setImpactingDept(String impactingDept) {
		this.impactingDept = impactingDept;
	}
	public String getReasonOfCriticality() {
		return reasonOfCriticality;
	}
	public void setReasonOfCriticality(String reasonOfCriticality) {
		this.reasonOfCriticality = reasonOfCriticality;
	}
	public String getActionItem() {
		return actionItem;
	}
	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}
	public String getIncState() {
		return incState;
	}
	public void setIncState(String incState) {
		this.incState = incState;
	}
	public String getPpInvolved() {
		return ppInvolved;
	}
	public void setPpInvolved(String ppInvolved) {
		this.ppInvolved = ppInvolved;
	}
	public String getCurrOwner() {
		return currOwner;
	}
	public void setCurrOwner(String currOwner) {
		this.currOwner = currOwner;
	}
	public String getCurrOwnerApp() {
		return currOwnerApp;
	}
	public void setCurrOwnerApp(String currOwnerApp) {
		this.currOwnerApp = currOwnerApp;
	}
	
	public String getIncidentPriority() {
		return incidentPriority;
	}
	public void setIncidentPriority(String incidentPriority) {
		this.incidentPriority = incidentPriority;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	

}
