package com.hdsupply.xmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.InventoryDao;
import com.hdsupply.xmi.repository.ProductDao;
import com.hdsupply.xmi.repository.ShopDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private ShopDao shopDao;

	@Override
	public ProductCatalog getProductById(Integer siteId, Integer productId) {
		
		ProductCatalog productCatalog = productDao.getProductById(siteId, productId);
		
		if(null != productCatalog) {
			Inventory inventory = inventoryDao.getInventoryById(productId, shopDao.getShopBySiteId(siteId).get(0).getId());
		
			productCatalog.setQuantity(inventory.getQuantity());
			productCatalog.setLocationId(inventory.getLocationId());
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
