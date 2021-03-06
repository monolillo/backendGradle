package com.hdsupply.xmi.resource;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.enums.StockNotificationEnum;
import com.hdsupply.xmi.service.NotificationEmailService;
import com.hdsupply.xmi.service.NotificationService;

@ContextConfiguration(classes = {NotificationControllerTest.class})
public class NotificationControllerTest extends ControllerTestBase {
	
	@Autowired
	private NotificationService mockNotificationService;
	
	@Autowired
	private NotificationEmailService mockNotificationEmailService;
	
	@Before
	public void setUp() throws Exception {
		
        super.setUp();		
        
        EasyMock.reset(mockNotificationService);
        EasyMock.reset(mockNotificationEmailService);
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "NOTIFICATION" })
	public void testGetNotification() throws Exception {
		
		ProductCatalog productCatalog1 = new ProductCatalog();
		productCatalog1.setIdProduct(3510);
		productCatalog1.setItemNumber(265699);
		productCatalog1.setPrice(new BigDecimal("49.99"));
		productCatalog1.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/product/fm/enlarged/26/265699_PT_600px_EPS_JPG.jpg");
		productCatalog1.setMax(10);
		productCatalog1.setMin(5);
		productCatalog1.setName("No Trespassing Designer Sign, Burgundy on Ivory, Non-Reflective, 16 x 16");
		productCatalog1.setCritical(true);
		productCatalog1.setQuantity(2);
		productCatalog1.setLocationId(10);
		
		ProductCatalog productCatalog2 = new ProductCatalog();
		productCatalog2.setIdProduct(3520);
		productCatalog2.setItemNumber(266015);
		productCatalog2.setPrice(new BigDecimal("14.99"));
		productCatalog2.setImageUrl("https://hdsupplysolutions.com/wcsstore/ThdsMroUs/product/fm/enlarged/26/266015_PT_600px_EPS_JPG.jpg");
		productCatalog2.setMax(10);
		productCatalog2.setMin(5);
		productCatalog2.setName("Right Arrow Directional Sign, Green, 3 x 3");
		productCatalog2.setCritical(false);
		productCatalog2.setQuantity(2);
		productCatalog2.setLocationId(10);
		
		Capture<FilterNotification> captured = EasyMock.newCapture();
		
		List<ProductCatalog> returnedProductCatalog = Arrays.asList(new ProductCatalog[] {productCatalog1, productCatalog2});
		
		EasyMock.expect(mockNotificationService.getNotifications(EasyMock.capture(captured))).andReturn(returnedProductCatalog);
		
		EasyMock.replay(mockNotificationService);
		
		File file = ResourceUtils.getFile("classpath:json/getAllNotifications.json");
		String expectedJson = new String(Files.readAllBytes(file.toPath()));	
		
		mockMvc.perform(get("/rest/site/4/product/notification")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockNotificationService);
		
		assertEquals(productCatalog1, returnedProductCatalog.get(0));
		assertEquals(productCatalog2, returnedProductCatalog.get(1));
		assertEquals((Integer)4, captured.getValue().getSiteId());
		
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "SEND" })
	public void testSendNotification() throws Exception {
		
		FilterNotification filter = new FilterNotification();
		filter.setCritical(true);
		filter.setSiteId(4);
		filter.setStockNotification(StockNotificationEnum.INVENTORYEXCESS);
		filter.setUser("monito");
		
		Capture<FilterNotification> filterCapture = EasyMock.newCapture();
		
		mockNotificationEmailService.emailNotifications(EasyMock.capture(filterCapture));
		
		EasyMock.replay(mockNotificationService);
		
		File file = ResourceUtils.getFile("classpath:request/requestSendNotification.json");
		String requestBody = new String(Files.readAllBytes(file.toPath()));
		
		mockMvc.perform(post("/rest/site/4/product/notification/send")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(requestBody))
				.andExpect(status().isOk());
		
		EasyMock.verify(mockNotificationService);
		
	}
	
	@Bean
	public NotificationService notificationService() {
		return EasyMock.createStrictMock(NotificationService.class);
	}
	
	@Bean
	public NotificationEmailService notificationEmailService() {
		return EasyMock.createStrictMock(NotificationEmailService.class);
	}
	
	@Bean
	public NotificationController mockNotificationService() {
		return new NotificationController();
	}

}
