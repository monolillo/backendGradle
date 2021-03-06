package com.hdsupply.xmi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.enums.StockNotificationEnum;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.NotificationDao;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public List<ProductCatalog> getNotifications(FilterNotification filter) {
		
		List<ProductCatalog> productCatalogList = new ArrayList<>();
		
		if (null == filter.getCritical() && null == filter.getStockNotification()) {
			
			getAllNotifications(filter, productCatalogList);
			
		} else if (null != filter.getCritical() && null == filter.getStockNotification()) {

			getNotificationsFilterCritical(filter, productCatalogList);
			
		} else if (null == filter.getCritical() && null != filter.getStockNotification()) {
			
			getNotificationOnlyStockNotification(filter, productCatalogList);
			
		} else if (null != filter.getCritical() && null != filter.getStockNotification()) {
			
			getNotificationCriticalStockNotification(filter, productCatalogList);
		}

		return productCatalogList;
	}
	
	private void setQuantity(List<ProductCatalog> productCatalogList, Integer siteId){
		
		for (ProductCatalog productCatalog : productCatalogList) {
			
			Integer qty = inventoryDao.getQuantity(productCatalog.getIdProduct(), siteId);
			
			productCatalog.setQuantity(qty);
		}
	}
		
	private void getAllNotifications(FilterNotification filter, List<ProductCatalog> productCatalogList){
		
		filter.setCritical(true);
		
		List<ProductCatalog> listOutOfStockTrue = notificationDao.getListOutOfStock(filter);
		
		if(!CollectionUtils.isEmpty(listOutOfStockTrue)) {
			
			setQuantity(listOutOfStockTrue, filter.getSiteId());
			
			productCatalogList.addAll(listOutOfStockTrue);
		}
		
		List<ProductCatalog> listLessThanMinTrue = notificationDao.getListLessThanMin(filter);
		
		if(!CollectionUtils.isEmpty(listLessThanMinTrue)) {
			
			setQuantity(listLessThanMinTrue, filter.getSiteId());
			
			productCatalogList.addAll(listLessThanMinTrue);
		}
		
		List<ProductCatalog> listMinThresholdTrue = notificationDao.getListMinThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMinThresholdTrue)) {
			
			setQuantity(listMinThresholdTrue, filter.getSiteId());
			
			productCatalogList.addAll(listMinThresholdTrue);
		}
		
		List<ProductCatalog> listMaxThresholdTrue = notificationDao.getListMaxThreshold(filter);
		
		filter.setCritical(false);
		
		List<ProductCatalog> listOutOfStockFalse = notificationDao.getListOutOfStock(filter);
		
		if(!CollectionUtils.isEmpty(listOutOfStockFalse)) {
			
			setQuantity(listOutOfStockFalse, filter.getSiteId());
			
			productCatalogList.addAll(listOutOfStockFalse);
		}
		
		List<ProductCatalog> listLessThanMinFalse = notificationDao.getListLessThanMin(filter);
		
		if(!CollectionUtils.isEmpty(listLessThanMinFalse)) {
			
			setQuantity(listLessThanMinFalse, filter.getSiteId());
			
			productCatalogList.addAll(listLessThanMinFalse);
		}
		
		List<ProductCatalog> listMinThresholdFalse = notificationDao.getListMinThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMinThresholdFalse)) {
			
			setQuantity(listMinThresholdFalse, filter.getSiteId());
			
			productCatalogList.addAll(listMinThresholdFalse);
		}
		
		List<ProductCatalog> listMaxThresholdFalse = notificationDao.getListMaxThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMaxThresholdTrue)) {
			
			setQuantity(listMaxThresholdTrue, filter.getSiteId());
			
			productCatalogList.addAll(listMaxThresholdTrue);
		}
		
		if(!CollectionUtils.isEmpty(listMaxThresholdFalse)) {
			
			setQuantity(listMaxThresholdFalse, filter.getSiteId());
			
			productCatalogList.addAll(listMaxThresholdFalse);
		}
	}
	
	private void getNotificationsFilterCritical(FilterNotification filter, List<ProductCatalog> productCatalogList){
		
		List<ProductCatalog> listOutOfStock = notificationDao.getListOutOfStock(filter);
		
		if(!CollectionUtils.isEmpty(listOutOfStock)) {
			
			setQuantity(listOutOfStock, filter.getSiteId());
			
			productCatalogList.addAll(listOutOfStock);
		}
		
		List<ProductCatalog> listLessThanMin = notificationDao.getListLessThanMin(filter);
		
		if(!CollectionUtils.isEmpty(listLessThanMin)) {
			
			setQuantity(listLessThanMin, filter.getSiteId());

			productCatalogList.addAll(listLessThanMin);
		}
		
		List<ProductCatalog> listMinThreshold = notificationDao.getListMinThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMinThreshold)) {
			
			setQuantity(listMinThreshold, filter.getSiteId());

			productCatalogList.addAll(listMinThreshold);
		}
		
		List<ProductCatalog> listMaxThreshold = notificationDao.getListMaxThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMaxThreshold)) {
			
			setQuantity(listMaxThreshold, filter.getSiteId());

			productCatalogList.addAll(listMaxThreshold);
		}
	}
	
	private void getNotificationOnlyStockNotification(FilterNotification filter, List<ProductCatalog> productCatalogList) {
		
		if (StockNotificationEnum.OUTOFSTOCK.equals(filter.getStockNotification())) {
			
			filter.setCritical(true);
			
			getNotificationsFilterOutOfStock(filter, productCatalogList);
			
			filter.setCritical(false);
			
			getNotificationsFilterOutOfStock(filter, productCatalogList);
			
		} else if (StockNotificationEnum.LOWINVENTORY.equals(filter.getStockNotification())) {
			
			filter.setCritical(true);
			
			getNotificationsFilterLowInventory(filter, productCatalogList);
			
			filter.setCritical(false);
			
			getNotificationsFilterLowInventory(filter, productCatalogList);
			
		} else if (StockNotificationEnum.MINTHRESHOLD.equals(filter.getStockNotification())) {
			
			filter.setCritical(true);
			
			getNotificationsFilterMinThreshold(filter, productCatalogList);
			
			filter.setCritical(false);
			
			getNotificationsFilterMinThreshold(filter, productCatalogList);
			
		} else if (StockNotificationEnum.INVENTORYEXCESS.equals(filter.getStockNotification())) {
			
			filter.setCritical(true);
			
			getNotificationsFilterMaxThreshold(filter, productCatalogList);
			
			filter.setCritical(false);
			
			getNotificationsFilterMaxThreshold(filter, productCatalogList);
		}
		
	}
	
	private void getNotificationCriticalStockNotification(FilterNotification filter, List<ProductCatalog> productCatalogList) {
		
		if (StockNotificationEnum.OUTOFSTOCK.equals(filter.getStockNotification())) {
			
			getNotificationsFilterOutOfStock(filter, productCatalogList);
			
		} else if (StockNotificationEnum.LOWINVENTORY.equals(filter.getStockNotification())) {
			
			getNotificationsFilterLowInventory(filter, productCatalogList);
			
		} else if (StockNotificationEnum.MINTHRESHOLD.equals(filter.getStockNotification())) {
			
			getNotificationsFilterMinThreshold(filter, productCatalogList);
			
		} else if (StockNotificationEnum.INVENTORYEXCESS.equals(filter.getStockNotification())) {
			
			getNotificationsFilterMaxThreshold(filter, productCatalogList);
		}
	}
	
	private void getNotificationsFilterOutOfStock(FilterNotification filter, List<ProductCatalog> productCatalogList){
		
		List<ProductCatalog> listOutOfStock = notificationDao.getListOutOfStock(filter);
		
		if(!CollectionUtils.isEmpty(listOutOfStock)) {
			
			setQuantity(listOutOfStock, filter.getSiteId());

			productCatalogList.addAll(listOutOfStock);
		}
	}
	
	private void getNotificationsFilterLowInventory(FilterNotification filter, List<ProductCatalog> productCatalogList){
		
		List<ProductCatalog> listLessThanMin = notificationDao.getListLessThanMin(filter);
		
		if(!CollectionUtils.isEmpty(listLessThanMin)) {
			
			setQuantity(listLessThanMin, filter.getSiteId());

			productCatalogList.addAll(listLessThanMin);
		}
	}
	
	private void getNotificationsFilterMinThreshold(FilterNotification filter, List<ProductCatalog> productCatalogList) {
		
		List<ProductCatalog> listMinThreshold = notificationDao.getListMinThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMinThreshold)) {
			
			setQuantity(listMinThreshold, filter.getSiteId());

			productCatalogList.addAll(listMinThreshold);
		}
	}
	
	private void getNotificationsFilterMaxThreshold(FilterNotification filter, List<ProductCatalog> productCatalogList) {
		
		List<ProductCatalog> listMaxThreshold = notificationDao.getListMaxThreshold(filter);
		
		if(!CollectionUtils.isEmpty(listMaxThreshold)) {
			
			setQuantity(listMaxThreshold, filter.getSiteId());

			productCatalogList.addAll(listMaxThreshold);
		}
	}
}
