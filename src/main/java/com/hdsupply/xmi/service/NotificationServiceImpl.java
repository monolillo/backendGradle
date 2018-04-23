package com.hdsupply.xmi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.NotificationDao;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public List<ProductCatalog> getNotification(FilterNotification filter) {
		
		List<ProductCatalog> productCatalogList = new ArrayList<>();
		
		if (null == filter.getCritical()) {
			
			List<ProductCatalog> listOutOfStock = notificationDao.getListOutOfStock(filter);
			
			if(!CollectionUtils.isEmpty(listOutOfStock)) {
				
				productCatalogList.addAll(listOutOfStock);
			}
			
			List<ProductCatalog> listLessThanMin = notificationDao.getListLessThanMin(filter);
			
			if(!CollectionUtils.isEmpty(listLessThanMin)) {
				
				productCatalogList.addAll(listLessThanMin);
			}
			
			List<ProductCatalog> listMinThreshold = notificationDao.getListMinThreshold(filter);
			
			if(!CollectionUtils.isEmpty(listMinThreshold)) {
				
				productCatalogList.addAll(listMinThreshold);
			}
			
			List<ProductCatalog> listMaxThreshold = notificationDao.getListMaxThreshold(filter);
			
			if(!CollectionUtils.isEmpty(listMaxThreshold)) {
				
				productCatalogList.addAll(listMaxThreshold);
			}
			
			for (ProductCatalog productCatalog : productCatalogList) {
				
				Integer qty = inventoryDao.getQuantity(productCatalog.getIdProduct(), filter.getSiteId());
				
				productCatalog.setQuantity(qty);
			}
			
		} else if (null != filter.getCritical()) {

			List<ProductCatalog> listOutOfStockCritical = notificationDao.getListOutOfStockCritical(filter);
			
			if(!CollectionUtils.isEmpty(listOutOfStockCritical)) {
				
				productCatalogList.addAll(listOutOfStockCritical);
			}
			
			List<ProductCatalog> listLessThanMinCritical = notificationDao.getListLessThanMinCritical(filter);
			
			if(!CollectionUtils.isEmpty(listLessThanMinCritical)) {
				
				productCatalogList.addAll(listLessThanMinCritical);
			}
			
			List<ProductCatalog> listMinThresholdCritical = notificationDao.getListMinThresholdCritical(filter);
			
			if(!CollectionUtils.isEmpty(listMinThresholdCritical)) {
				
				productCatalogList.addAll(listMinThresholdCritical);
			}
			
			List<ProductCatalog> listMaxThresholdCritical = notificationDao.getListMaxThresholdCritical(filter);
			
			if(!CollectionUtils.isEmpty(listMaxThresholdCritical)) {
				
				productCatalogList.addAll(listMaxThresholdCritical);
			}
			
			for (ProductCatalog productCatalog : productCatalogList) {
				
				Integer qty = inventoryDao.getQuantity(productCatalog.getIdProduct(), filter.getSiteId());
				
				productCatalog.setQuantity(qty);
			}
		} 

		return productCatalogList;
	}

}
