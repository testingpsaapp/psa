package com.psa.application.model;

public class Menu 
{
	private long id;
	private String mainMenu;
	private String[] subMenu;
	private String[] permittedAction;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}
	public String[] getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(String[] subMenu) {
		this.subMenu = subMenu;
	}
	public String[] getPermittedAction() {
		return permittedAction;
	}
	public void setPermittedAction(String[] permittedAction) {
		this.permittedAction = permittedAction;
	}
		
}
