package com.hdsupply.xmi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.CatalogDao;

@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogDao catalogDao;
	
	public List<ProductCatalog> getActiveCatalog(Integer siteId){
		
		List<ProductCatalog> listProductCatalog = catalogDao.getActiveCatalog(siteId);
		
		return listProductCatalog;
	}
	
	public void updateActiveCatalog (Catalog catalog){
		
		 catalogDao.updateActiveCatalog(catalog);
	
	}

}

