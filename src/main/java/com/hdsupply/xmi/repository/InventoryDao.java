package com.hdsupply.xmi.repository;

import com.hdsupply.xmi.domain.Inventory;

public interface InventoryDao {
	
	public void newInventoryProduct(Inventory inventory);
	
	public void updateInventoryProduct(Inventory inventory);
	
	public Boolean existProductInInventory(Integer shopId, Integer productId);
	
	public Inventory getInventoryById(Integer productId, Integer shopId);

}