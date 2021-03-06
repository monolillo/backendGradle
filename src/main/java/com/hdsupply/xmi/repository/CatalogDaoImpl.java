package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Catalog;
import com.hdsupply.xmi.domain.ProductCatalog;

@Repository
public class CatalogDaoImpl implements CatalogDao{

	@Autowired
	private DataSource dataSource;
	
	@Value("${catalogDao.getActiveCatalogSql}")
	private String getActiveCatalogSql;
	
	@Value("${catalogDao.updateActiveCatalogSql}")
	private String updateActiveCatalogSql;
	
	@Value("${catalogDao.getCatalogByIdSql}")
	private String getCatalogByIdSql;
	
	@Override
	public List<ProductCatalog> getActiveCatalog(Integer siteId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getActiveCatalogSql, new Object[] { siteId }, new BeanPropertyRowMapper<ProductCatalog>(ProductCatalog.class));
	}

	public void updateActiveCatalog(Catalog catalog){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(updateActiveCatalogSql, catalog.getMin(), catalog.getMax(), catalog.getCritical(), catalog.getSiteId(), catalog.getProductId());
		
		
	}

	@Override
	public Catalog getCatalogById(Integer siteId, Integer productId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getCatalogByIdSql, new Object[] { siteId, productId }, new BeanPropertyRowMapper<Catalog>(Catalog.class));
	}
	
}
