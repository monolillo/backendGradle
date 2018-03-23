package com.hdsupply.xmi.repository;

import com.hdsupply.xmi.domain.Inventory;

public interface InventoryDao {
	
	public void newProduct(Inventory inventory);
	
	public void updateProduct(Inventory inventory);
	
	public Integer existProductInInventory(Integer shopId, Integer productId);
	
	public Inventory getInventoryById(Integer productId);

}
