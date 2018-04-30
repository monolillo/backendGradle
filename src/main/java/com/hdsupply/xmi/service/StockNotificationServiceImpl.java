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

	private static final String EMAIL_PERMISSION = "STOCK_PUSH_ALERTS";
	
	@Override
	public void doNotification(String user, Integer shopId, Integer productId) throws IOException {
	
		Site site = siteService.getSiteByIdShop(shopId);
		
		ProductCatalog productCatalog = productService.getProductById(site.getId(), productId);
		
		if(!productCatalog.getCritical()) {
			return;
		}
		
		if (productCatalog.getQuantity() < productCatalog.getMin() || productCatalog.getQuantity() == 0) {
			notifyProduct(productCatalog, site.getId());
			
		}
	
	}

	private void notifyProduct(ProductCatalog productCatalog, Integer siteId) throws IOException {
		
		List<XmiUser> listXmiUsers = xmiUserService.loadUsersEmailBySiteId(siteId, EMAIL_PERMISSION);
		
		String emails = listEmails(listXmiUsers);
		
		String emailBody = populateTemplate(productCatalog);
		
		String  emailSubject = generateSubject(productCatalog);
		
		iftttDao.tiggerEvent("xmi_critical_low", emails, emailSubject, emailBody);
		
	}
	
	private String listEmails(List<XmiUser> listXmiUsers) {
		
		StringBuilder emails = new StringBuilder();
		
		for (XmiUser xmiUser : listXmiUsers) {
			emails.append(xmiUser.getEmail()).append(",");
		}
		
		return emails.toString();
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
		
		String oooSubject = "Product #{0} is Out of Stock.";
		String lowSubject = "Product #{0} is Running low.";
		
		String template = null;
		if (productCatalog.getQuantity() == 0) {
			template = oooSubject;
		} else {
			template = lowSubject;
		}
		
		String emailSubject = MessageFormat.format(template, 
				Long.toString(productCatalog.getItemNumber()));
		
		return emailSubject;
	}

	public void setEmailTemplateLocation(String emailTemplateLocation) {
		this.emailTemplateLocation = emailTemplateLocation;
	}
}
