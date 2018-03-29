package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.ProductCatalog;

@ContextConfiguration(classes=ProductDaoImplTest.class)
@Configuration
public class ProductDaoImplTest extends DaoDbTestBase{
	
	@Autowired
	private ProductDao fixture;	

	@Test
	public void getProductById() {
		
		ProductCatalog product = fixture.getProductById(2,1);
		assertEquals((Integer)1, product.getIdProduct());
		assertEquals("A Bulb 40W A15 Frost", product.getName());
		assertEquals((Integer)307692, product.getItemNumber());
		
	}
	
	@Test
	public void getProductByIdNotFound() {
		
		ProductCatalog product = fixture.getProductById(2,100);
		assertEquals(null, product);
		
	}	
	
	@Bean
	public ProductDao fixture() {
		return new ProductDaoImpl();
	}

}
