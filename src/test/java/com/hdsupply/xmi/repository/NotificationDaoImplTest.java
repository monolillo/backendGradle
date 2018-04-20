package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;

@ContextConfiguration(classes=NotificationDaoImplTest.class)
@Configuration
public class NotificationDaoImplTest extends DaoDbTestBase {
	
	@Autowired
	private NotificationDao testNotificationDao;
	
	@Test
	public void testGetListOutOfStock() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<ProductCatalog>();
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(3);
		filter.setCritical(true);
		
		listProductCatalog.addAll(testNotificationDao.getListOutOfStock(filter));
		
		filter.setCritical(false);
		
		listProductCatalog.addAll(testNotificationDao.getListOutOfStock(filter));
		
		assertEquals("Avery Door Hangers With Tear Away Cards, 2 Cards P", listProductCatalog.get(0).getName());
		assertEquals((Integer)186335, listProductCatalog.get(0).getItemNumber());
		assertEquals((Integer)1090, listProductCatalog.get(0).getIdProduct());
		assertEquals(new BigDecimal("15.67"), listProductCatalog.get(0).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(0).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(0).getMin());
		assertEquals((Integer)10, listProductCatalog.get(0).getMax());
		assertEquals(true, listProductCatalog.get(0).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(0).getLocationId());
		
		assertEquals("Echo 2.6 Ounce 50-1 2-Cycle Engine Oil With Fuel S", listProductCatalog.get(1).getName());
		assertEquals((Integer)130946, listProductCatalog.get(1).getItemNumber());
		assertEquals((Integer)1091, listProductCatalog.get(1).getIdProduct());
		assertEquals(new BigDecimal("19.99"), listProductCatalog.get(1).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(1).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(1).getMin());
		assertEquals((Integer)10, listProductCatalog.get(1).getMax());
		assertEquals(true, listProductCatalog.get(1).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(1).getLocationId());
		
		assertEquals("Certified Safety 4-Shelf Class B First Aid Cabinet", listProductCatalog.get(2).getName());
		assertEquals((Integer)113644, listProductCatalog.get(2).getItemNumber());
		assertEquals((Integer)2000, listProductCatalog.get(2).getIdProduct());
		assertEquals(new BigDecimal("253.99"), listProductCatalog.get(2).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(2).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(2).getMin());
		assertEquals((Integer)10, listProductCatalog.get(2).getMax());
		assertEquals(false, listProductCatalog.get(2).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(2).getLocationId());
		
		assertEquals("Medifirst Zika Outdoor Protection, Package Of 2 K", listProductCatalog.get(3).getName());
		assertEquals((Integer)112286, listProductCatalog.get(3).getItemNumber());
		assertEquals((Integer)2001, listProductCatalog.get(3).getIdProduct());
		assertEquals(new BigDecimal("160.00"), listProductCatalog.get(3).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(3).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(3).getMin());
		assertEquals((Integer)10, listProductCatalog.get(3).getMax());
		assertEquals(false, listProductCatalog.get(3).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(3).getLocationId());
		
	}
	
	@Test
	public void testGetListLessThanMin() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<ProductCatalog>();
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(3);
		filter.setCritical(true);
		
		listProductCatalog.addAll(testNotificationDao.getListLessThanMin(filter));
		
		filter.setCritical(false);
		
		listProductCatalog.addAll(testNotificationDao.getListLessThanMin(filter));
		
		assertEquals("Poulan Pro SAE 30 4-Cycle Engine Oil", listProductCatalog.get(0).getName());
		assertEquals((Integer)130254, listProductCatalog.get(0).getItemNumber());
		assertEquals((Integer)1092, listProductCatalog.get(0).getIdProduct());
		assertEquals(new BigDecimal("6.99"), listProductCatalog.get(0).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(0).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(0).getMin());
		assertEquals((Integer)10, listProductCatalog.get(0).getMax());
		assertEquals(true, listProductCatalog.get(0).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(0).getLocationId());
		
		assertEquals("Mi-T-M 16 Ounce Pressure Washer Pump Oil", listProductCatalog.get(1).getName());
		assertEquals((Integer)131088, listProductCatalog.get(1).getItemNumber());
		assertEquals((Integer)1093, listProductCatalog.get(1).getIdProduct());
		assertEquals(new BigDecimal("5.99"), listProductCatalog.get(1).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(1).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(1).getMin());
		assertEquals((Integer)10, listProductCatalog.get(1).getMax());
		assertEquals(true, listProductCatalog.get(1).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(1).getLocationId());
		
		assertEquals("Legionella Bacterium Water Testing Kit", listProductCatalog.get(2).getName());
		assertEquals((Integer)112287, listProductCatalog.get(2).getItemNumber());
		assertEquals((Integer)2002, listProductCatalog.get(2).getIdProduct());
		assertEquals(new BigDecimal("440.00"), listProductCatalog.get(2).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(2).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(2).getMin());
		assertEquals((Integer)10, listProductCatalog.get(2).getMax());
		assertEquals(false, listProductCatalog.get(2).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(2).getLocationId());
		
		assertEquals("2 Oz Burn Spray", listProductCatalog.get(3).getName());
		assertEquals((Integer)132064, listProductCatalog.get(3).getItemNumber());
		assertEquals((Integer)2003, listProductCatalog.get(3).getIdProduct());
		assertEquals(new BigDecimal("5.99"), listProductCatalog.get(3).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(3).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(3).getMin());
		assertEquals((Integer)10, listProductCatalog.get(3).getMax());
		assertEquals(false, listProductCatalog.get(3).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(3).getLocationId());
		
	}
	
	@Test
	public void testGetListMinThreshold() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<ProductCatalog>();
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(3);
		filter.setCritical(true);
		
		listProductCatalog.addAll(testNotificationDao.getListMinThreshold(filter));
		
		filter.setCritical(false);
		
		listProductCatalog.addAll(testNotificationDao.getListMinThreshold(filter));
		
		assertEquals("11 Oz Liquid Wrench Industrial Chain Lubricant", listProductCatalog.get(0).getName());
		assertEquals((Integer)111593, listProductCatalog.get(0).getItemNumber());
		assertEquals((Integer)1094, listProductCatalog.get(0).getIdProduct());
		assertEquals(new BigDecimal("6.49"), listProductCatalog.get(0).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(0).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(0).getMin());
		assertEquals((Integer)10, listProductCatalog.get(0).getMax());
		assertEquals(true, listProductCatalog.get(0).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(0).getLocationId());
		
		assertEquals("Tanaka Perfect Mix 16 Oz 2-Cycle Engine Oil With F", listProductCatalog.get(1).getName());
		assertEquals((Integer)130818, listProductCatalog.get(1).getItemNumber());
		assertEquals((Integer)1095, listProductCatalog.get(1).getIdProduct());
		assertEquals(new BigDecimal("7.49"), listProductCatalog.get(1).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(1).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(1).getMin());
		assertEquals((Integer)10, listProductCatalog.get(1).getMax());
		assertEquals(true, listProductCatalog.get(1).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(1).getLocationId());
		
		assertEquals("200 Mg Medi-First Ibuprofen 2 Tablets, Box Of 250", listProductCatalog.get(2).getName());
		assertEquals((Integer)132075, listProductCatalog.get(2).getItemNumber());
		assertEquals((Integer)2004, listProductCatalog.get(2).getIdProduct());
		assertEquals(new BigDecimal("14.99"), listProductCatalog.get(2).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(2).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(2).getMin());
		assertEquals((Integer)10, listProductCatalog.get(2).getMax());
		assertEquals(false, listProductCatalog.get(2).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(2).getLocationId());
		
		assertEquals("Medi-First Antacid Box Of 50", listProductCatalog.get(3).getName());
		assertEquals((Integer)132078, listProductCatalog.get(3).getItemNumber());
		assertEquals((Integer)2005, listProductCatalog.get(3).getIdProduct());
		assertEquals(new BigDecimal("9.49"), listProductCatalog.get(3).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(3).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(3).getMin());
		assertEquals((Integer)10, listProductCatalog.get(3).getMax());
		assertEquals(false, listProductCatalog.get(3).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(3).getLocationId());
		
	}
	
	@Test
	public void testGetListMaxThreshold() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<ProductCatalog>();
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(3);
		filter.setCritical(true);
		
		listProductCatalog.addAll(testNotificationDao.getListMaxThreshold(filter));
		
		filter.setCritical(false);
		
		listProductCatalog.addAll(testNotificationDao.getListMaxThreshold(filter));
		
		assertEquals("14 Ounce High Temperature Grease Cartridge", listProductCatalog.get(0).getName());
		assertEquals((Integer)111267, listProductCatalog.get(0).getItemNumber());
		assertEquals((Integer)1098, listProductCatalog.get(0).getIdProduct());
		assertEquals(new BigDecimal("9.49"), listProductCatalog.get(0).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(0).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(0).getMin());
		assertEquals((Integer)10, listProductCatalog.get(0).getMax());
		assertEquals(true, listProductCatalog.get(0).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(0).getLocationId());
		
		assertEquals("14 Oz Multipurpose Grease Cartridge", listProductCatalog.get(1).getName());
		assertEquals((Integer)111364, listProductCatalog.get(1).getItemNumber());
		assertEquals((Integer)1099, listProductCatalog.get(1).getIdProduct());
		assertEquals(new BigDecimal("4.99"), listProductCatalog.get(1).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(1).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(1).getMin());
		assertEquals((Integer)10, listProductCatalog.get(1).getMax());
		assertEquals(true, listProductCatalog.get(1).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(1).getLocationId());
		
		assertEquals("Burn Aid Burn Gel Package Of 25", listProductCatalog.get(2).getName());
		assertEquals((Integer)132068, listProductCatalog.get(2).getItemNumber());
		assertEquals((Integer)2008, listProductCatalog.get(2).getIdProduct());
		assertEquals(new BigDecimal("15.49"), listProductCatalog.get(2).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(2).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(2).getMin());
		assertEquals((Integer)10, listProductCatalog.get(2).getMax());
		assertEquals(false, listProductCatalog.get(2).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(2).getLocationId());
		
		assertEquals("1/2 Oz Eyedrops", listProductCatalog.get(3).getName());
		assertEquals((Integer)132069, listProductCatalog.get(3).getItemNumber());
		assertEquals((Integer)2009, listProductCatalog.get(3).getIdProduct());
		assertEquals(new BigDecimal("4.49"), listProductCatalog.get(3).getPrice());
		assertEquals("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p", listProductCatalog.get(3).getImageUrl());
		assertEquals((Integer)5, listProductCatalog.get(3).getMin());
		assertEquals((Integer)10, listProductCatalog.get(3).getMax());
		assertEquals(false, listProductCatalog.get(3).getCritical());
		assertEquals((Integer)8, listProductCatalog.get(3).getLocationId());
		
	}
	
	@Bean
	public NotificationDao testNotificationDao() {
		return new NotificationDaoImpl();
	}
}
