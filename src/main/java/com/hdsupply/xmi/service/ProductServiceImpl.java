package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private InventoryDao inventoryDao;

	@Override
	public ProductCatalog getProductById(Integer siteId, Integer productId) {
		
		ProductCatalog productCatalog = productDao.getProductById(siteId, productId);
		
		if(null != productCatalog) {
			Integer qty = inventoryDao.getQuantity(productId, siteId);
		
			productCatalog.setQuantity(qty);
		}
		
		return productCatalog;
	}
	
	@Override
	public ProductCatalog getProductByItemNumber(Integer siteId, Integer itemNumber) {
		
		ProductCatalog productCatalog = productDao.getProductByItemNumber(siteId, itemNumber);
		
		if(null != productCatalog) {
			Integer qty = inventoryDao.getQuantity(productCatalog.getIdProduct(), siteId);
		
			productCatalog.setQuantity(qty);
		}
		
		return productCatalog;
	}	
	
}
