package com.hdsupply.xmi.repository;

import com.hdsupply.xmi.domain.CheckIn;
import com.hdsupply.xmi.domain.CheckOut;
import com.hdsupply.xmi.domain.Inventory;

public interface InventoryDao {
	
	public void newInventoryProduct(Inventory inventory);
	
	public void updateInventoryProduct(Inventory inventory);
	
	public Boolean existProductInInventory(Integer shopId, Integer productId);
	
	public Inventory getInventoryById(Integer productId, Integer shopId);
	
	public Integer getQuantity(Integer productId, Integer siteId);
	
	public void newCheckIn(Inventory inventory, String user, Integer checkInId);
	
	public Integer getNextCheckinId();
	
	public CheckIn getCheckInById(Integer checkInId);
	
	public void updateCheckOutInventoryProduct(Inventory inventory);
	
	public void newCheckOut(Inventory inventory, String user, Integer checkOutId);
		
	public Integer getNextCheckOutId();
		
	public CheckOut getCheckOutById(Integer checkOutId); 

}