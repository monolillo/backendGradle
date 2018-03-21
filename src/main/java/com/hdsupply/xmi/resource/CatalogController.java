package com.hdsupply.xmi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Product;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.ProductService;


@RestController
@CrossOrigin(origins = "*")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/site/{siteId}/product")
	public List<ProductCatalog> getActiveCatalog(@PathVariable("siteId") Integer siteId) {
		
		return catalogService.getActiveCatalog(siteId);
	}
	
	@RequestMapping(value="/site/{siteId}/product/{productId}")
	public ProductCatalog getProductById(@PathVariable("siteId") Integer siteId, @PathVariable("productId") Integer productId) {
		
		return productService.getProductById(siteId,productId);
		
	}
}
