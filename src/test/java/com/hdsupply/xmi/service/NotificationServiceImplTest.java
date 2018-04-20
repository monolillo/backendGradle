package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.enums.StockNotificationEnum;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.NotificationDao;

@RunWith(EasyMockRunner.class)
public class NotificationServiceImplTest extends EasyMockSupport{
	
	@TestSubject
	private NotificationServiceImpl testNotificationService = new NotificationServiceImpl();
	
	@Mock
	private NotificationDao notificationDao;
	
	@Mock
	private InventoryDao inventoryDao;
	
	@Test
	public void testGetAllNotifications() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		
		ProductCatalog productCatalogTrue1 = new ProductCatalog();
		productCatalogTrue1.setName("Avery Door Hangers With Tear Away Cards, 2 Cards P");
		productCatalogTrue1.setItemNumber(186335);
		productCatalogTrue1.setIdProduct(1090);
		productCatalogTrue1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue1.setMin(5);
		productCatalogTrue1.setMax(10);
		productCatalogTrue1.setCritical(true);
		productCatalogTrue1.setLocationId(8);
		productCatalogTrue1.setQuantity(0);
		productCatalogTrue1.setPrice(new BigDecimal("12.49"));
		
		ProductCatalog productCatalogFalse1 = new ProductCatalog();
		productCatalogFalse1.setName("Certified Safety 4-Shelf Class B First Aid Cabinet");
		productCatalogFalse1.setItemNumber(113644);
		productCatalogFalse1.setIdProduct(2000);
		productCatalogFalse1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse1.setMin(5);
		productCatalogFalse1.setMax(10);
		productCatalogFalse1.setCritical(false);
		productCatalogFalse1.setLocationId(8);
		productCatalogFalse1.setQuantity(0);
		productCatalogFalse1.setPrice(new BigDecimal("12.49"));
		
		ProductCatalog productCatalogTrue2 = new ProductCatalog();
		productCatalogTrue2.setName("Poulan Pro SAE 30 4-Cycle Engine Oil");
		productCatalogTrue2.setItemNumber(130254);
		productCatalogTrue2.setIdProduct(1092);
		productCatalogTrue2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue2.setMin(5);
		productCatalogTrue2.setMax(10);
		productCatalogTrue2.setCritical(true);
		productCatalogTrue2.setLocationId(8);
		productCatalogTrue2.setQuantity(2);
		productCatalogTrue2.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogFalse2 = new ProductCatalog();
		productCatalogFalse2.setName("Legionella Bacterium Water Testing Kit");
		productCatalogFalse2.setItemNumber(112287);
		productCatalogFalse2.setIdProduct(2002);
		productCatalogFalse2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse2.setMin(5);
		productCatalogFalse2.setMax(10);
		productCatalogFalse2.setCritical(false);
		productCatalogFalse2.setLocationId(8);
		productCatalogFalse2.setQuantity(4);
		productCatalogFalse2.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogTrue3 = new ProductCatalog();
		productCatalogTrue3.setName("11 Oz Liquid Wrench Industrial Chain Lubricant");
		productCatalogTrue3.setItemNumber(111593);
		productCatalogTrue3.setIdProduct(1094);
		productCatalogTrue3.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue3.setMin(5);
		productCatalogTrue3.setMax(10);
		productCatalogTrue3.setCritical(true);
		productCatalogTrue3.setLocationId(8);
		productCatalogTrue3.setQuantity(5);
		productCatalogTrue3.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogFalse3 = new ProductCatalog();
		productCatalogFalse3.setName("200 Mg Medi-First Ibuprofen 2 Tablets, Box Of 250");
		productCatalogFalse3.setItemNumber(132075);
		productCatalogFalse3.setIdProduct(2004);
		productCatalogFalse3.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse3.setMin(5);
		productCatalogFalse3.setMax(10);
		productCatalogFalse3.setCritical(false);
		productCatalogFalse3.setLocationId(8);
		productCatalogFalse3.setQuantity(5);
		productCatalogFalse3.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogTrue4 = new ProductCatalog();
		productCatalogTrue4.setName("14 Ounce High Temperature Grease Cartridge");
		productCatalogTrue4.setItemNumber(111267);
		productCatalogTrue4.setIdProduct(1098);
		productCatalogTrue4.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue4.setMin(5);
		productCatalogTrue4.setMax(10);
		productCatalogTrue4.setCritical(true);
		productCatalogTrue4.setLocationId(8);
		productCatalogTrue4.setQuantity(12);
		productCatalogTrue4.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogFalse4 = new ProductCatalog();
		productCatalogFalse4.setName("Burn Aid Burn Gel Package Of 25");
		productCatalogFalse4.setItemNumber(132068);
		productCatalogFalse4.setIdProduct(2008);
		productCatalogFalse4.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse4.setMin(5);
		productCatalogFalse4.setMax(10);
		productCatalogFalse4.setCritical(false);
		productCatalogFalse4.setLocationId(8);
		productCatalogFalse4.setQuantity(13);
		productCatalogFalse4.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedOutOfStockTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue1});
		List<ProductCatalog> returnedLessThanMinTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue2});
		List<ProductCatalog> returnedListMinThresholdTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue3});
		List<ProductCatalog> returnedListMaxThresholdTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue4});
		
		List<ProductCatalog> returnedOutOfStockFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse1});
		List<ProductCatalog> returnedLessThanMinFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse2});
		List<ProductCatalog> returnedListMinThresholdFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse3});
		List<ProductCatalog> returnedListMaxThresholdFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse4});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		
		EasyMock.expect(notificationDao.getListOutOfStock(filter)).andReturn(returnedOutOfStockTrueList);
		EasyMock.expect(notificationDao.getListLessThanMin(filter)).andReturn(returnedLessThanMinTrueList);
		EasyMock.expect(notificationDao.getListMinThreshold(filter)).andReturn(returnedListMinThresholdTrueList);
		EasyMock.expect(notificationDao.getListMaxThreshold(filter)).andReturn(returnedListMaxThresholdTrueList);
		
		EasyMock.expect(notificationDao.getListOutOfStock(filter)).andReturn(returnedOutOfStockFalseList);
		EasyMock.expect(notificationDao.getListLessThanMin(filter)).andReturn(returnedLessThanMinFalseList);
		EasyMock.expect(notificationDao.getListMinThreshold(filter)).andReturn(returnedListMinThresholdFalseList);
		EasyMock.expect(notificationDao.getListMaxThreshold(filter)).andReturn(returnedListMaxThresholdFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(1090, 4)).andReturn(0);
		EasyMock.expect(inventoryDao.getQuantity(1092, 4)).andReturn(2);
		EasyMock.expect(inventoryDao.getQuantity(1094, 4)).andReturn(5);
		EasyMock.expect(inventoryDao.getQuantity(1098, 4)).andReturn(12);
		EasyMock.expect(inventoryDao.getQuantity(2000, 4)).andReturn(0);
		EasyMock.expect(inventoryDao.getQuantity(2002, 4)).andReturn(4);
		EasyMock.expect(inventoryDao.getQuantity(2004, 4)).andReturn(5);
		EasyMock.expect(inventoryDao.getQuantity(2008, 4)).andReturn(13);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogTrue1.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogTrue1.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogTrue1.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogTrue1.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogTrue1.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogTrue1.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogTrue1.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogTrue1.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogTrue1.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogTrue1.getPrice(), listProductCatalog.get(0).getPrice());

		assertEquals(productCatalogTrue2.getName(), listProductCatalog.get(1).getName());
		assertEquals(productCatalogTrue2.getItemNumber(), listProductCatalog.get(1).getItemNumber());
		assertEquals(productCatalogTrue2.getIdProduct(), listProductCatalog.get(1).getIdProduct());
		assertEquals(productCatalogTrue2.getImageUrl(), listProductCatalog.get(1).getImageUrl());
		assertEquals(productCatalogTrue2.getMin(), listProductCatalog.get(1).getMin());
		assertEquals(productCatalogTrue2.getMax(), listProductCatalog.get(1).getMax());
		assertEquals(productCatalogTrue2.getCritical(), listProductCatalog.get(1).getCritical());
		assertEquals(productCatalogTrue2.getLocationId(), listProductCatalog.get(1).getLocationId());
		assertEquals(productCatalogTrue2.getQuantity(), listProductCatalog.get(1).getQuantity());
		assertEquals(productCatalogTrue2.getPrice(), listProductCatalog.get(1).getPrice());

		assertEquals(productCatalogTrue3.getName(), listProductCatalog.get(2).getName());
		assertEquals(productCatalogTrue3.getItemNumber(), listProductCatalog.get(2).getItemNumber());
		assertEquals(productCatalogTrue3.getIdProduct(), listProductCatalog.get(2).getIdProduct());
		assertEquals(productCatalogTrue3.getImageUrl(), listProductCatalog.get(2).getImageUrl());
		assertEquals(productCatalogTrue3.getMin(), listProductCatalog.get(2).getMin());
		assertEquals(productCatalogTrue3.getMax(), listProductCatalog.get(2).getMax());
		assertEquals(productCatalogTrue3.getCritical(), listProductCatalog.get(2).getCritical());
		assertEquals(productCatalogTrue3.getLocationId(), listProductCatalog.get(2).getLocationId());
		assertEquals(productCatalogTrue3.getQuantity(), listProductCatalog.get(2).getQuantity());
		assertEquals(productCatalogTrue3.getPrice(), listProductCatalog.get(2).getPrice());

		assertEquals(productCatalogFalse1.getName(), listProductCatalog.get(3).getName());
		assertEquals(productCatalogFalse1.getItemNumber(), listProductCatalog.get(3).getItemNumber());
		assertEquals(productCatalogFalse1.getIdProduct(), listProductCatalog.get(3).getIdProduct());
		assertEquals(productCatalogFalse1.getImageUrl(), listProductCatalog.get(3).getImageUrl());
		assertEquals(productCatalogFalse1.getMin(), listProductCatalog.get(3).getMin());
		assertEquals(productCatalogFalse1.getMax(), listProductCatalog.get(3).getMax());
		assertEquals(productCatalogFalse1.getCritical(), listProductCatalog.get(3).getCritical());
		assertEquals(productCatalogFalse1.getLocationId(), listProductCatalog.get(3).getLocationId());
		assertEquals(productCatalogFalse1.getQuantity(), listProductCatalog.get(3).getQuantity());
		assertEquals(productCatalogFalse1.getPrice(), listProductCatalog.get(3).getPrice());

		assertEquals(productCatalogFalse2.getName(), listProductCatalog.get(4).getName());
		assertEquals(productCatalogFalse2.getItemNumber(), listProductCatalog.get(4).getItemNumber());
		assertEquals(productCatalogFalse2.getIdProduct(), listProductCatalog.get(4).getIdProduct());
		assertEquals(productCatalogFalse2.getImageUrl(), listProductCatalog.get(4).getImageUrl());
		assertEquals(productCatalogFalse2.getMin(), listProductCatalog.get(4).getMin());
		assertEquals(productCatalogFalse2.getMax(), listProductCatalog.get(4).getMax());
		assertEquals(productCatalogFalse2.getCritical(), listProductCatalog.get(4).getCritical());
		assertEquals(productCatalogFalse2.getLocationId(), listProductCatalog.get(4).getLocationId());
		assertEquals(productCatalogFalse2.getQuantity(), listProductCatalog.get(4).getQuantity());
		assertEquals(productCatalogFalse2.getPrice(), listProductCatalog.get(4).getPrice());

		assertEquals(productCatalogFalse3.getName(), listProductCatalog.get(5).getName());
		assertEquals(productCatalogFalse3.getItemNumber(), listProductCatalog.get(5).getItemNumber());
		assertEquals(productCatalogFalse3.getIdProduct(), listProductCatalog.get(5).getIdProduct());
		assertEquals(productCatalogFalse3.getImageUrl(), listProductCatalog.get(5).getImageUrl());
		assertEquals(productCatalogFalse3.getMin(), listProductCatalog.get(5).getMin());
		assertEquals(productCatalogFalse3.getMax(), listProductCatalog.get(5).getMax());
		assertEquals(productCatalogFalse3.getCritical(), listProductCatalog.get(5).getCritical());
		assertEquals(productCatalogFalse3.getLocationId(), listProductCatalog.get(5).getLocationId());
		assertEquals(productCatalogFalse3.getQuantity(), listProductCatalog.get(5).getQuantity());
		assertEquals(productCatalogFalse3.getPrice(), listProductCatalog.get(5).getPrice());

		assertEquals(productCatalogTrue4.getName(), listProductCatalog.get(6).getName());
		assertEquals(productCatalogTrue4.getItemNumber(), listProductCatalog.get(6).getItemNumber());
		assertEquals(productCatalogTrue4.getIdProduct(), listProductCatalog.get(6).getIdProduct());
		assertEquals(productCatalogTrue4.getImageUrl(), listProductCatalog.get(6).getImageUrl());
		assertEquals(productCatalogTrue4.getMin(), listProductCatalog.get(6).getMin());
		assertEquals(productCatalogTrue4.getMax(), listProductCatalog.get(6).getMax());
		assertEquals(productCatalogTrue4.getCritical(), listProductCatalog.get(6).getCritical());
		assertEquals(productCatalogTrue4.getLocationId(), listProductCatalog.get(6).getLocationId());
		assertEquals(productCatalogTrue4.getQuantity(), listProductCatalog.get(6).getQuantity());
		assertEquals(productCatalogTrue4.getPrice(), listProductCatalog.get(6).getPrice());

		assertEquals(productCatalogFalse4.getName(), listProductCatalog.get(7).getName());
		assertEquals(productCatalogFalse4.getItemNumber(), listProductCatalog.get(7).getItemNumber());
		assertEquals(productCatalogFalse4.getIdProduct(), listProductCatalog.get(7).getIdProduct());
		assertEquals(productCatalogFalse4.getImageUrl(), listProductCatalog.get(7).getImageUrl());
		assertEquals(productCatalogFalse4.getMin(), listProductCatalog.get(7).getMin());
		assertEquals(productCatalogFalse4.getMax(), listProductCatalog.get(7).getMax());
		assertEquals(productCatalogFalse4.getCritical(), listProductCatalog.get(7).getCritical());
		assertEquals(productCatalogFalse4.getLocationId(), listProductCatalog.get(7).getLocationId());
		assertEquals(productCatalogFalse4.getQuantity(), listProductCatalog.get(7).getQuantity());
		assertEquals(productCatalogFalse4.getPrice(), listProductCatalog.get(7).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterCritical() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		
		ProductCatalog productCatalogTrue1 = new ProductCatalog();
		productCatalogTrue1.setName("Avery Door Hangers With Tear Away Cards, 2 Cards P");
		productCatalogTrue1.setItemNumber(186335);
		productCatalogTrue1.setIdProduct(1090);
		productCatalogTrue1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue1.setMin(5);
		productCatalogTrue1.setMax(10);
		productCatalogTrue1.setCritical(true);
		productCatalogTrue1.setLocationId(8);
		productCatalogTrue1.setQuantity(0);
		productCatalogTrue1.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogTrue2 = new ProductCatalog();
		productCatalogTrue2.setName("Poulan Pro SAE 30 4-Cycle Engine Oil");
		productCatalogTrue2.setItemNumber(130254);
		productCatalogTrue2.setIdProduct(1092);
		productCatalogTrue2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue2.setMin(5);
		productCatalogTrue2.setMax(10);
		productCatalogTrue2.setCritical(true);
		productCatalogTrue2.setLocationId(8);
		productCatalogTrue2.setQuantity(2);
		productCatalogTrue2.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogTrue3 = new ProductCatalog();
		productCatalogTrue3.setName("11 Oz Liquid Wrench Industrial Chain Lubricant");
		productCatalogTrue3.setItemNumber(111593);
		productCatalogTrue3.setIdProduct(1094);
		productCatalogTrue3.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue3.setMin(5);
		productCatalogTrue3.setMax(10);
		productCatalogTrue3.setCritical(true);
		productCatalogTrue3.setLocationId(8);
		productCatalogTrue3.setQuantity(5);
		productCatalogTrue3.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogTrue4 = new ProductCatalog();
		productCatalogTrue4.setName("14 Ounce High Temperature Grease Cartridge");
		productCatalogTrue4.setItemNumber(111267);
		productCatalogTrue4.setIdProduct(1098);
		productCatalogTrue4.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue4.setMin(5);
		productCatalogTrue4.setMax(10);
		productCatalogTrue4.setCritical(true);
		productCatalogTrue4.setLocationId(8);
		productCatalogTrue4.setQuantity(12);
		productCatalogTrue4.setPrice(new BigDecimal("12.49"));

		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setCritical(true);
		
		List<ProductCatalog> returnedOutOfStockTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue1});
		List<ProductCatalog> returnedLessThanMinTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue2});
		List<ProductCatalog> returnedListMinThresholdTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue3});
		List<ProductCatalog> returnedListMaxThresholdTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue4});
		
		EasyMock.expect(notificationDao.getListOutOfStock(filter)).andReturn(returnedOutOfStockTrueList);
		EasyMock.expect(notificationDao.getListLessThanMin(filter)).andReturn(returnedLessThanMinTrueList);
		EasyMock.expect(notificationDao.getListMinThreshold(filter)).andReturn(returnedListMinThresholdTrueList);
		EasyMock.expect(notificationDao.getListMaxThreshold(filter)).andReturn(returnedListMaxThresholdTrueList);
		
		EasyMock.expect(inventoryDao.getQuantity(1090, 4)).andReturn(0);
		EasyMock.expect(inventoryDao.getQuantity(1092, 4)).andReturn(2);
		EasyMock.expect(inventoryDao.getQuantity(1094, 4)).andReturn(5);
		EasyMock.expect(inventoryDao.getQuantity(1098, 4)).andReturn(12);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogTrue1.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogTrue1.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogTrue1.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogTrue1.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogTrue1.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogTrue1.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogTrue1.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogTrue1.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogTrue1.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogTrue1.getPrice(), listProductCatalog.get(0).getPrice());

		assertEquals(productCatalogTrue2.getName(), listProductCatalog.get(1).getName());
		assertEquals(productCatalogTrue2.getItemNumber(), listProductCatalog.get(1).getItemNumber());
		assertEquals(productCatalogTrue2.getIdProduct(), listProductCatalog.get(1).getIdProduct());
		assertEquals(productCatalogTrue2.getImageUrl(), listProductCatalog.get(1).getImageUrl());
		assertEquals(productCatalogTrue2.getMin(), listProductCatalog.get(1).getMin());
		assertEquals(productCatalogTrue2.getMax(), listProductCatalog.get(1).getMax());
		assertEquals(productCatalogTrue2.getCritical(), listProductCatalog.get(1).getCritical());
		assertEquals(productCatalogTrue2.getLocationId(), listProductCatalog.get(1).getLocationId());
		assertEquals(productCatalogTrue2.getQuantity(), listProductCatalog.get(1).getQuantity());
		assertEquals(productCatalogTrue2.getPrice(), listProductCatalog.get(1).getPrice());

		assertEquals(productCatalogTrue3.getName(), listProductCatalog.get(2).getName());
		assertEquals(productCatalogTrue3.getItemNumber(), listProductCatalog.get(2).getItemNumber());
		assertEquals(productCatalogTrue3.getIdProduct(), listProductCatalog.get(2).getIdProduct());
		assertEquals(productCatalogTrue3.getImageUrl(), listProductCatalog.get(2).getImageUrl());
		assertEquals(productCatalogTrue3.getMin(), listProductCatalog.get(2).getMin());
		assertEquals(productCatalogTrue3.getMax(), listProductCatalog.get(2).getMax());
		assertEquals(productCatalogTrue3.getCritical(), listProductCatalog.get(2).getCritical());
		assertEquals(productCatalogTrue3.getLocationId(), listProductCatalog.get(2).getLocationId());
		assertEquals(productCatalogTrue3.getLocationId(), listProductCatalog.get(2).getLocationId());
		assertEquals(productCatalogTrue3.getQuantity(), listProductCatalog.get(2).getQuantity());
		assertEquals(productCatalogTrue3.getPrice(), listProductCatalog.get(2).getPrice());

		assertEquals(productCatalogTrue4.getName(), listProductCatalog.get(3).getName());
		assertEquals(productCatalogTrue4.getItemNumber(), listProductCatalog.get(3).getItemNumber());
		assertEquals(productCatalogTrue4.getIdProduct(), listProductCatalog.get(3).getIdProduct());
		assertEquals(productCatalogTrue4.getImageUrl(), listProductCatalog.get(3).getImageUrl());
		assertEquals(productCatalogTrue4.getMin(), listProductCatalog.get(3).getMin());
		assertEquals(productCatalogTrue4.getMax(), listProductCatalog.get(3).getMax());
		assertEquals(productCatalogTrue4.getCritical(), listProductCatalog.get(3).getCritical());
		assertEquals(productCatalogTrue4.getLocationId(), listProductCatalog.get(3).getLocationId());
		assertEquals(productCatalogTrue4.getLocationId(), listProductCatalog.get(3).getLocationId());
		assertEquals(productCatalogTrue4.getQuantity(), listProductCatalog.get(3).getQuantity());
		assertEquals(productCatalogTrue4.getPrice(), listProductCatalog.get(3).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterOutOfStock() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		
		ProductCatalog productCatalogTrue1 = new ProductCatalog();
		productCatalogTrue1.setName("Avery Door Hangers With Tear Away Cards, 2 Cards P");
		productCatalogTrue1.setItemNumber(186335);
		productCatalogTrue1.setIdProduct(1090);
		productCatalogTrue1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue1.setMin(5);
		productCatalogTrue1.setMax(10);
		productCatalogTrue1.setCritical(true);
		productCatalogTrue1.setLocationId(8);
		productCatalogTrue1.setQuantity(0);
		productCatalogTrue1.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogFalse1 = new ProductCatalog();
		productCatalogFalse1.setName("Certified Safety 4-Shelf Class B First Aid Cabinet");
		productCatalogFalse1.setItemNumber(113644);
		productCatalogFalse1.setIdProduct(2000);
		productCatalogFalse1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse1.setMin(5);
		productCatalogFalse1.setMax(10);
		productCatalogFalse1.setCritical(false);
		productCatalogFalse1.setLocationId(8);
		productCatalogFalse1.setQuantity(0);
		productCatalogFalse1.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedOutOfStockTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue1});
		List<ProductCatalog> returnedOutOfStockFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse1});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.OUTOFSTOCK);
		
		EasyMock.expect(notificationDao.getListOutOfStock(filter)).andReturn(returnedOutOfStockTrueList);
		EasyMock.expect(notificationDao.getListOutOfStock(filter)).andReturn(returnedOutOfStockFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(1090, 4)).andReturn(0);
		EasyMock.expect(inventoryDao.getQuantity(2000, 4)).andReturn(0);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogTrue1.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogTrue1.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogTrue1.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogTrue1.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogTrue1.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogTrue1.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogTrue1.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogTrue1.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogTrue1.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogTrue1.getPrice(), listProductCatalog.get(0).getPrice());

		assertEquals(productCatalogFalse1.getName(), listProductCatalog.get(1).getName());
		assertEquals(productCatalogFalse1.getItemNumber(), listProductCatalog.get(1).getItemNumber());
		assertEquals(productCatalogFalse1.getIdProduct(), listProductCatalog.get(1).getIdProduct());
		assertEquals(productCatalogFalse1.getImageUrl(), listProductCatalog.get(1).getImageUrl());
		assertEquals(productCatalogFalse1.getMin(), listProductCatalog.get(1).getMin());
		assertEquals(productCatalogFalse1.getMax(), listProductCatalog.get(1).getMax());
		assertEquals(productCatalogFalse1.getCritical(), listProductCatalog.get(1).getCritical());
		assertEquals(productCatalogFalse1.getLocationId(), listProductCatalog.get(1).getLocationId());
		assertEquals(productCatalogFalse1.getQuantity(), listProductCatalog.get(1).getQuantity());
		assertEquals(productCatalogFalse1.getPrice(), listProductCatalog.get(1).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterLowInventory() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		
		ProductCatalog productCatalogTrue2 = new ProductCatalog();
		productCatalogTrue2.setName("Poulan Pro SAE 30 4-Cycle Engine Oil");
		productCatalogTrue2.setItemNumber(130254);
		productCatalogTrue2.setIdProduct(1092);
		productCatalogTrue2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue2.setMin(5);
		productCatalogTrue2.setMax(10);
		productCatalogTrue2.setCritical(true);
		productCatalogTrue2.setLocationId(8);
		productCatalogTrue2.setQuantity(2);
		productCatalogTrue2.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogFalse2 = new ProductCatalog();
		productCatalogFalse2.setName("Legionella Bacterium Water Testing Kit");
		productCatalogFalse2.setItemNumber(112287);
		productCatalogFalse2.setIdProduct(2002);
		productCatalogFalse2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse2.setMin(5);
		productCatalogFalse2.setMax(10);
		productCatalogFalse2.setCritical(false);
		productCatalogFalse2.setLocationId(8);
		productCatalogFalse2.setQuantity(4);
		productCatalogFalse2.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedLessThanMinTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue2});
		List<ProductCatalog> returnedLessThanMinFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse2});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.LOWINVENTORY);
		
		EasyMock.expect(notificationDao.getListLessThanMin(filter)).andReturn(returnedLessThanMinTrueList);
		EasyMock.expect(notificationDao.getListLessThanMin(filter)).andReturn(returnedLessThanMinFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(1092, 4)).andReturn(2);
		EasyMock.expect(inventoryDao.getQuantity(2002, 4)).andReturn(4);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogTrue2.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogTrue2.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogTrue2.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogTrue2.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogTrue2.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogTrue2.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogTrue2.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogTrue2.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogTrue2.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogTrue2.getPrice(), listProductCatalog.get(0).getPrice());

		assertEquals(productCatalogFalse2.getName(), listProductCatalog.get(1).getName());
		assertEquals(productCatalogFalse2.getItemNumber(), listProductCatalog.get(1).getItemNumber());
		assertEquals(productCatalogFalse2.getIdProduct(), listProductCatalog.get(1).getIdProduct());
		assertEquals(productCatalogFalse2.getImageUrl(), listProductCatalog.get(1).getImageUrl());
		assertEquals(productCatalogFalse2.getMin(), listProductCatalog.get(1).getMin());
		assertEquals(productCatalogFalse2.getMax(), listProductCatalog.get(1).getMax());
		assertEquals(productCatalogFalse2.getCritical(), listProductCatalog.get(1).getCritical());
		assertEquals(productCatalogFalse2.getLocationId(), listProductCatalog.get(1).getLocationId());
		assertEquals(productCatalogFalse2.getQuantity(), listProductCatalog.get(1).getQuantity());
		assertEquals(productCatalogFalse2.getPrice(), listProductCatalog.get(1).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterMinThreshold() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		
		ProductCatalog productCatalogTrue3 = new ProductCatalog();
		productCatalogTrue3.setName("11 Oz Liquid Wrench Industrial Chain Lubricant");
		productCatalogTrue3.setItemNumber(111593);
		productCatalogTrue3.setIdProduct(1094);
		productCatalogTrue3.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue3.setMin(5);
		productCatalogTrue3.setMax(10);
		productCatalogTrue3.setCritical(true);
		productCatalogTrue3.setLocationId(8);
		productCatalogTrue3.setQuantity(5);
		productCatalogTrue3.setPrice(new BigDecimal("12.49"));

		ProductCatalog productCatalogFalse3 = new ProductCatalog();
		productCatalogFalse3.setName("200 Mg Medi-First Ibuprofen 2 Tablets, Box Of 250");
		productCatalogFalse3.setItemNumber(132075);
		productCatalogFalse3.setIdProduct(2004);
		productCatalogFalse3.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse3.setMin(5);
		productCatalogFalse3.setMax(10);
		productCatalogFalse3.setCritical(false);
		productCatalogFalse3.setLocationId(8);
		productCatalogFalse3.setQuantity(5);
		productCatalogFalse3.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedListMinThresholdTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue3});
		List<ProductCatalog> returnedListMinThresholdFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse3});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.MINTHRESHOLD);
		
		EasyMock.expect(notificationDao.getListMinThreshold(filter)).andReturn(returnedListMinThresholdTrueList);
		EasyMock.expect(notificationDao.getListMinThreshold(filter)).andReturn(returnedListMinThresholdFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(1094, 4)).andReturn(5);
		EasyMock.expect(inventoryDao.getQuantity(2004, 4)).andReturn(5);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogTrue3.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogTrue3.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogTrue3.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogTrue3.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogTrue3.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogTrue3.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogTrue3.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogTrue3.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogTrue3.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogTrue3.getPrice(), listProductCatalog.get(0).getPrice());

		assertEquals(productCatalogFalse3.getName(), listProductCatalog.get(1).getName());
		assertEquals(productCatalogFalse3.getItemNumber(), listProductCatalog.get(1).getItemNumber());
		assertEquals(productCatalogFalse3.getIdProduct(), listProductCatalog.get(1).getIdProduct());
		assertEquals(productCatalogFalse3.getImageUrl(), listProductCatalog.get(1).getImageUrl());
		assertEquals(productCatalogFalse3.getMin(), listProductCatalog.get(1).getMin());
		assertEquals(productCatalogFalse3.getMax(), listProductCatalog.get(1).getMax());
		assertEquals(productCatalogFalse3.getCritical(), listProductCatalog.get(1).getCritical());
		assertEquals(productCatalogFalse3.getLocationId(), listProductCatalog.get(1).getLocationId());
		assertEquals(productCatalogFalse3.getQuantity(), listProductCatalog.get(1).getQuantity());
		assertEquals(productCatalogFalse3.getPrice(), listProductCatalog.get(1).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterMaxThreshold() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		
		ProductCatalog productCatalogTrue4 = new ProductCatalog();
		productCatalogTrue4.setName("14 Ounce High Temperature Grease Cartridge");
		productCatalogTrue4.setItemNumber(111267);
		productCatalogTrue4.setIdProduct(1098);
		productCatalogTrue4.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogTrue4.setMin(5);
		productCatalogTrue4.setMax(10);
		productCatalogTrue4.setCritical(true);
		productCatalogTrue4.setLocationId(8);
		productCatalogTrue4.setQuantity(12);
		productCatalogTrue4.setPrice(new BigDecimal("12.49"));
		
		ProductCatalog productCatalogFalse4 = new ProductCatalog();
		productCatalogFalse4.setName("Burn Aid Burn Gel Package Of 25");
		productCatalogFalse4.setItemNumber(132068);
		productCatalogFalse4.setIdProduct(2008);
		productCatalogFalse4.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse4.setMin(5);
		productCatalogFalse4.setMax(10);
		productCatalogFalse4.setCritical(false);
		productCatalogFalse4.setLocationId(8);
		productCatalogFalse4.setQuantity(13);
		productCatalogFalse4.setPrice(new BigDecimal("12.49"));
		
		List<ProductCatalog> returnedListMaxThresholdTrueList = Arrays.asList(new ProductCatalog[] {productCatalogTrue4});
		List<ProductCatalog> returnedListMaxThresholdFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse4});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.INVENTORYEXCESS);
		
		EasyMock.expect(notificationDao.getListMaxThreshold(filter)).andReturn(returnedListMaxThresholdTrueList);
		EasyMock.expect(notificationDao.getListMaxThreshold(filter)).andReturn(returnedListMaxThresholdFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(1098, 4)).andReturn(12);
		EasyMock.expect(inventoryDao.getQuantity(2008, 4)).andReturn(13);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogTrue4.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogTrue4.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogTrue4.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogTrue4.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogTrue4.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogTrue4.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogTrue4.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogTrue4.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogTrue4.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogTrue4.getPrice(), listProductCatalog.get(0).getPrice());

		assertEquals(productCatalogFalse4.getName(), listProductCatalog.get(1).getName());
		assertEquals(productCatalogFalse4.getItemNumber(), listProductCatalog.get(1).getItemNumber());
		assertEquals(productCatalogFalse4.getIdProduct(), listProductCatalog.get(1).getIdProduct());
		assertEquals(productCatalogFalse4.getImageUrl(), listProductCatalog.get(1).getImageUrl());
		assertEquals(productCatalogFalse4.getMin(), listProductCatalog.get(1).getMin());
		assertEquals(productCatalogFalse4.getMax(), listProductCatalog.get(1).getMax());
		assertEquals(productCatalogFalse4.getCritical(), listProductCatalog.get(1).getCritical());
		assertEquals(productCatalogFalse4.getLocationId(), listProductCatalog.get(1).getLocationId());
		assertEquals(productCatalogFalse4.getQuantity(), listProductCatalog.get(1).getQuantity());
		assertEquals(productCatalogFalse4.getPrice(), listProductCatalog.get(1).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterOutOfStockCritical() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();

		ProductCatalog productCatalogFalse1 = new ProductCatalog();
		productCatalogFalse1.setName("Certified Safety 4-Shelf Class B First Aid Cabinet");
		productCatalogFalse1.setItemNumber(113644);
		productCatalogFalse1.setIdProduct(2000);
		productCatalogFalse1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse1.setMin(5);
		productCatalogFalse1.setMax(10);
		productCatalogFalse1.setCritical(false);
		productCatalogFalse1.setLocationId(8);
		productCatalogFalse1.setQuantity(0);
		productCatalogFalse1.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedOutOfStockFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse1});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.OUTOFSTOCK);
		filter.setCritical(false);
		
		EasyMock.expect(notificationDao.getListOutOfStock(filter)).andReturn(returnedOutOfStockFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(2000, 4)).andReturn(0);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();

		assertEquals(productCatalogFalse1.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogFalse1.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogFalse1.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogFalse1.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogFalse1.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogFalse1.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogFalse1.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogFalse1.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogFalse1.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogFalse1.getPrice(), listProductCatalog.get(0).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterLowInventoryCritical() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();

		ProductCatalog productCatalogFalse2 = new ProductCatalog();
		productCatalogFalse2.setName("Legionella Bacterium Water Testing Kit");
		productCatalogFalse2.setItemNumber(112287);
		productCatalogFalse2.setIdProduct(2002);
		productCatalogFalse2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse2.setMin(5);
		productCatalogFalse2.setMax(10);
		productCatalogFalse2.setCritical(false);
		productCatalogFalse2.setLocationId(8);
		productCatalogFalse2.setQuantity(4);
		productCatalogFalse2.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedLessThanMinFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse2});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.LOWINVENTORY);
		filter.setCritical(false);
		
		EasyMock.expect(notificationDao.getListLessThanMin(filter)).andReturn(returnedLessThanMinFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(2002, 4)).andReturn(4);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();

		assertEquals(productCatalogFalse2.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogFalse2.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogFalse2.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogFalse2.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogFalse2.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogFalse2.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogFalse2.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogFalse2.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogFalse2.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogFalse2.getPrice(), listProductCatalog.get(0).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterMinThresholdCritical() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();

		ProductCatalog productCatalogFalse3 = new ProductCatalog();
		productCatalogFalse3.setName("200 Mg Medi-First Ibuprofen 2 Tablets, Box Of 250");
		productCatalogFalse3.setItemNumber(132075);
		productCatalogFalse3.setIdProduct(2004);
		productCatalogFalse3.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse3.setMin(5);
		productCatalogFalse3.setMax(10);
		productCatalogFalse3.setCritical(false);
		productCatalogFalse3.setLocationId(8);
		productCatalogFalse3.setQuantity(5);
		productCatalogFalse3.setPrice(new BigDecimal("12.49"));

		List<ProductCatalog> returnedListMinThresholdFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse3});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.MINTHRESHOLD);
		filter.setCritical(false);
		
		EasyMock.expect(notificationDao.getListMinThreshold(filter)).andReturn(returnedListMinThresholdFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(2004, 4)).andReturn(5);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();

		assertEquals(productCatalogFalse3.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogFalse3.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogFalse3.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogFalse3.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogFalse3.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogFalse3.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogFalse3.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogFalse3.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogFalse3.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogFalse3.getPrice(), listProductCatalog.get(0).getPrice());

	}
	
	@Test
	public void testGetNotificationsFilterMaxThresholdCritical() {
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();

		ProductCatalog productCatalogFalse4 = new ProductCatalog();
		productCatalogFalse4.setName("Burn Aid Burn Gel Package Of 25");
		productCatalogFalse4.setItemNumber(132068);
		productCatalogFalse4.setIdProduct(2008);
		productCatalogFalse4.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p");
		productCatalogFalse4.setMin(5);
		productCatalogFalse4.setMax(10);
		productCatalogFalse4.setCritical(false);
		productCatalogFalse4.setLocationId(8);
		productCatalogFalse4.setQuantity(13);
		productCatalogFalse4.setPrice(new BigDecimal("12.49"));
		
		List<ProductCatalog> returnedListMaxThresholdFalseList = Arrays.asList(new ProductCatalog[] {productCatalogFalse4});
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.INVENTORYEXCESS);
		filter.setCritical(false);
		
		EasyMock.expect(notificationDao.getListMaxThreshold(filter)).andReturn(returnedListMaxThresholdFalseList);
		
		EasyMock.expect(inventoryDao.getQuantity(2008, 4)).andReturn(13);
		
		replayAll();
		
		listProductCatalog = testNotificationService.getNotifications(filter);
		
		verifyAll();
		
		assertEquals(productCatalogFalse4.getName(), listProductCatalog.get(0).getName());
		assertEquals(productCatalogFalse4.getItemNumber(), listProductCatalog.get(0).getItemNumber());
		assertEquals(productCatalogFalse4.getIdProduct(), listProductCatalog.get(0).getIdProduct());
		assertEquals(productCatalogFalse4.getImageUrl(), listProductCatalog.get(0).getImageUrl());
		assertEquals(productCatalogFalse4.getMin(), listProductCatalog.get(0).getMin());
		assertEquals(productCatalogFalse4.getMax(), listProductCatalog.get(0).getMax());
		assertEquals(productCatalogFalse4.getCritical(), listProductCatalog.get(0).getCritical());
		assertEquals(productCatalogFalse4.getLocationId(), listProductCatalog.get(0).getLocationId());
		assertEquals(productCatalogFalse4.getQuantity(), listProductCatalog.get(0).getQuantity());
		assertEquals(productCatalogFalse4.getPrice(), listProductCatalog.get(0).getPrice());
	}
}
