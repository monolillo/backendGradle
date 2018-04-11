package com.hdsupply.xmi.domain;

public class FilterNotification {
	
	private Integer siteId;
	private Boolean critical;

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
