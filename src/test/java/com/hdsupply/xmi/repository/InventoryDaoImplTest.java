package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.CheckIn;
import com.hdsupply.xmi.domain.CheckOut;
import com.hdsupply.xmi.domain.Inventory;

@ContextConfiguration(classes=InventoryDaoImplTest.class)
@Configuration
public class InventoryDaoImplTest extends DaoDbTestBase {
	
	@Autowired
	private InventoryDao testIntendoryDao;
	
	@Test
	public void testExistProductInInventory() {
		
		Boolean existe = testIntendoryDao.existProductInInventory(2, 1);
		Boolean noExiste = testIntendoryDao.existProductInInventory(10, 11);
		
		assertEquals(true, existe);
		assertEquals(false, noExiste);
		
	}
	
	@Test
	public void testNewInventoryProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(2);
		inventory.setProductId(3);
		inventory.setQuantity(10);
		inventory.setShopId(3);
		
		testIntendoryDao.newInventoryProduct(inventory);
		
		Inventory inventoryNew = testIntendoryDao.getInventoryById(inventory.getProductId(), inventory.getShopId());
		
		assertEquals((Integer) 2, inventoryNew.getLocationId());
		assertEquals((Integer) 3, inventoryNew.getProductId());
		assertEquals((Integer) 10, inventoryNew.getQuantity());
		assertEquals((Integer) 3, inventoryNew.getShopId());
		
	}
	
	@Test
	public void testUpdateInventoryProduct() {
		
		Inventory inventoryNew = new Inventory();
		inventoryNew.setLocationId(2);
		inventoryNew.setProductId(3);
		inventoryNew.setQuantity(10);
		inventoryNew.setShopId(2);
		
		testIntendoryDao.newInventoryProduct(inventoryNew);
		
		Inventory inventoryInserted = testIntendoryDao.getInventoryById(inventoryNew.getProductId(), inventoryNew.getShopId());
		
		Inventory inventoryUpdate = new Inventory();
		inventoryUpdate.setLocationId(3);
		inventoryUpdate.setProductId(3);
		inventoryUpdate.setQuantity(15);
		inventoryUpdate.setShopId(2);
		
		testIntendoryDao.updateInventoryProduct(inventoryUpdate);
		
		Inventory inventoryUpdated = testIntendoryDao.getInventoryById(inventoryUpdate.getProductId(), inventoryUpdate.getShopId());
		
		assertEquals((Integer) 3, inventoryUpdated.getLocationId());
		assertEquals((Integer) 3, inventoryUpdated.getProductId());
		assertEquals((Integer) 15, inventoryUpdated.getQuantity());
		assertEquals((Integer) 2, inventoryUpdated.getShopId());
		
		assertNotEquals(inventoryInserted.getLocationId(), inventoryUpdated.getLocationId());
		assertNotEquals(inventoryInserted, inventoryUpdated.getQuantity());
		
	}
	
	@Test
	public void testGetInventoryById() {
		
		Inventory inventory = testIntendoryDao.getInventoryById(5, 3);
		
		assertEquals((Integer) 6, inventory.getLocationId());
		assertEquals((Integer) 5, inventory.getProductId());
		assertEquals((Integer) 2, inventory.getQuantity());
		assertEquals((Integer) 3, inventory.getShopId());
		
	}
	
	@Test
	public void testGetInventoryByIdNotFound() {
		
		Inventory inventory = testIntendoryDao.getInventoryById(100, 200);
		
		assertEquals(null, inventory);
		
	}
	
	@Test
	public void testUpdateCheckOutInventoryProduct() {
		
		Inventory inventoryCheckout = new Inventory();
		
		inventoryCheckout.setProductId(2);
		inventoryCheckout.setQuantity(2);
		inventoryCheckout.setShopId(2);
		
		testIntendoryDao.updateCheckOutInventoryProduct(inventoryCheckout);
		
		Inventory inventoryUpdated = testIntendoryDao.getInventoryById(inventoryCheckout.getProductId(), inventoryCheckout.getShopId());

		assertEquals((Integer) 2, inventoryUpdated.getProductId());
		assertEquals((Integer) 2, inventoryUpdated.getQuantity());
		assertEquals((Integer) 2, inventoryUpdated.getShopId());

		
	}
	
	@Test
	public void testGetQuantity() {
		
		Integer qty = testIntendoryDao.getQuantity(1, 2);
		
		assertEquals((Integer) 10, qty);
		
	}
	
	@Test
	public void testNewCheckIn() {
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(2);
		inventory.setProductId(3);
		inventory.setQuantity(10);
		inventory.setShopId(3);
		
		String user = "admin";
		
		Integer checkInId = 1;
		
		testIntendoryDao.newCheckIn(inventory, user, checkInId);
		
		CheckIn checkIn = testIntendoryDao.getCheckInById(checkInId);
		
		assertEquals(inventory.getLocationId(), checkIn.getLocationId());
		assertEquals(inventory.getProductId(), checkIn.getProductId());
		assertEquals(inventory.getQuantity(), checkIn.getQuantity());
		assertEquals(inventory.getShopId(), checkIn.getShopId());
		assertEquals(user, checkIn.getUserName());
		assertEquals(checkInId, checkIn.getId());
		
	}
	
	@Test
	public void testGetNextCheckinId() {
		
		Integer checkInId = testIntendoryDao.getNextCheckinId();
		
		assertEquals((Integer) 1, checkInId);
		
	}
	
	@Test
	public void testGetCheckInById() {
		
		CheckIn checkIn = testIntendoryDao.getCheckInById(5);
		
		assertEquals((Integer) 5, checkIn.getId());
		assertEquals((Integer) 5, checkIn.getQuantity());
		assertEquals("admin", checkIn.getUserName());
		assertEquals((Integer) 2, checkIn.getShopId());
		assertEquals((Integer) 2, checkIn.getLocationId());
		assertEquals((Integer) 1, checkIn.getProductId());
		
	}
	
	@Test
	public void testUndoCheckIn() {
		
		Inventory inventory = testIntendoryDao.getInventoryById(5, 3);
		CheckIn checkIn = testIntendoryDao.getCheckInById(7);
		
		assertEquals((Integer)2, inventory.getQuantity());
		
		testIntendoryDao.undoCheckIn(checkIn);
		
		Inventory inventoryUndo = testIntendoryDao.getInventoryById(5, 3);
		
		assertEquals((Integer)1, inventoryUndo.getQuantity());
		
	}
	
	@Test
	public void testGetCheckInByIdNotFound() {
		
		CheckIn checkIn = testIntendoryDao.getCheckInById(100);
		
		assertEquals(null, checkIn);
		
	}
	
	@Test
	public void testNewCheckOut() {
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(2);
		inventory.setProductId(3);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
		String user = "admin";
		
		Integer checkOutId = 1;
		
		testIntendoryDao.newCheckOut(inventory, user, checkOutId);
		
		CheckOut checkOut = testIntendoryDao.getCheckOutById(checkOutId);
		
		assertEquals(inventory.getLocationId(), checkOut.getLocationId());
		assertEquals(inventory.getProductId(), checkOut.getProductId());
		assertEquals(inventory.getQuantity(), checkOut.getQuantity());
		assertEquals(inventory.getShopId(), checkOut.getShopId());
		assertEquals(user, checkOut.getUserName());
		assertEquals(checkOutId, checkOut.getId()); 
		
	}
	
	@Test
	public void testGetNextCheckoutId() {
		
		Integer checkOutId = testIntendoryDao.getNextCheckOutId();
		
		assertEquals((Integer) 1, checkOutId);
		
	}
	
	@Test
	public void testGetCheckOutById() {
		
		CheckOut checkOut = testIntendoryDao.getCheckOutById(6);
		
		assertEquals((Integer) 6, checkOut.getId());
		assertEquals((Integer) 5, checkOut.getQuantity());
		assertEquals("admin", checkOut.getUserName());
		assertEquals((Integer) 2, checkOut.getShopId());
		assertEquals((Integer) 2, checkOut.getLocationId());
		assertEquals((Integer) 1, checkOut.getProductId());
		
	}

	@Test
	public void testGetCheckOutByIdNotFound() {
		
		CheckOut checkOut = testIntendoryDao.getCheckOutById(100);
		
		assertEquals(null, checkOut);
		
	}
	
	@Test
	public void testDeleteCheckIn() {
		
		CheckIn checkIn = testIntendoryDao.getCheckInById(6);
		
		assertEquals((Integer) 6, checkIn.getId());
		
		testIntendoryDao.deleteCheckIn(6);
		
		CheckIn checkInDelete = testIntendoryDao.getCheckInById(6);
		
		assertEquals(null, checkInDelete);
		
	}
	
	@Bean
	public InventoryDao testIntendoryDao() {
		return new InventoryDaoImpl();
	}

}