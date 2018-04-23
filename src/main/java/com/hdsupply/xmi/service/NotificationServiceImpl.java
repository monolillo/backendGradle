package com.hdsupply.xmi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			
			productCatalogList.addAll(listOutOfStock);
			
			List<ProductCatalog> listLessThanMin = notificationDao.getListLessThanMin(filter);
			
			productCatalogList.addAll(listLessThanMin);
			
			List<ProductCatalog> listMinThreshold = notificationDao.getListMinThreshold(filter);
			
			productCatalogList.addAll(listMinThreshold);
			
			for (ProductCatalog productCatalog : productCatalogList) {
				
				Integer qty = inventoryDao.getQuantity(productCatalog.getIdProduct(), filter.getSiteId());
				
				productCatalog.setQuantity(qty);
				
			}
			
		} else if (filter.getCritical()) {
			//solo critico
		} else if (!filter.getCritical()) {
			//solo no critico
		}

		return productCatalogList;
	}

}
