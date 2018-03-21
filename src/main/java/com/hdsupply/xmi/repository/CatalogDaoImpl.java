package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.Product;


@Repository
public class CatalogDaoImpl implements CatalogDao{

	@Autowired
	private DataSource dataSource;
	
	@Value("${catalogDao.getActiveCatalogSql}")
	private String getActiveCatalogSql;
	
	@Value("${catalogDao.getCatalogSql}")
	private String getCatalogSql;
	
	@Override
	public List<Catalog> getActiveCatalog(Integer siteId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getActiveCatalogSql, new Object[] { siteId }, new BeanPropertyRowMapper<Catalog>(Catalog.class));
	}

	@Override
	public Catalog getCatalog(Integer siteId, Integer productId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getCatalogSql, new Object[] { siteId, productId }, new BeanPropertyRowMapper<Catalog>(Catalog.class));
	}
	
}
