package com.hdsupply.xmi.service;

import com.hdsupply.xmi.domain.Inventory;

public interface InventoryService {
	
	public void checkInProduct(Inventory inventory);
	
	public void checkOutProduct(Inventory inventory);

}
