package com.hdsupply.xmi.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.domain.XmiUser;
import com.hdsupply.xmi.repository.AzureBlobDao;
import com.hdsupply.xmi.repository.IftttDao;
import com.hdsupply.xmi.service.excel.ExcelService;

@RunWith(EasyMockRunner.class)
public class NotificationEmailServiceImplTest extends EasyMockSupport{
	
	@TestSubject
	private NotificationEmailServiceImpl fixture = new NotificationEmailServiceImpl();
	
	@Mock
	private AzureBlobDao blobDao;
	
	@Mock
	private ExcelService excelService;
	
	@Mock
	private IftttDao iftttDao;
	
	@Mock
	private NotificationService notificationService;
	
	@Mock
	private UserDetailsService userDetailsService;
	
	@Test
	public void testEmailNotifications() throws IOException {
		
		FilterNotification filter = new FilterNotification();
		filter.setUser("theuser");
		List<ProductCatalog> products = new ArrayList<>();
		byte[] excelContent = "This is the excel file".getBytes();
		
		List<? extends GrantedAuthority> authList = Collections.singletonList(new SimpleGrantedAuthority("admin"));
		UserDetails details = new XmiUser("theuser","pass","test@test.com","1234567890", authList);
		
		EasyMock.expect(notificationService.getNotifications(EasyMock.eq(filter))).andReturn(products);
		
		EasyMock.expect(excelService.convertToExcel(EasyMock.eq(products))).andReturn(excelContent);
		
		EasyMock.expect(userDetailsService.loadUserByUsername(EasyMock.eq("theuser"))).andReturn(details);
		
		Capture<String> capturedFileName = Capture.newInstance();
		EasyMock.expect(blobDao.uploadBlob(EasyMock.capture(capturedFileName), EasyMock.eq(excelContent), EasyMock.eq("application/vnd.ms-excel"))).andReturn("newFile.xlsx");
		
		iftttDao.tiggerEvent(EasyMock.eq("xmi_product_list"), EasyMock.eq("test@test.com"), EasyMock.anyString(), EasyMock.eq("newFile.xlsx"));	
		
		replayAll();
		
		fixture.emailNotifications(filter);
		
		verifyAll();
		
		assertTrue("Filename should match", capturedFileName.getValue().startsWith("Product-list_"));
		assertTrue("Filename should match", capturedFileName.getValue().endsWith(".xlsx"));
		
	}
	
}
