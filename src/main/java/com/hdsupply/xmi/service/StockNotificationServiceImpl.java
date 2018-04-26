package com.hdsupply.xmi.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.domain.XmiUser;
import com.hdsupply.xmi.repository.IftttDao;
import com.hdsupply.xmi.service.security.XmiUserService;

@Service
public class StockNotificationServiceImpl implements StockNotificationService {

	@Autowired
	private IftttDao iftttDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private XmiUserService xmiUserService;
	
	private String emailTemplateLocation = "classpath:templates/stockEmail.html";

	private final String emailPermission = "STOCK_PUSH_ALERTS";
	
	@Override
	public void doNotification(String user, Integer shopId, Integer productId) throws IOException {
	
		Site site = siteService.getSiteByIdShop(shopId);   
		
		ProductCatalog productCatalog = productService.getProductById(site.getId(), productId);
		
		if (productCatalog.getQuantity() < productCatalog.getMin() || productCatalog.getQuantity() == 0) {
			notifyProduct(user, productCatalog);
			
		}
	
	}

	private void notifyProduct(String username, ProductCatalog productCatalog) throws IOException {
		
		List<XmiUser> listXmiUsers = xmiUserService.loadUsersEmailBySiteId(4, emailPermission);
		
		String emails = listEmails(listXmiUsers);
		
		String emailBody = populateTemplate(productCatalog);
		
		String  emailSubject = generateSubject(productCatalog);
		
		iftttDao.tiggerEvent("xmi_critical_low", emails, emailSubject, emailBody);
		
	}
	
	private String listEmails(List<XmiUser> listXmiUsers) {
		
		String emails = "";
		
		for (XmiUser xmiUser : listXmiUsers) {
			emails = emails + xmiUser.getEmail() + ",";
		}
		
		return emails;
	}
	
	private String populateTemplate(ProductCatalog productCatalog) throws IOException {
		
		File file = ResourceUtils.getFile(emailTemplateLocation);
		
		String emailTemplate = new String(Files.readAllBytes(file.toPath()));
		
		String msg = emailTemplate.replaceAll("AAAitemNumberAAA", String.valueOf(productCatalog.getItemNumber()).trim());
		
		String msg1 = msg.replaceAll("AAAquantityAAA", String.valueOf(productCatalog.getQuantity()).trim());
		
		String msg2 = msg1.replaceAll("AAAnameAAA", String.valueOf(productCatalog.getName()).trim());
		
		String msg3 = msg2.replaceAll("AAAminAAA", String.valueOf(productCatalog.getMin()).trim());
		
		String msg4;
		
		if(0 == productCatalog.getQuantity()) {
			msg4 = msg3.replaceAll("AAAstateAAA", "Out of Stock").trim();
		} else {
			msg4 = msg3.replaceAll("AAAstateAAA", "Running low").trim();
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

	public void setEmailTemplateLocation(String emailTemplateLocation) {
		this.emailTemplateLocation = emailTemplateLocation;
	}
}
