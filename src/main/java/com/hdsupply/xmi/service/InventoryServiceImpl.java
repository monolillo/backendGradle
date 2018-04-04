package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.repository.InventoryDao;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryDao inventoryDao;

	@Override
	public void checkInProduct(Inventory inventory) {
		
		Boolean exist = inventoryDao.existProductInInventory(inventory.getShopId(), inventory.getProductId());
		if (exist) {
			inventoryDao.updateInventoryProduct(inventory);
		} else {
			inventoryDao.newInventoryProduct(inventory);
		}
	}
	
	@Override
	public void checkOutProduct(Inventory inventory) {
		
		inventoryDao.updateCheckOutInventoryProduct(inventory);
		
	}

}