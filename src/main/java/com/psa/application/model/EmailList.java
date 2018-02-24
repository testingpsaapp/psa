package com.psa.application.model;

import java.util.List;

public class EmailList 
{
	private List<String> listOfToMailId;
	private List<String> listOfCCMailId;
	private List<String> listOfBCCMailId;
	
	public List<String> getListOfToMailId() {
		return listOfToMailId;
	}
	public void setListOfToMailId(List<String> listOfToMailId) {
		this.listOfToMailId = listOfToMailId;
	}
	public List<String> getListOfCCMailId() {
		return listOfCCMailId;
	}
	public void setListOfCCMailId(List<String> listOfCCMailId) {
		this.listOfCCMailId = listOfCCMailId;
	}
	public List<String> getListOfBCCMailId() {
		return listOfBCCMailId;
	}
	public void setListOfBCCMailId(List<String> listOfBCCMailId) {
		this.listOfBCCMailId = listOfBCCMailId;
	}
	
	

}
