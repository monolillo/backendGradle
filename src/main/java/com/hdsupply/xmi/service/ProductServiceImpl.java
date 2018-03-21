package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.Product;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.CatalogDao;
import com.hdsupply.xmi.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CatalogDao catalogDao;

	@Override
	public ProductCatalog getProductById(Integer siteId, Integer pruductId) {
		
		ProductCatalog productCatalog = new ProductCatalog();
		
		Product product = productDao.getProductById(pruductId);
		
		Catalog catalog = catalogDao.getCatalog(siteId, pruductId);
		
		productCatalog.setIdProduct(product.getId());
		productCatalog.setName(product.getName());
		productCatalog.setItemNumber(product.getItemNumber());
		productCatalog.setMax(catalog.getMax());
		productCatalog.setMin(catalog.getMin());
		
		return productCatalog;
	}
	
}
