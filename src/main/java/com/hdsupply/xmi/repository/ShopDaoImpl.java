package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Shop;

@Repository
public class ShopDaoImpl implements ShopDao {

	@Autowired
	private DataSource dataSource;
	
	@Value("${shopDao.getShopByUserSql}")
	private String getShopByUserSql;
	

	@Override
	public List<Shop> getShopBySiteId(Integer siteId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getShopByUserSql, new Object[] { siteId }, new BeanPropertyRowMapper<Shop>(Shop.class));
		
	}

}
