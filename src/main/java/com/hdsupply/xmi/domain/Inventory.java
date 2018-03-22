package com.hdsupply.xmi.domain;

public class Inventory {
	
	Integer qty;
	Integer checkedOutQty;
	Integer shopId;
	Integer productId;
	Integer locationId;
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getCheckedOutQty() {
		return checkedOutQty;
	}
	public void setCheckedOutQty(Integer checkedOutQty) {
		this.checkedOutQty = checkedOutQty;
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
