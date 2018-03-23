package com.hdsupply.xmi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.ProductService;

public class CatalogController extends BaseRestController {

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/site/{siteId}/product")
	@PreAuthorize("hasAuthority('READ_SITE_CATALOG')")
	public List<ProductCatalog> getActiveCatalog(@PathVariable("siteId") Integer siteId) {
		
		return catalogService.getActiveCatalog(siteId);
	}
	
	@RequestMapping(value="/site/{siteId}/product/{productId}")
	@PreAuthorize("hasAuthority('READ_SITE_CATALOG')")
	public ProductCatalog getProductById(@PathVariable("siteId") Integer siteId, @PathVariable("productId") Integer productId) {
		
		return productService.getProductById(siteId,productId);
		
	}
}
