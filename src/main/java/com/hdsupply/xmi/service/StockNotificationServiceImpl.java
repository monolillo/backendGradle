package com.hdsupply.xmi.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.domain.XmiUser;
import com.hdsupply.xmi.repository.IftttDao;

@Service
public class StockNotificationServiceImpl implements StockNotificationService {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IftttDao iftttDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SiteService siteService; 
	
	@Override
	public void doNotification(String user, Integer shopId, Integer productId) throws IOException {
	
		Site site = siteService.getSiteByIdShop(shopId);   
		
		ProductCatalog productCatalog = productService.getProductById(site.getId(), productId);
		
		if (productCatalog.getQuantity() < productCatalog.getMin() || productCatalog.getQuantity() == 0) {
			notifyProduct(user, productCatalog);
			
		}
	
	}

	private void notifyProduct(String username, ProductCatalog productCatalog) throws IOException {
		
		XmiUser user = (XmiUser) userDetailsService.loadUserByUsername(username);
		
		String emailBody = populateTemplate(productCatalog);
		
		String  emailSubject = generateSubject(productCatalog);
		
		iftttDao.tiggerEvent("xmi_critical_low", user.getEmail(), emailSubject, emailBody);
		
	}
	
	private String populateTemplate(ProductCatalog productCatalog) throws IOException {
		
		File file = ResourceUtils.getFile("classpath:templatesTest/stockTest.html");
		
		String emailTemplate = new String(Files.readAllBytes(file.toPath()));
		
		String msg = emailTemplate.replaceAll("AAAitemNumberAAA", String.valueOf(productCatalog.getItemNumber()));
		
		String msg1 = msg.replaceAll("AAAquantityAAA", String.valueOf(productCatalog.getQuantity()));
		
		String msg2 = msg1.replaceAll("AAAnameAAA", String.valueOf(productCatalog.getName()));
		
		String msg3 = msg2.replaceAll("AAAminAAA", String.valueOf(productCatalog.getMin()));
		
		String msg4;
		
		if(0 == productCatalog.getQuantity()) {
			msg4 = msg3.replaceAll("AAAstateAAA", "Out of Stock");
		} else {
			msg4 = msg3.replaceAll("AAAstateAAA", "Running low");
		}
		
		return msg4;
	}
	
	private String generateSubject(ProductCatalog productCatalog) {
		
		String oooSubject = "Product {0} is Out of Stock";
		
		String lowSubject = "Product {0} is Running low";
		
		String emailSubject = null;
		
		if (productCatalog.getQuantity() == 0) {
			
			emailSubject = MessageFormat.format(oooSubject, productCatalog.getItemNumber());
	
		}else {
			
			emailSubject = MessageFormat.format(lowSubject, productCatalog.getItemNumber());
			
		}
		
		return emailSubject;
	}
}
