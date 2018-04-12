package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.Shop;

public interface ShopDao {

	List<Shop> getShopBySiteId(Integer siteId);
	
}
