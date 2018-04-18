package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Location;

/**
 * Generic DAO that demonstrates the use of JdbcTemplate
 *
 * @author Julian F. Nunez <vc.julian.nune@lowes.com>
 * @created Apr 17, 2015
 */
@Repository
public class LocationDaoDbImpl implements LocationDao {

	@Autowired
	private DataSource dataSource;
	
	@Value("${locationDao.getLocationsByShop}")
	private String getLocationsByShopSql;
	
	/* (non-Javadoc)
	 * @see com.neoris.rest.repository.PlaceDao#getActivePlaces()
	 */

	@Override
	public List<Location> getLocationsByShop(int shopId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Location> result = 
				jdbcTemplate.query(getLocationsByShopSql, new Object[] { shopId }, BeanPropertyRowMapper.newInstance(Location.class));
		
		return result;
	}

}
