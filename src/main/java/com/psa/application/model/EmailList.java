package com.psa.application.model;


public class EmailList 
{
	private String[] listOfToMailId;
	private String[] listOfCCMailId;
	private String[] listOfBCCMailId;
	
	public String[] getListOfToMailId() {
		return listOfToMailId;
	}
	public void setListOfToMailId(String[] listOfToMailId) {
		this.listOfToMailId = listOfToMailId;
	}
	public String[] getListOfCCMailId() {
		return listOfCCMailId;
	}
	public void setListOfCCMailId(String[] listOfCCMailId) {
		this.listOfCCMailId = listOfCCMailId;
	}
	public String[] getListOfBCCMailId() {
		return listOfBCCMailId;
	}
	public void setListOfBCCMailId(String[] listOfBCCMailId) {
		this.listOfBCCMailId = listOfBCCMailId;
	}
	
	

}
