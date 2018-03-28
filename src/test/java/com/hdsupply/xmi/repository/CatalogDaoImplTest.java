package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;

@ContextConfiguration(classes=CatalogDaoImplTest.class)
@Configuration
public class CatalogDaoImplTest extends DaoDbTestBase{
	
	@Autowired
	private CatalogDao fixture;	

	@Test
	public void testGetActiveCatalog() {
		
		List<ProductCatalog> catalogList = fixture.getActiveCatalog(2);
		
		assertEquals((Integer)5, catalogList.get(0).getMin());
		assertEquals((Integer)30, catalogList.get(0).getMax());
		assertEquals((Integer)1, catalogList.get(0).getIdProduct());

		assertEquals((Integer)5, catalogList.get(1).getMin());
		assertEquals((Integer)30, catalogList.get(1).getMax());
		assertEquals((Integer)2, catalogList.get(1).getIdProduct());
		
	}
	
	@Test
	public void testUpdateActiveCatalog() {
		
		Catalog catalog = fixture.getCatalogById(2,1);
		
		catalog.setCritical(true);
		catalog.setMin(3);
		catalog.setMax(20);
		
		fixture.updateActiveCatalog(catalog);
		
		Catalog modifiedCatalog = fixture.getCatalogById(2,1);
		
		assertEquals((Integer)3, modifiedCatalog.getMin());
		assertEquals((Integer)20, modifiedCatalog.getMax());
		assertEquals(true, modifiedCatalog.getCritical());
	
	}
	
	@Test
	public void testGetCatalogById() {
		
		Catalog catalog = fixture.getCatalogById(2,1);
		
		assertEquals((Integer)5, catalog.getMin());
		assertEquals((Integer)30, catalog.getMax());
		assertEquals(false, catalog.getCritical());
		assertEquals((Integer)2, catalog.getSiteId());
		assertEquals((Integer)1, catalog.getProductId());
		
	}
	
	@Bean
	public CatalogDao fixture() {
		return new CatalogDaoImpl();
	}
}
