package com.hdsupply.xmi.domain;

import javax.validation.constraints.NotNull;

public class Inventory {
	
	Integer quantity;
	Integer checkedOutQuantity;
	Integer shopId;
	Integer productId;
	Integer locationId;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getCheckedOutQuantity() {
		return checkedOutQuantity;
	}
	public void setCheckedOutQuantity(Integer checkedOutQuantity) {
		this.checkedOutQuantity = checkedOutQuantity;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
}
