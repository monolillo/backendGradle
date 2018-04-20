package com.hdsupply.xmi.domain;

import com.hdsupply.xmi.enums.StockNotificationEnum;

public class FilterNotification {
	
	private Integer siteId;
	private Boolean critical;
	private StockNotificationEnum stockNotification;

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

	public StockNotificationEnum getStockNotification() {
		return stockNotification;
	}

	public void setStockNotification(StockNotificationEnum stockNotification) {
		this.stockNotification = stockNotification;
	}
}
