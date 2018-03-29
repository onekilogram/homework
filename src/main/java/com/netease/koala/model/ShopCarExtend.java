package com.netease.koala.model;

public class ShopCarExtend extends ShopCar {
	
	private String icon;

	private String itemname;

	private String title;

	private String description;

	private int itemstatus;

	private Float pirce;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
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

	public int getItemstatus() {
		return itemstatus;
	}

	public void setItemstatus(int itemstatus) {
		this.itemstatus = itemstatus;
	}

	public Float getPirce() {
		return pirce;
	}

	public void setPirce(Float pirce) {
		this.pirce = pirce;
	}

}
