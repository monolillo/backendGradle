package com.hdsupply.xmi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.CatalogDao;
import com.hdsupply.xmi.repository.InventoryDao;

@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogDao catalogDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	public List<ProductCatalog> getActiveCatalog(Integer siteId){
		
		List<ProductCatalog> listProductCatalog = catalogDao.getActiveCatalog(siteId);
		
		for (ProductCatalog productCatalog : listProductCatalog) {
			
			Integer qty = inventoryDao.getQuantity(productCatalog.getIdProduct(), siteId);
			
			productCatalog.setQuantity(qty);
			
		}
		
		listProductCatalog.sort((p1, p2) -> p1.getQuantity().compareTo(p2.getQuantity()));
		
		return listProductCatalog;
	}
	
	public void updateActiveCatalog (Catalog catalog){
		
		 catalogDao.updateActiveCatalog(catalog);
	
	}

}

