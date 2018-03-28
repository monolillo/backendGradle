package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.ProductDao;

@RunWith(EasyMockRunner.class)
public class ProductServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private ProductServiceImpl productServiceImplTest = new ProductServiceImpl();
	
	@Mock
	private ProductDao productDao;
	
	@Mock
	private InventoryDao inventoryDao;
	
	@Test
	public void testGetProductById() {
		
		ProductCatalog product = new ProductCatalog();
		product.setIdProduct(123);
		product.setName("A Bulb 40W A15 Frost");
		product.setItemNumber(2);
		product.setMax(10);
		product.setMin(5);
		
		EasyMock.expect(productDao.getProductById(2,1)).andReturn(product);
		
		EasyMock.expect(inventoryDao.getQuantity(1, 2)).andReturn(20);
		
		replayAll();
		
		ProductCatalog productcatalog = productServiceImplTest.getProductById(2,1);
		verifyAll();
		
		assertEquals((Integer) 123, productcatalog.getIdProduct());
		assertEquals("A Bulb 40W A15 Frost", productcatalog.getName());
		assertEquals((Integer) 2, productcatalog.getItemNumber());
		assertEquals((Integer) 10, productcatalog.getMax());
		assertEquals((Integer) 5, productcatalog.getMin());
		assertEquals((Integer)20, productcatalog.getQuantity());

	}
	
}

