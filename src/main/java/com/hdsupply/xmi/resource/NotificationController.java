package com.hdsupply.xmi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.NotificationService;

public class NotificationController extends BaseRestController {
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value="/site/{siteId}/product/notification", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('NOTIFICATION')")
	public List<ProductCatalog> getNotification(@RequestBody FilterNotification filter, @PathVariable("siteId") Integer siteId) {
		
		filter.setSiteId(siteId);
		
		List<ProductCatalog> productCatalogList = notificationService.getNotification(filter);
		
		return productCatalogList;
	}

}
