package com.hi5.ff.entity;

import java.util.Date;

public class Item {

	private int itemId;
	private String itemName;
	private double itemPrice;
	private String itemRemark;
	private Date itemAddDate;
	private int categoryId;
	private int userId;
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public Date getItemAddDate() {
		return itemAddDate;
	}

	public void setItemAddDate(Date itemAddDate) {
		this.itemAddDate = itemAddDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}








}
