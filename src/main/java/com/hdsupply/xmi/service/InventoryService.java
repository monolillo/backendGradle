package com.hdsupply.xmi.service;

import com.hdsupply.xmi.domain.Inventory;

public interface InventoryService {
	
	public Integer checkInProduct(Inventory inventory, String user);
	
	public Integer checkOutProduct(Inventory inventory, String user);
	
	public void undoCheckIn(Integer checkInId);
	
	public void undoCheckOut(Integer checkOut);
 
}
