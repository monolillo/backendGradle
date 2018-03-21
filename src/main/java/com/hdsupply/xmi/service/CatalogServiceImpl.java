package com.hdsupply.xmi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.Product;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.CatalogDao;
import com.hdsupply.xmi.repository.ProductDao;

@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogDao catalogDao;
	
	@Autowired
	private ProductDao productDao;
	
	public List<ProductCatalog> getActiveCatalog(Integer siteId){
		
		List<ProductCatalog> listProductCatalog = new ArrayList<>();
		ProductCatalog productCatalog = null;
		
		List<Catalog> listCatalog = catalogDao.getActiveCatalog(siteId);
		
		for (Catalog catalog : listCatalog) {
			
			productCatalog = new ProductCatalog();
			
			productCatalog.setMax(catalog.getMax());
			productCatalog.setMin(catalog.getMin());
			
			Product product = productDao.getProductById(catalog.getProductId());
			
			productCatalog.setItemNumber(product.getItemNumber());
			productCatalog.setName(product.getName());
			productCatalog.setIdProduct(product.getId());
			
			listProductCatalog.add(productCatalog);
			
		}
		
		return listProductCatalog;
	}

}

