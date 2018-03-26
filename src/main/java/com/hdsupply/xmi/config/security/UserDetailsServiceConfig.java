package com.hdsupply.xmi.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class UserDetailsServiceConfig {
	
    @Autowired
    private DataSource datasource;
    
    @Bean
    public UserDetailsService jdbcDaoImpl() {
    	
    	JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
    	jdbcDaoImpl.setEnableGroups(true);
    	jdbcDaoImpl.setEnableAuthorities(false);
    	jdbcDaoImpl.setDataSource(datasource);
    	
    	return jdbcDaoImpl;
    }

}
