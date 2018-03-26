package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.Product;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.CatalogDao;
import com.hdsupply.xmi.repository.ProductDao;

@RunWith(EasyMockRunner.class)
public class ProductServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private ProductServiceImpl productServiceImplTest = new ProductServiceImpl();
	
	@Mock
	private CatalogDao catalogDao;
	
	@Mock
	private ProductDao productDao;
	
	@Test
	public void testGetProductById() {
		
		ProductCatalog product = new ProductCatalog();
		product.setIdProduct(123);
		product.setName("A Bulb 40W A15 Frost");
		product.setItemNumber(2);
		product.setMax(10);
		product.setMin(5);
		product.setQuantity(20);
		
		EasyMock.expect(productDao.getProductById(2,1)).andReturn(product);
		
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

