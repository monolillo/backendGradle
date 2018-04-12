package com.hdsupply.xmi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.Site;


@Repository
public class SiteDaoImpl implements SiteDao{

	@Autowired
	private DataSource dataSource;
	
	@Value("${siteDao.getSiteByUserSql}")
	private String getSiteByUserSql;
	
	@Override
	public List<Site> getSiteByUser(String user) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(getSiteByUserSql, new Object[] { user }, new BeanPropertyRowMapper<Site>(Site.class));
	}
	

}
