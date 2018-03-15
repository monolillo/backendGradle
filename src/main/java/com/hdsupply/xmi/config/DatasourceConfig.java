package com.hdsupply.xmi.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Example configuration for a bean read from a JNDI lookup
 *
 * @author Julian F. Nunez <vc.julian.nune@lowes.com>
 * @created Apr 17, 2015
 */
@Configuration
@PropertySource({"classpath:datasource.properties"})
public class DatasourceConfig {
	
    @Autowired
    private Environment environment;
    
    @Bean
    public DataSource dataSource() throws NamingException {
    	
    	DataSource dataSource = DataSourceBuilder.create()
    			.url(environment.getProperty("JDBC_URL"))
    			.username(environment.getProperty("DB_USERNAME"))
    			.password(environment.getProperty("DB_PASSWORD"))    			
    			.build();
    	
        return dataSource;
    }

}
