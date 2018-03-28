package com.hdsupply.xmi.resource;

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

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.service.InventoryService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;;

@ContextConfiguration(classes = {InventoryControllerTest.class})
public class InventoryControllerTest extends ControllerTestBase{
	
	@Autowired
	private InventoryService mockInventoryService;
	
	@Before
	public void setUp() throws Exception {
		
        super.setUp();		
        
        EasyMock.reset(mockInventoryService);
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "CHECK_IN_PRODUCT" })
	public void testCheckInProduct() throws Exception {
		
		Inventory inventory = new Inventory();
		inventory.setLocationId(2);
		inventory.setProductId(3);
		inventory.setQuantity(5);
		inventory.setShopId(2);
		
		Capture<Inventory> captured = EasyMock.newCapture();
		
		mockInventoryService.checkInProduct(EasyMock.capture(captured));
		EasyMock.replay(mockInventoryService);
		
		File file = ResourceUtils.getFile("classpath:request/requestCheckInProduct.json");
		String requestBody = new String(Files.readAllBytes(file.toPath()));
		
		mockMvc.perform(post("/rest/shop/2/product/3/checkin").
				contentType(MediaType.APPLICATION_JSON_UTF8).
				content(requestBody)
				).andExpect(status().isCreated());
		
		EasyMock.verify(mockInventoryService);
		
		assertEquals(inventory.getLocationId(), captured.getValue().getLocationId());
		assertEquals(inventory.getProductId(), captured.getValue().getProductId());
		assertEquals(inventory.getQuantity(), captured.getValue().getQuantity());
		assertEquals(inventory.getShopId(), captured.getValue().getShopId());
		
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION" })
	public void testCheckInProductUnauthorized() throws Exception {
		
		File file = ResourceUtils.getFile("classpath:request/requestCheckInProduct.json");
		String requestBody = new String(Files.readAllBytes(file.toPath()));
		
		mockMvc.perform(post("/rest/shop/2/product/3/checkin").
				contentType(MediaType.APPLICATION_JSON_UTF8).
				content(requestBody)
				).andExpect(status().isForbidden());
		
	}
	
	@Bean
	public InventoryService inventoryService() {
		return EasyMock.createStrictMock(InventoryService.class);
	}
	
	@Bean
	public InventoryController mockInventoryCatalog() {
		return new InventoryController();
	}
	
}
