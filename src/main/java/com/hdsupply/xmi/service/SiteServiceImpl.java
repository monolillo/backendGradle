package com.hdsupply.xmi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.Shop;
import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.repository.ShopDao;
import com.hdsupply.xmi.repository.SiteDao;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteDao siteDao;
	
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public List<Site> getSiteByUser(String user) {

		List<Site> listSite = siteDao.getSiteByUser(user);
		
		for (Site site : listSite) {
			
			List<Shop> listShop = shopDao.getShopBySiteId(site.getId());
			
			site.setShops(listShop);
			
		}
		
		return listSite;
	}

}
