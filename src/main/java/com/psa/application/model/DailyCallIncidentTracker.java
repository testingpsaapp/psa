package com.psa.application.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="daily_call_incident_tracker")
public class DailyCallIncidentTracker {

	//@Column(name="id")
	private long id;
	
	//@Column(name="inc_num")
	private String incNum;
	
	//@Column(name="impacting_reg")
	private String impactingReg;
	
	//@Column(name="impacting_ctry")
	private String impactingCtry;
	
	//@Column(name="highlighted_by")
	private String highlightedBy;
	
	//@Column(name="highlighted_date")
	private String highlightedDate;
	
	//@Column(name="closed_date")
	private String closedDate;
	
	//@Column(name="impacting_dept")
	private String impactingDept;
	
	//@Column(name="reason_of_criticality")
	private String reasonOfCriticality;
	
	//@Column(name="action_item")
	private String actionItem;
	
	//@Column(name="inc_state")
	private String incState;
	
	//@Column(name="pp_involved")
	private String[] ppInvolved;
	
	//@Column(name="curr_owner")
	private String currOwner;
	
	//@Column(name="curr_owner_app")
	private String currOwnerApp;
	
	//@Column(name="incident_priority")
	private String incidentPriority;
	
	//@Column(name="impact")
	private String impact;
	
	//@Column(name="update_comm")
	private String updateComm;
	
	
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
	public String getHighlightedDate() {
		return highlightedDate;
	}
	public void setHighlightedDate(String highlightedDate) {
		this.highlightedDate = highlightedDate;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
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
	public String[] getPpInvolved() {
		return ppInvolved;
	}
	public void setPpInvolved(String[] ppInvolved) {
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
	public String getUpdateComm() {
		return updateComm;
	}
	public void setUpdateComm(String update_comm) {
		this.updateComm = update_comm;
	}

}
