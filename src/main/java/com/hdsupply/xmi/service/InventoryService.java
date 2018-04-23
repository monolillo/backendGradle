package com.hdsupply.xmi.service;

import java.io.IOException;

import com.hdsupply.xmi.domain.Inventory;

public interface InventoryService {
	
	public Integer checkInProduct(Inventory inventory, String user);
	
	public Integer checkOutProduct(Inventory inventory, String user) throws IOException;
	
	public void undoCheckIn(Integer checkInId);
	
	public void undoCheckOut(Integer checkOut);
 
}
