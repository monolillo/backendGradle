package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.repository.InventoryDao;

@Service
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryDao inventoryDao;

	@Override
	public void checkInProduct(Inventory inventory) {
		
		Integer exist = inventoryDao.existProductInInventory(inventory.getShopId(), inventory.getProductId());
		if (exist > 0) {
			inventoryDao.updateProduct(inventory);
		} else {
			inventoryDao.newProduct(inventory);
		}
	}

}
