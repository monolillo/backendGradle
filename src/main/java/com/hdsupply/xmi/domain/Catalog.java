package com.hdsupply.xmi.domain;

public class Catalog {

	private Integer productId;
	private Integer min;
	private Integer max;
	private Integer siteId;
	private Boolean critical;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Boolean getCritical() {
		return critical;
	}
	public void setCritical(Boolean critical) {
		this.critical = critical;
	}
	
	
	
	
}
