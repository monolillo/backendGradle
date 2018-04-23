package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;

public interface NotificationDao {
	
	public List<ProductCatalog> getListOutOfStock(FilterNotification filter);
	
	public List<ProductCatalog> getListLessThanMin(FilterNotification filter);
	
	public List<ProductCatalog> getListMinThreshold(FilterNotification filter);
	
	public List<ProductCatalog> getListMaxThreshold(FilterNotification filter);
	
	public List<ProductCatalog> getListOutOfStockCritical(FilterNotification filter);
	
	public List<ProductCatalog> getListLessThanMinCritical(FilterNotification filter);
	
	public List<ProductCatalog> getListMinThresholdCritical(FilterNotification filter);
	
	public List<ProductCatalog> getListMaxThresholdCritical(FilterNotification filter);

}
