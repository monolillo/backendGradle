package com.hdsupply.xmi.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Example configuration for a bean read from a JNDI lookup
 *
 * @author Julian F. Nunez <vc.julian.nune@lowes.com>
 * @created Apr 17, 2015
 */
@Configuration
public class DatasourceConfig {
	
    @Autowired
    private Environment environment;
	
    @Bean
    public DataSource dataSource() throws NamingException {
    	
    	String conn = "jdbc:sqlserver://miidb.database.windows.net:1433;database=xmi;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    	
    	DataSource dataSource = DataSourceBuilder.create()
    			.url(conn)
    			.username("mii_admin")
    			.password("Neoris.16")
    			.build();
    	
        return dataSource;
    }

}
