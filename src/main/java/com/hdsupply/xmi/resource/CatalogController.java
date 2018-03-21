package com.hdsupply.xmi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;


@RestController
@CrossOrigin(origins = "*")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;
	
	@RequestMapping(value="/site/{siteId}/product")
	public List<ProductCatalog> getActiveCatalog(@PathVariable("siteId") Integer siteId) {
		
		List<ProductCatalog> listProductCatalog = catalogService.getActiveCatalog(siteId);
		
		return listProductCatalog;
	}
	
}
