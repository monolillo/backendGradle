package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Catalog;

@ContextConfiguration(classes=CatalogDaoImplTest.class)
@Configuration
public class CatalogDaoImplTest extends DaoDbTestBase{
	
	@Autowired
	private CatalogDao fixture;	

	@Test
	public void testGetActiveCatalog() {
		
		List<Catalog> catalogList = fixture.getActiveCatalog(2);
		
		assertEquals(5, catalogList.get(0).getMin());
		assertEquals(30, catalogList.get(0).getMax());
		assertEquals(1, catalogList.get(0).getProductId());

		assertEquals(5, catalogList.get(1).getMin());
		assertEquals(30, catalogList.get(1).getMax());
		assertEquals(2, catalogList.get(1).getProductId());
		
	}
	
	@Bean
	public CatalogDao fixture() {
		return new CatalogDaoImpl();
	}
}