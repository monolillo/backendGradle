package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.domain.Shop;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.ProductDao;
import com.hdsupply.xmi.repository.ShopDao;

@RunWith(EasyMockRunner.class)
public class ProductServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private ProductServiceImpl productServiceImplTest = new ProductServiceImpl();
	
	@Mock
	private ProductDao productDao;
	
	@Mock
	private InventoryDao inventoryDao;
	
	@Mock
	private ShopDao shopDao;	
	
	@Test
	public void testGetProductById() {
		
		ProductCatalog product = new ProductCatalog();
		product.setIdProduct(123);
		product.setName("A Bulb 40W A15 Frost");
		product.setItemNumber(2);
		product.setMax(10);
		product.setMin(5);
		
		EasyMock.expect(productDao.getProductById(2,1)).andReturn(product);
		
		Shop shop = new Shop();
		shop.setId(10);
		
		EasyMock.expect(shopDao.getShopBySiteId(EasyMock.eq(2))).andReturn(Collections.singletonList(shop));
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(30);
		inventory.setQuantity(20);
		EasyMock.expect(inventoryDao.getInventoryById(EasyMock.eq(1), EasyMock.eq(10))).andReturn(inventory);
		
		replayAll();
		
		ProductCatalog productcatalog = productServiceImplTest.getProductById(2,1);
		verifyAll();
		
		assertEquals((Integer) 123, productcatalog.getIdProduct());
		assertEquals("A Bulb 40W A15 Frost", productcatalog.getName());
		assertEquals((Integer) 2, productcatalog.getItemNumber());
		assertEquals((Integer) 10, productcatalog.getMax());
		assertEquals((Integer) 5, productcatalog.getMin());
		assertEquals((Integer)20, productcatalog.getQuantity());
		assertEquals((Integer)30, productcatalog.getLocationId());

	}

	@Test
	public void testGetProductByIdNoInventory() {
		
		ProductCatalog product = new ProductCatalog();
		product.setIdProduct(123);
		product.setName("A Bulb 40W A15 Frost");
		product.setItemNumber(2);
		product.setMax(10);
		product.setMin(5);
		
		EasyMock.expect(productDao.getProductById(2,1)).andReturn(product);
		
		Shop shop = new Shop();
		shop.setId(10);
		
		EasyMock.expect(shopDao.getShopBySiteId(EasyMock.eq(2))).andReturn(Collections.singletonList(shop));
		
		EasyMock.expect(inventoryDao.getInventoryById(EasyMock.eq(1), EasyMock.eq(10))).andReturn(null);
		
		replayAll();
		
		ProductCatalog productcatalog = productServiceImplTest.getProductById(2,1);
		verifyAll();
		
		assertEquals((Integer) 123, productcatalog.getIdProduct());
		assertEquals("A Bulb 40W A15 Frost", productcatalog.getName());
		assertEquals((Integer) 2, productcatalog.getItemNumber());
		assertEquals((Integer) 10, productcatalog.getMax());
		assertEquals((Integer) 5, productcatalog.getMin());
		assertEquals((Integer)0, productcatalog.getQuantity());
		assertEquals(null, productcatalog.getLocationId());

	}	
	
	@Test
	public void testGetProductByNotFound() {
		
		EasyMock.expect(productDao.getProductById(2,1)).andReturn(null);
		
		replayAll();
		
		ProductCatalog productcatalog = productServiceImplTest.getProductById(2,1);
		verifyAll();
		
		assertEquals(null, productcatalog);

	}	

	@Test
	public void testGetProductByItemNumber() {
		
		ProductCatalog product = new ProductCatalog();
		product.setIdProduct(123);
		product.setName("A Bulb 40W A15 Frost");
		product.setItemNumber(2);
		product.setMax(10);
		product.setMin(5);
		
		EasyMock.expect(productDao.getProductByItemNumber(2,1)).andReturn(product);
		
		EasyMock.expect(inventoryDao.getQuantity(123, 2)).andReturn(20);
		
		replayAll();
		
		ProductCatalog productcatalog = productServiceImplTest.getProductByItemNumber(2,1);
		verifyAll();
		
		assertEquals((Integer) 123, productcatalog.getIdProduct());
		assertEquals("A Bulb 40W A15 Frost", productcatalog.getName());
		assertEquals((Integer) 2, productcatalog.getItemNumber());
		assertEquals((Integer) 10, productcatalog.getMax());
		assertEquals((Integer) 5, productcatalog.getMin());
		assertEquals((Integer)20, productcatalog.getQuantity());

	}
	
	@Test
	public void testGetProductByItemNumberNotFound() {
		
		EasyMock.expect(productDao.getProductByItemNumber(2,1)).andReturn(null);
		
		replayAll();
		
		ProductCatalog productcatalog = productServiceImplTest.getProductByItemNumber(2,1);
		verifyAll();
		
		assertEquals(null, productcatalog);

	}	
	
}

