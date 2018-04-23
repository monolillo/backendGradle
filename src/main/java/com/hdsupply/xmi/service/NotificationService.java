package com.hdsupply.xmi.service;

import java.util.List;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;

public interface NotificationService {
	
	public List<ProductCatalog> getNotifications(FilterNotification filter);

}
