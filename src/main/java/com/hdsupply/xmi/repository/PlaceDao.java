package com.hdsupply.xmi.repository;

import java.util.List;

import com.hdsupply.xmi.domain.Place;

public interface PlaceDao {

	/**
	 * Gets a String from the DB using JdbcTemplate
	 * 
	 * @see com.neoris.symphony.repository.ExampleDao#getExample()
	 */

	List<Place> getActivePlaces();

}