package com.hdsupply.xmi.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Sets us an in-memory H2 DB to use in Junits for DAOs.
 * 
 * @author Julian F. Nunez
 *
 */
@Configuration
public class InMemoryDataConfig {
	
	@Bean
	public DataSource dataSource(){
			
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.
				              setType(EmbeddedDatabaseType.H2).
				              addScript("classpath:sql/schema.sql").
				              addScript("classpath:sql/data.sql").
				              build();
			
		//db.shutdown();
		return db;
	}		

}