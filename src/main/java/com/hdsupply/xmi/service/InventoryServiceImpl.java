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
	public Integer checkInProduct(Inventory inventory, String user) {
		
		Boolean exist = inventoryDao.existProductInInventory(inventory.getShopId(), inventory.getProductId());
		if (exist) {
			inventoryDao.updateInventoryProduct(inventory);
		} else {
			inventoryDao.newInventoryProduct(inventory);
		}
		
		Integer checkInId = inventoryDao.getNextCheckinId();
		
		inventoryDao.newCheckIn(inventory, user, checkInId);
		
		return checkInId;
	}
	
	@Override
	public void checkOutProduct(Inventory inventory) {
		
		inventoryDao.updateCheckOutInventoryProduct(inventory);
		
	}

}