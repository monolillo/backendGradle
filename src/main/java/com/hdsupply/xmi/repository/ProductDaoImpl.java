package com.hdsupply.xmi.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${productDao.getProductByIdSql}")
	private String getProductByIdSql;
	
	@Override
	public Product getProductById(Integer idProduct) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Product product = jdbcTemplate.queryForObject(getProductByIdSql, new Object[] { idProduct }, new BeanPropertyRowMapper<Product>(Product.class));
		
		return product;
	}

}
