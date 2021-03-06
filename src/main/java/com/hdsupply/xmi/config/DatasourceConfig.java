package com.hdsupply.xmi.config;



import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Database configuration using DataSourceBuilder
 *
 * @author Julian F. Nunez <julian.nunez@neoris.com>
 * @created Mar 15, 2018
 */
@Configuration
@PropertySource({"classpath:datasource.properties"})
public class DatasourceConfig {
	
    @Autowired
    private Environment environment;
    
    @Bean
    public DataSource dataSource() {
    	
    	DataSource dataSource = (DataSource)DataSourceBuilder.create()
    			.url(environment.getProperty("JDBC_URL"))
    			.username(environment.getProperty("DB_USERNAME"))
    			.password(environment.getProperty("DB_PASSWORD"))    			
    			.build();
    	
    	dataSource.setTestWhileIdle(true);
    	dataSource.setTimeBetweenEvictionRunsMillis(600000); //10 minutes
    	dataSource.setValidationQuery("SELECT 1");
    	
        return dataSource;
    }

}
