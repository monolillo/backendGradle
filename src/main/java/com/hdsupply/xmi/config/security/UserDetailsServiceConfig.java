package com.hdsupply.xmi.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hdsupply.xmi.service.security.XmiUserService;

@Configuration
public class UserDetailsServiceConfig {
	
    @Autowired
    private DataSource datasource;
    
    @Bean
    public UserDetailsService jdbcDaoImpl() {
    	
    	XmiUserService jdbcDaoImpl = new XmiUserService();
    	jdbcDaoImpl.setEnableGroups(true);
    	jdbcDaoImpl.setEnableAuthorities(false);
    	jdbcDaoImpl.setDataSource(datasource);
    	
    	return jdbcDaoImpl;
    }

}
