package com.hdsupply.xmi.service;

import java.util.List;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;

public class NotificationServiceImpl implements NotificationService{

	@Override
	public List<ProductCatalog> getNotification(FilterNotification filter) {
		
		if (null == filter.getCritical()) {
			//todo el catalogo
		} else if (filter.getCritical()) {
			//solo critico
		} else if (!filter.getCritical()) {
			//solo no critico
		}

		return null;
	}

}
