package com.hdsupply.xmi.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.NotificationService;

@RestController
public class NotificationController extends BaseRestController {
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value="/site/{siteId}/product/notification", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('NOTIFICATION')")
	public List<ProductCatalog> getNotification(@PathParam("critical") Boolean critical, @PathVariable("siteId") Integer siteId) {
		
		FilterNotification filter = new FilterNotification();
		filter.setSiteId(siteId);
		filter.setCritical(critical);
		
		List<ProductCatalog> productCatalogList = notificationService.getNotification(filter);
		
		return productCatalogList;
	}

}
