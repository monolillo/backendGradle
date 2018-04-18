package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.Location;

public interface LocationDao {

	/**
	 * Gets a String from the DB using JdbcTemplate
	 * 
	 * @see com.neoris.symphony.repository.ExampleDao#getExample()
	 */

	List<Location> getLocationsByShop(int shopId);

}