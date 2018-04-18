package com.hdsupply.xmi.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hdsupply.xmi.service.security.XmiUserService;

@Configuration
public class UserDetailsServiceConfig {
	
    @Autowired
    private DataSource datasource;
    
    @Autowired 
    private AutowireCapableBeanFactory beanFactory;
    
    @Bean
    public UserDetailsService userDetailsService() {
    	
    	XmiUserService xmiUserService = new XmiUserService();
    	xmiUserService.setEnableGroups(true);
    	xmiUserService.setEnableAuthorities(false);
    	xmiUserService.setDataSource(datasource);
    	
    	beanFactory.autowireBean(xmiUserService);
    	
    	return xmiUserService;
    }

}
