package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.CatalogDao;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.ProductDao;

@RunWith(EasyMockRunner.class)
public class CatalogServiceImplTest extends EasyMockSupport {
	
	@TestSubject
	private CatalogServiceImpl fixture = new CatalogServiceImpl();
	
	@Mock
	private CatalogDao catalogDao;
	
	@Mock
	private ProductDao productDao;
	
	@Mock
	private InventoryDao inventoryDao;
	
	@Test
	public void testGetActiveCatalogEmptyCatalog() {
		
		List<ProductCatalog> listProdCatalog = new ArrayList<>();
		
		EasyMock.expect(catalogDao.getActiveCatalog(2)).andReturn(listProdCatalog);
		
		replayAll();
		
		List<ProductCatalog> listProductcatalog = fixture.getActiveCatalog(2);
		verifyAll();
		assertEquals("Returned list should match the one returned by DAO.", listProdCatalog, listProductcatalog);
		
	}
	
	@Test
	public void testGetActiveCatalog() {
		
		ProductCatalog catalog1 = new ProductCatalog();

		catalog1.setIdProduct(1);
		catalog1.setItemNumber(123);
		catalog1.setName("AAAAAA");
		catalog1.setMax(9);
		catalog1.setMin(2);
		catalog1.setCritical(false);
		
		ProductCatalog catalog2 = new ProductCatalog();
		
		catalog2.setIdProduct(2);
		catalog2.setItemNumber(321);
		catalog2.setName("BBBBB");
		catalog2.setMax(10);
		catalog2.setMin(5);
		catalog2.setCritical(true);
		
		List<ProductCatalog> listProdCatalog = Arrays.asList(new ProductCatalog[] {catalog1, catalog2});
		
		EasyMock.expect(catalogDao.getActiveCatalog(2)).andReturn(listProdCatalog);
		
		EasyMock.expect(inventoryDao.getQuantity(1, 2)).andReturn(20);
		EasyMock.expect(inventoryDao.getQuantity(2, 2)).andReturn(30);
		
		replayAll();
		
		List<ProductCatalog> listProductcatalog = fixture.getActiveCatalog(2);
		verifyAll();
		assertEquals("AAAAAA", listProductcatalog.get(0).getName());
		assertEquals((Integer) 1, listProductcatalog.get(0).getIdProduct());
		assertEquals((Integer) 123, listProductcatalog.get(0).getItemNumber());
		assertEquals((Integer) 9, listProductcatalog.get(0).getMax());
		assertEquals((Integer) 2, listProductcatalog.get(0).getMin());
		assertEquals((Integer)20, listProductcatalog.get(0).getQuantity());
		assertEquals(false, listProdCatalog.get(0).getCritical());
		
		assertEquals("BBBBB", listProductcatalog.get(1).getName());
		assertEquals((Integer) 2, listProductcatalog.get(1).getIdProduct());
		assertEquals((Integer) 321, listProductcatalog.get(1).getItemNumber());
		assertEquals((Integer) 10, listProductcatalog.get(1).getMax());
		assertEquals((Integer) 5, listProductcatalog.get(1).getMin());
		assertEquals((Integer) 30, listProductcatalog.get(1).getQuantity());
		assertEquals(true, listProdCatalog.get(1).getCritical());
		
	}
	
	@Test
	public void testUpdateActiveCatalog() {
		
		Catalog catalog = new Catalog();
		
		catalog.setMin(4);
		catalog.setMax(10);
		catalog.setCritical(true);
		catalog.setSiteId(2);
		catalog.setProductId(1);
		catalog.setCritical(false);
		
		Capture<Catalog> capture = EasyMock.newCapture();
		
		catalogDao.updateActiveCatalog(EasyMock.capture(capture));
		
		replayAll();
		
		fixture.updateActiveCatalog(catalog);
		
		verifyAll();
		
		assertEquals(catalog.getMin(), capture.getValue().getMin());
		assertEquals(catalog.getMax(), capture.getValue().getMax());
		assertEquals(catalog.getCritical(), capture.getValue().getCritical());
		assertEquals(catalog.getSiteId(), capture.getValue().getSiteId());
		assertEquals(catalog.getProductId(), capture.getValue().getProductId());
		assertEquals(catalog.getCritical(), capture.getValue().getCritical());
		
	}

}
