package com.jingcheng.ordermeal.bean;

import java.io.Serializable;

import android.R.bool;

public class Dish implements Serializable{
	private int dishId;
	private String dishName;
	private String details;
	private float price;
	private int image;
	private boolean isNew;
	private boolean isHot;
	
	/**
	 * 
	 * @param int dishId  菜品编号
	 * @param String dishName 菜品名称
	 * @param String details 详情介绍
	 * @param float price 价格
	 * @param int image 图片
	 * @param boolean isNew 是否为新品
	 * @param boolean isHot 是否热销
	 */
	public Dish(int dishId, String dishName, String details, float price,
			int image, int count, boolean isNew, boolean isHot) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.details = details;
		this.price = price;
		this.image = image;
		this.isNew = isNew;
		this.isHot = isHot;
	}
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public boolean isHot() {
		return isHot;
	}
	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
	
	
}
