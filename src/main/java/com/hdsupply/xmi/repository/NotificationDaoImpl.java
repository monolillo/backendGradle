package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;

@Repository
public class NotificationDaoImpl implements NotificationDao{

	@Autowired
	private DataSource dataSource;
	
	@Value("${notificationDao.getListOutOfStockSql}")
	private String getListOutOfStockSql;
	
	@Value("${notificationDao.getListLessThanMinSql}")
	private String getListLessThanMinSql;
	
	@Value("${notificationDao.getListMinThresholdSql")
	private String getListMinThresholdSql;
	
	@Override
	public List<ProductCatalog> getListOutOfStock(FilterNotification filter) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getListOutOfStockSql, new Object[] { filter.getSiteId(), filter.getSiteId() }, new BeanPropertyRowMapper<ProductCatalog>(ProductCatalog.class));
	}

	@Override
	public List<ProductCatalog> getListLessThanMin(FilterNotification filter) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getListLessThanMinSql, new Object[] { filter.getSiteId(), filter.getSiteId() }, new BeanPropertyRowMapper<ProductCatalog>(ProductCatalog.class));
	}

	@Override
	public List<ProductCatalog> getListMinThreshold(FilterNotification filter) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getListMinThresholdSql, new Object[] { filter.getSiteId(), filter.getSiteId() }, new BeanPropertyRowMapper<ProductCatalog>(ProductCatalog.class));
	}
	
	

}
