package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Product;

@ContextConfiguration(classes=ProductDaoImplTest.class)
@Configuration
public class ProductDaoImplTest extends DaoDbTestBase{
	
	@Autowired
	private ProductDao fixture;	

	@Test
	public void getProductById() {
		
		Product product = fixture.getProductById(1);
		assertEquals((Integer)1, product.getId());
		assertEquals("A Bulb 40W A15 Frost", product.getName());
		assertEquals((Integer)307692, product.getItemNumber());
		
	}
	
	@Bean
	public ProductDao fixture() {
		return new ProductDaoImpl();
	}

}
