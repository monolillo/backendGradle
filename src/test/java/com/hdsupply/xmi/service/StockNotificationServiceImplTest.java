package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.domain.XmiUser;
import com.hdsupply.xmi.repository.IftttDao;
import com.hdsupply.xmi.service.security.XmiUserService;

@RunWith(EasyMockRunner.class)
public class StockNotificationServiceImplTest extends EasyMockSupport{
	
	@TestSubject
	private StockNotificationServiceImpl stockNotificationServiceImplTest = new StockNotificationServiceImpl();
	
	@Mock
	private UserDetailsService userDetailsService;
	
	@Mock
	private XmiUserService xmiUserService;
	
	@Mock
	private IftttDao iftttDao;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private SiteService siteService; 
	
	@Test
	public void testDoNotification() throws IOException {
		
		stockNotificationServiceImplTest.setEmailTemplateLocation("classpath:templatesTest/stockEmailTest.html");
		
		Site site = new Site();
		site.setId(2);
		site.setName("Courtyard by Marriott Atlanta Cumberland/Galleria");
		site.setCompanyId(2);
		
		EasyMock.expect(siteService.getSiteByIdShop(2)).andReturn(site);
		
		ProductCatalog productCatalog = new ProductCatalog();
		productCatalog.setIdProduct(123);
		productCatalog.setName("A Bulb 40W A15 Frost");
		productCatalog.setItemNumber(2);
		productCatalog.setMax(10);
		productCatalog.setMin(5);
		productCatalog.setQuantity(0);
		
		XmiUser xmiUser1 = new XmiUser("admin", "123", "rodrigo.masanes@gmail.com", "11111", AuthorityUtils.NO_AUTHORITIES);
		XmiUser xmiUser2 = new XmiUser("admin", "123", "rodrigo.masanes@neoris.com", "11111", AuthorityUtils.NO_AUTHORITIES);
		
		List<XmiUser> listXmiUsers = Arrays.asList(new XmiUser[] {xmiUser1, xmiUser2});
		
		EasyMock.expect(productService.getProductById(2,2)).andReturn(productCatalog);
		
		EasyMock.expect(xmiUserService.loadUsersEmailBySiteId(4, "STOCK_PUSH_ALERTS")).andReturn(listXmiUsers);
		
		Capture<String> capturedEvent = EasyMock.newCapture(); 
		Capture<String> captureValue1 = EasyMock.newCapture();
		Capture<String> captureValue2 = EasyMock.newCapture();
		Capture<String> captureValue3 = EasyMock.newCapture();
		
		iftttDao.tiggerEvent(EasyMock.capture(capturedEvent), EasyMock.capture(captureValue1), EasyMock.capture(captureValue2), EasyMock.capture(captureValue3));		
		
		replayAll();
		
		stockNotificationServiceImplTest.doNotification("admin", 2, 2);
		
		verifyAll();
	
		assertEquals("xmi_critical_low", capturedEvent.getValue());
		assertEquals("rodrigo.masanes@gmail.com,rodrigo.masanes@neoris.com,",captureValue1.getValue());
		assertEquals("Product 2 is Out of Stock",captureValue2.getValue());
		assertEquals("2.0.A Bulb 40W A15 Frost.5.Out of Stock",captureValue3.getValue());
		
	}
	
	@Test
	public void testDoNotification2() throws IOException{
		
		stockNotificationServiceImplTest.setEmailTemplateLocation("classpath:templatesTest/stockEmailTest.html");
		
		Site site = new Site();
		site.setId(2);
		site.setName("Courtyard by Marriott Atlanta Cumberland/Galleria");
		site.setCompanyId(2);
		
		EasyMock.expect(siteService.getSiteByIdShop(2)).andReturn(site);
		
		ProductCatalog productCatalog2 = new ProductCatalog();
		productCatalog2.setIdProduct(123);
		productCatalog2.setName("A Bulb 40W A15 Frost");
		productCatalog2.setItemNumber(2);
		productCatalog2.setMax(10);
		productCatalog2.setMin(5);
		productCatalog2.setQuantity(3);
		
		XmiUser xmiUser1 = new XmiUser("admin", "123", "rodrigo.masanes@gmail.com", "11111", AuthorityUtils.NO_AUTHORITIES);
		XmiUser xmiUser2 = new XmiUser("admin", "123", "rodrigo.masanes@neoris.com", "11111", AuthorityUtils.NO_AUTHORITIES);
		
		List<XmiUser> listXmiUsers = Arrays.asList(new XmiUser[] {xmiUser1, xmiUser2});
		
		EasyMock.expect(productService.getProductById(2,2)).andReturn(productCatalog2);
		
		EasyMock.expect(xmiUserService.loadUsersEmailBySiteId(4, "STOCK_PUSH_ALERTS")).andReturn(listXmiUsers);
		
		Capture<String> capturedEvent = EasyMock.newCapture(); 
		Capture<String> captureValue1 = EasyMock.newCapture();
		Capture<String> captureValue2 = EasyMock.newCapture();
		Capture<String> captureValue3 = EasyMock.newCapture();
		
		iftttDao.tiggerEvent(EasyMock.capture(capturedEvent), EasyMock.capture(captureValue1), EasyMock.capture(captureValue2), EasyMock.capture(captureValue3));		
		
		replayAll();
		
		stockNotificationServiceImplTest.doNotification("admin", 2, 2);
		
		verifyAll();
	
		assertEquals("xmi_critical_low", capturedEvent.getValue());
		assertEquals("rodrigo.masanes@gmail.com,rodrigo.masanes@neoris.com,",captureValue1.getValue());
		assertEquals("Product 2 is Running low",captureValue2.getValue());
		assertEquals ("2.3.A Bulb 40W A15 Frost.5.Running low",captureValue3.getValue());
		
	}

}
