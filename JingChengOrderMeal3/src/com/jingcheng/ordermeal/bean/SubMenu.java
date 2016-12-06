package com.jingcheng.ordermeal.bean;

public class SubMenu {
	private int icon;
	private String subMenuName;
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getSubMenuName() {
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
	public SubMenu(int icon, String subMenuName) {
		super();
		this.icon = icon;
		this.subMenuName = subMenuName;
	}
	
	
}
