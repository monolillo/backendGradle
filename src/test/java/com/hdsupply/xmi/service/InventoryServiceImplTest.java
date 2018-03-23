package com.hdsupply.xmi.service;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.repository.InventoryDao;

@RunWith(EasyMockRunner.class)
public class InventoryServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private InventoryServiceImpl inventoryServiceImplTest = new InventoryServiceImpl();
	
	@Mock
	private InventoryDao inventoryDao;
	
	@Test
	public void testCheckInProductExist() {
		
		Inventory inventory = new Inventory();
		inventory.setCheckedOutQuantity(0);
		inventory.setLocationId(2);
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		EasyMock.expect(inventoryDao.existProductInInventory(2, 1)).andReturn(1);
		
		replayAll();
		
		inventoryServiceImplTest.checkInProduct(inventory);
		
	}
	
//	@Test
//	public void testCheckInProductNotExist() {
//		
//		Integer existe = 0;
//		
//		EasyMock.expect(inventoryDao.existProductInInventory(2, 1)).andReturn(existe);
//		
//	}

}
