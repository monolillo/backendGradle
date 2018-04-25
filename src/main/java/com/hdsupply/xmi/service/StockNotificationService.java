package com.hdsupply.xmi.service;

import java.io.IOException;

public interface StockNotificationService {

	public void doNotification(String user, Integer shopId, Integer productId) throws IOException;
	
}
