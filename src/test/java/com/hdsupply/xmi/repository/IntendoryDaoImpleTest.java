package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Inventory;

@ContextConfiguration(classes=IntendoryDaoImpleTest.class)
@Configuration
public class IntendoryDaoImpleTest extends DaoDbTestBase {
	
	@Autowired
	private InventoryDao testIntendoryDao;
	
	@Test
	public void testExistProductInInventory() {
		
		Integer existe = testIntendoryDao.existProductInInventory(2, 1);
		Integer noExiste = testIntendoryDao.existProductInInventory(10, 11);
		
		assertEquals((Integer) 1, existe);
		assertEquals((Integer) 0, noExiste);
		
	}
	
	@Test
	public void testNewProduct() {
		
		Inventory inventory = new Inventory();
		inventory.setCheckedOutQuantity(0);
		inventory.setLocationId(2);
		inventory.setProductId(3);
		inventory.setQuantity(10);
		inventory.setShopId(2);
		
	}
	
	@Bean
	public InventoryDao testIntendoryDao() {
		return new InventoryDaoImpl();
	}

}
