package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.CheckIn;
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
		inventory.setLocationId(1);
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		EasyMock.expect(inventoryDao.existProductInInventory(2, 1)).andReturn(true);
		
		inventoryDao.updateInventoryProduct(EasyMock.capture(captured));
		
		EasyMock.expect(inventoryDao.getNextCheckinId()).andReturn(1);
		
		inventoryDao.newCheckIn(EasyMock.capture(captured), EasyMock.eq("admin"), EasyMock.eq(1));
		
		replayAll();
		
		inventoryServiceImplTest.checkInProduct(inventory, "admin");
		
		verifyAll();
		
		assertEquals(inventory.getLocationId(), captured.getValue().getLocationId());
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
		
	}
	
	@Test
	public void testCheckoutUpdateProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(2);
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
				
		inventoryDao.updateCheckOutInventoryProduct(EasyMock.capture(captured));
		
		EasyMock.expect(inventoryDao.getNextCheckOutId()).andReturn(1);
		EasyMock.expect(inventoryDao.getInventoryById(1,2)).andReturn(inventory);
		
		inventoryDao.newCheckOut(EasyMock.capture(captured), EasyMock.eq("admin"), EasyMock.eq(1));
		
		replayAll();
		
		inventoryServiceImplTest.checkOutProduct(inventory, "admin");
		
		verifyAll();
		
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
	}
	
	@Test
	public void testCheckInNewProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(1);
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		EasyMock.expect(inventoryDao.existProductInInventory(2, 1)).andReturn(false);
		
		inventoryDao.newInventoryProduct(EasyMock.capture(captured));
		
		EasyMock.expect(inventoryDao.getNextCheckinId()).andReturn(1);
		
		inventoryDao.newCheckIn(EasyMock.capture(captured), EasyMock.eq("admin"), EasyMock.eq(1));
		
		replayAll();
		
		inventoryServiceImplTest.checkInProduct(inventory, "admin");
		
		verifyAll();
		
		assertEquals(inventory.getLocationId(), captured.getValue().getLocationId());
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
		
	}
	
	@Test
	public void testCheckOutProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setProductId(1);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		inventoryDao.updateCheckOutInventoryProduct(EasyMock.capture(captured));
		
		EasyMock.expect(inventoryDao.getNextCheckOutId()).andReturn(1);
		EasyMock.expect(inventoryDao.getInventoryById(1,2)).andReturn(inventory);
		
		inventoryDao.newCheckOut(EasyMock.capture(captured), EasyMock.eq("admin"), EasyMock.eq(1));
		
		replayAll();
		
		inventoryServiceImplTest.checkOutProduct(inventory, "admin");
		
		verifyAll();
		
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
		
	}
	
	@Test
	public void testUndoCheckIn() {
		
		CheckIn checkIn = new CheckIn();
		checkIn.setId(5);
		checkIn.setQuantity(5);
		checkIn.setUserName("admin");
		checkIn.setTimestamp(new Date());
		checkIn.setShopId(2);
		checkIn.setLocationId(2);
		checkIn.setProductId(1);
		
		Capture<CheckIn> captured = EasyMock.newCapture();
		
		EasyMock.expect(inventoryDao.getCheckInById(EasyMock.eq(5))).andReturn(checkIn);
		
		inventoryDao.deleteCheckIn(EasyMock.eq(5));
		
		inventoryDao.undoCheckIn(EasyMock.capture(captured));
		
		replayAll();
		
		inventoryServiceImplTest.undoCheckIn(5);
		
		verifyAll();
		
		assertEquals(checkIn.getId(), captured.getValue().getId());
		assertEquals(checkIn.getQuantity(), captured.getValue().getQuantity());
		assertEquals(checkIn.getUserName(), captured.getValue().getUserName());
		assertEquals(checkIn.getTimestamp(), captured.getValue().getTimestamp());
		assertEquals(checkIn.getShopId(), captured.getValue().getShopId());
		assertEquals(checkIn.getLocationId(), captured.getValue().getLocationId());
		assertEquals(checkIn.getProductId(), captured.getValue().getProductId());
		
	}
	
}
	