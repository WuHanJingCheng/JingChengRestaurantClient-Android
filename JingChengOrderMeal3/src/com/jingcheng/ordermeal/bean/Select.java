package com.jingcheng.ordermeal.bean;

public class Select {
	private int dishId;
	private String dishName;
	private String details;
	private float price;
	private int image;
	private boolean isNew;
	private boolean isHot;
	private int count;
	
	/**
	 * 
	 * @param int dishId  菜品编号
	 * @param String dishName 菜品名称
	 * @param String details 详情介绍
	 * @param float price 价格
	 * @param int image 图片
	 * @param boolean isNew 是否为新品
	 * @param boolean isHot 是否热销
	 * @param int count 以选择的数量
	 * 
	 */
	public Select(int dishId, String dishName, String details, float price,
			int image, boolean isNew, boolean isHot, int count) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.details = details;
		this.price = price;
		this.image = image;
		this.isNew = isNew;
		this.isHot = isHot;
		this.count = count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
