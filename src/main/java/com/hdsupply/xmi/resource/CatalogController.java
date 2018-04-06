package com.hdsupply.xmi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.service.CatalogService;
import com.hdsupply.xmi.service.ProductService;

@RestController
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
	public ResponseEntity<ProductCatalog> getProductById(@PathVariable("siteId") Integer siteId, @PathVariable("productId") Integer productId) {
		
		ProductCatalog productCatalog = productService.getProductById(siteId,productId);
		
		if(null == productCatalog) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok().body(productCatalog);
		}
		
	}

	@RequestMapping(value="/site/{siteId}/product/itemNumber/{itemNumber}")
	@PreAuthorize("hasAuthority('READ_SITE_CATALOG')")
	public ResponseEntity<ProductCatalog> getProductByItemNumber(@PathVariable("siteId") Integer siteId, @PathVariable("itemNumber") Integer itemNumber) {
		
		ProductCatalog productCatalog = productService.getProductByItemNumber(siteId,itemNumber);
		
		if(null == productCatalog) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok().body(productCatalog);
		}
		
	}
	
	@RequestMapping(value="/site/{siteId}/product/{productId}", method=RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('READ_SITE_CATALOG')")
	public void updateActiveCatalog(@Valid @RequestBody Catalog catalog, @PathVariable("siteId") Integer siteId, @PathVariable("productId") Integer productId) {
		
		catalog.setSiteId(siteId);
		catalog.setProductId(productId);
		catalogService.updateActiveCatalog(catalog);
		
	}
}
