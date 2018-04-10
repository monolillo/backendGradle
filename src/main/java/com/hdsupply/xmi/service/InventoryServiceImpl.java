package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.CheckIn;
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
	public void checkOutProduct(Inventory inventory, String user) {
		
		inventoryDao.updateCheckOutInventoryProduct(inventory);
		
		Inventory invent = inventoryDao.getInventoryById(inventory.getProductId(), inventory.getShopId());
		
		inventory.setLocationId(invent.getLocationId());
		
		Integer checkOutId = inventoryDao.getNextCheckOutId();
		
		inventoryDao.newCheckOut(inventory, user, checkOutId);
		
	}
	
	@Override
	public void undoCheckIn(Integer checkInId) {
		
		CheckIn checkIn = inventoryDao.getCheckInById(checkInId);
		
		inventoryDao.deleteCheckIn(checkInId);
		
		inventoryDao.undoCheckIn(checkIn);
		
	}
 
}