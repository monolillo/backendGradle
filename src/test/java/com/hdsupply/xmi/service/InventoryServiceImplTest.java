package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import org.easymock.Capture;
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
	public void testCheckInUpdateProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setCheckedOutQuantity(0);
		inventory.setLocationId(1);
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		EasyMock.expect(inventoryDao.existProductInInventory(2, 1)).andReturn(true);
		
		inventoryDao.updateInventoryProduct(EasyMock.capture(captured));
		
		replayAll();
		
		inventoryServiceImplTest.checkInProduct(inventory, "admin");
		
		verifyAll();
		
		assertEquals(inventory.getCheckedOutQuantity(), captured.getValue().getCheckedOutQuantity());
		assertEquals(inventory.getLocationId(), captured.getValue().getLocationId());
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
		
	}
	
	@Test
	public void testCheckInNewProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setCheckedOutQuantity(0);
		inventory.setLocationId(1);
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		EasyMock.expect(inventoryDao.existProductInInventory(2, 1)).andReturn(false);
		
		inventoryDao.newInventoryProduct(EasyMock.capture(captured));
		
		replayAll();
		
		inventoryServiceImplTest.checkInProduct(inventory, "admin");
		
		verifyAll();
		
		assertEquals(inventory.getCheckedOutQuantity(), captured.getValue().getCheckedOutQuantity());
		assertEquals(inventory.getLocationId(), captured.getValue().getLocationId());
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
		
	}
	
	@Test
	public void testCheckoutUpdateProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		inventoryDao.updateCheckOutInventoryProduct(EasyMock.capture(captured));
		
		replayAll();
		
		inventoryServiceImplTest.checkOutProduct(inventory);
		
		verifyAll();
		
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
	}
	
}
	