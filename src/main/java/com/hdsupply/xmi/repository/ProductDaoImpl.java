package com.hdsupply.xmi.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Product;
import com.hdsupply.xmi.domain.ProductCatalog;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${productDao.getProductByIdSql}")
	private String getProductByIdSql;
	
	@Override
	public ProductCatalog getProductById(Integer siteId, Integer productId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getProductByIdSql, new Object[] { siteId, productId }, new BeanPropertyRowMapper<ProductCatalog>(ProductCatalog.class));
	}

}
