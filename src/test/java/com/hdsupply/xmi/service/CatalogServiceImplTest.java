package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
public class CatalogServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private CatalogServiceImpl fixture = new CatalogServiceImpl();
	
	@Mock
	private CatalogDao catalogDao;
	
	@Mock
	private ProductDao productDao;
	
	@Test
	public void testGetActiveCatalogEmptyCatalog() {
		
		List<ProductCatalog> listProdCatalog = new ArrayList<>();
		
		List<Catalog> returnedList = new ArrayList<Catalog>();
		EasyMock.expect(catalogDao.getActiveCatalog(2)).andReturn(returnedList);
		
		replayAll();
		
		List<ProductCatalog> listProductcatalog = fixture.getActiveCatalog(2);
		verifyAll();
		assertEquals("Returned list should match the one returned by DAO.", listProdCatalog, listProductcatalog);
		
	}
	
	@Test
	public void testGetActiveCatalog() {
		
		Catalog catalog1 = new Catalog();
		catalog1.setSiteId(2);
		catalog1.setProductId(1);
		catalog1.setMax(9);
		catalog1.setMin(2);
		
		Catalog catalog2 = new Catalog();
		catalog2.setSiteId(2);
		catalog2.setProductId(2);
		catalog2.setMax(10);
		catalog2.setMin(5);
		
		List<Catalog> returnedCatalog = Arrays.asList(new Catalog[] {catalog1, catalog2});
		
		EasyMock.expect(catalogDao.getActiveCatalog(2)).andReturn(returnedCatalog);
		
		Product product1 = new Product();
		product1.setId(1);
		product1.setItemNumber(123);
		product1.setName("AAAAAA");
		
		Product product2 = new Product();
		product2.setId(2);
		product2.setItemNumber(321);
		product2.setName("BBBBB");
		
		EasyMock.expect(productDao.getProductById(1)).andReturn(product1);
		EasyMock.expect(productDao.getProductById(2)).andReturn(product2);
		
		replayAll();
		
		List<ProductCatalog> listProductcatalog = fixture.getActiveCatalog(2);
		verifyAll();
		assertEquals("AAAAAA", listProductcatalog.get(0).getName());
		assertEquals((Integer) 1, listProductcatalog.get(0).getIdProduct());
		assertEquals((Integer) 123, listProductcatalog.get(0).getItemNumber());
		assertEquals((Integer) 9, listProductcatalog.get(0).getMax());
		assertEquals((Integer) 2, listProductcatalog.get(0).getMin());
		
		assertEquals("BBBBB", listProductcatalog.get(1).getName());
		assertEquals((Integer) 2, listProductcatalog.get(1).getIdProduct());
		assertEquals((Integer) 321, listProductcatalog.get(1).getItemNumber());
		assertEquals((Integer) 10, listProductcatalog.get(1).getMax());
		assertEquals((Integer) 5, listProductcatalog.get(1).getMin());
		
	}

}
