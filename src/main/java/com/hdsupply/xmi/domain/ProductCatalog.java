package com.hdsupply.xmi.domain;

import java.math.BigDecimal;

public class ProductCatalog {
	
	private String name;
	private Integer itemNumber;
	private Integer idProduct;
	private BigDecimal price;
	private String imageUrl;
	private Integer min;
	private Integer max;
	private Integer quantity;
	private Boolean critical;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Boolean getCritical() {
		return critical;
	}
	public void setCritical(Boolean critical) {
		this.critical = critical;
	}

}
