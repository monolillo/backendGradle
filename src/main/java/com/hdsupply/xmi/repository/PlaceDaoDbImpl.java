package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Place;

/**
 * Generic DAO that demonstrates the use of JdbcTemplate
 *
 * @author Julian F. Nunez <vc.julian.nune@lowes.com>
 * @created Apr 17, 2015
 */
@Repository
public class PlaceDaoDbImpl implements PlaceDao {

	@Autowired
	private DataSource dataSource;
	
	@Value("${placeDao.getActivePlacesSql}")
	private String getActivePlacesSql;
	
	/* (non-Javadoc)
	 * @see com.neoris.rest.repository.PlaceDao#getActivePlaces()
	 */

	@Override
	public List<Place> getActivePlaces() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Place> result = 
				jdbcTemplate.query(getActivePlacesSql, new BeanPropertyRowMapper<Place>(Place.class));
		
		return result;
	}

}
