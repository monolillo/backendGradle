package com.hdsupply.xmi.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.Shop;
import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.service.SiteService;

import junit.framework.AssertionFailedError;

@ContextConfiguration(classes = {SiteControllerTest.class})
public class SiteControllerTest extends ControllerTestBase{
	
	@Autowired
	private SiteService mockSiteService;
	
	@Before
	public void setUp() throws Exception {
		
        super.setUp();		
        
        EasyMock.reset(mockSiteService);
        
	}	
	
	@Test
	@WithMockUser(username = "admin", authorities = { "USER_SITE" })
	public void testGetSiteByUser() throws Exception {
		
		Site site = new Site();
		site.setId(2);
		site.setName("Courtyard by Marriott Atlanta Cumberland/Galleria");
		site.setCompanyId(2);
		
		List<Site> returnedSite = Arrays.asList(new Site[] {site});

		Shop shop1 = new Shop();
		shop1.setId(2);
		shop1.setName("Maintenance");
		shop1.setSiteId(2);

		Shop shop2 = new Shop();
		shop2.setId(3);
		shop2.setName("Storage");
		shop2.setSiteId(2);
		
		List<Shop> returnedShop = Arrays.asList(new Shop[] {shop1, shop2});
		
		site.setShops(returnedShop);
		
		EasyMock.expect(mockSiteService.getSiteByUser("admin")).andReturn(returnedSite);
		
		EasyMock.replay(mockSiteService);
		
		File file = ResourceUtils.getFile("classpath:json/getSiteByUser.json");
		String expectedJson = new String(Files.readAllBytes(file.toPath()));	
		
		mockMvc.perform(get("/rest/user/admin/site")
			.header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedJson));
		
		EasyMock.verify(mockSiteService);
		
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = { "OTHER_PERMISSION" })
	public void testGetSiteByUserUnauthorized() throws Exception {
		

		EasyMock.expect(mockSiteService.getSiteByUser("user")).andThrow(new AssertionFailedError())
			.anyTimes();
	
		EasyMock.replay(mockSiteService);
		
		mockMvc.perform(get("/rest/user/user/site")
			.header("Accept", "application/json"))
			.andExpect(status().isForbidden());
		
		EasyMock.verify(mockSiteService);		
	}
	
	@Bean
	public SiteService mockSiteService() {
		return EasyMock.createStrictMock(SiteService.class);
	}
	
	@Bean
	public SiteController fixture() {
		return new SiteController();
	}
}
