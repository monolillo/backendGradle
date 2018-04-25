package com.hdsupply.xmi.resource;

import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.enums.StockNotificationEnum;
import com.hdsupply.xmi.service.NotificationEmailService;
import com.hdsupply.xmi.service.NotificationService;

@RestController
public class NotificationController extends BaseRestController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NotificationEmailService notificationEmailService;
	
	@RequestMapping(value="/site/{siteId}/product/notification", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('NOTIFICATION')")
	public List<ProductCatalog> getNotifications(@PathParam("critical") Boolean critical, 
			@PathVariable("siteId") Integer siteId, @PathParam("stockNotification") StockNotificationEnum stockNotification) {
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(siteId);
		filter.setCritical(critical);
		filter.setStockNotification(stockNotification);
		
		List<ProductCatalog> productCatalogList = notificationService.getNotifications(filter);
		
		return productCatalogList;
	}
	@RequestMapping(value="/site/{siteId}/product/notification/send", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('SEND')")
	public void sendNotification(@RequestBody FilterNotification filter, @PathVariable("siteId") Integer siteId, Principal userName) {
		
		filter.setSiteId(siteId);
		filter.setUser(userName.getName());
		
		notificationEmailService.emailNotifications(filter);
		
	}

}
