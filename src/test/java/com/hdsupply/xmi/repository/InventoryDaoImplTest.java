package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

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
		inventory.setCheckedOutQuantity(0);
		inventory.setLocationId(2);
		inventory.setProductId(3);
		inventory.setQuantity(10);
		inventory.setShopId(3);
		
		testIntendoryDao.newInventoryProduct(inventory);
		
		Inventory inventoryNew = testIntendoryDao.getInventoryById(inventory.getProductId(), inventory.getShopId());
		
		assertEquals((Integer) 0, inventoryNew.getCheckedOutQuantity());
		assertEquals((Integer) 2, inventoryNew.getLocationId());
		assertEquals((Integer) 3, inventoryNew.getProductId());
		assertEquals((Integer) 10, inventoryNew.getQuantity());
		assertEquals((Integer) 3, inventoryNew.getShopId());
		
	}
	
	@Test
	public void testUpdateInventoryProduct() {
		
		Inventory inventoryNew = new Inventory();
		inventoryNew.setCheckedOutQuantity(0);
		inventoryNew.setLocationId(2);
		inventoryNew.setProductId(3);
		inventoryNew.setQuantity(10);
		inventoryNew.setShopId(2);
		
		testIntendoryDao.newInventoryProduct(inventoryNew);
		
		Inventory inventoryInserted = testIntendoryDao.getInventoryById(inventoryNew.getProductId(), inventoryNew.getShopId());
		
		Inventory inventoryUpdate = new Inventory();
		inventoryUpdate.setCheckedOutQuantity(2);
		inventoryUpdate.setLocationId(3);
		inventoryUpdate.setProductId(3);
		inventoryUpdate.setQuantity(15);
		inventoryUpdate.setShopId(2);
		
		testIntendoryDao.updateInventoryProduct(inventoryUpdate);
		
		Inventory inventoryUpdated = testIntendoryDao.getInventoryById(inventoryUpdate.getProductId(), inventoryUpdate.getShopId());
		
		assertEquals((Integer) 2, inventoryUpdated.getCheckedOutQuantity());
		assertEquals((Integer) 3, inventoryUpdated.getLocationId());
		assertEquals((Integer) 3, inventoryUpdated.getProductId());
		assertEquals((Integer) 15, inventoryUpdated.getQuantity());
		assertEquals((Integer) 2, inventoryUpdated.getShopId());
		
		assertNotEquals(inventoryInserted.getCheckedOutQuantity(), inventoryUpdated.getCheckedOutQuantity());
		assertNotEquals(inventoryInserted.getLocationId(), inventoryUpdated.getLocationId());
		assertNotEquals(inventoryInserted, inventoryUpdated.getQuantity());
		
	}
	
	@Test
	public void testGetInventoryById() {
		
		Inventory inventory = testIntendoryDao.getInventoryById(5, 3);
		
		assertEquals((Integer) 0, inventory.getCheckedOutQuantity());
		assertEquals((Integer) 6, inventory.getLocationId());
		assertEquals((Integer) 5, inventory.getProductId());
		assertEquals((Integer) 2, inventory.getQuantity());
		assertEquals((Integer) 3, inventory.getShopId());
		
	}
	
	@Bean
	public InventoryDao testIntendoryDao() {
		return new InventoryDaoImpl();
	}

}
