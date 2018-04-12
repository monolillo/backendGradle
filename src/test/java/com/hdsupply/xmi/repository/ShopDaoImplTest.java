package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Shop;

@ContextConfiguration(classes=ShopDaoImplTest.class)
@Configuration
public class ShopDaoImplTest extends DaoDbTestBase{
	
	@Autowired
	private ShopDao fixture;
	
	@Test
	public void testGetSiteByUser() {
		
		List<Shop> shop = fixture.getShopBySiteId(2);
		
		assertEquals((Integer)2, shop.get(0).getId());
		assertEquals("Maintenance", shop.get(0).getName());
		assertEquals((Integer)2, shop.get(0).getSiteId());

		assertEquals((Integer)3, shop.get(1).getId());
		assertEquals("Storage", shop.get(1).getName());
		assertEquals((Integer)2, shop.get(1).getSiteId());
	
		
	}
	
	
	
	
	@Bean
	public ShopDao fixture() {
		return new ShopDaoImpl();
	}

}
