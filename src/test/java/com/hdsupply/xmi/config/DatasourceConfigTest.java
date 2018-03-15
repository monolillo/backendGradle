package com.hdsupply.xmi.config;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DatasourceConfig.class, PropertiesConfig.class})
@TestPropertySource({"classpath:testDatasource.properties"})
public class DatasourceConfigTest {
	
	@Autowired
	private DataSource datasource;

	@Test
	public void testPropertySourcesPlaceholderConfigurer() {
		
		assertNotNull(datasource);
	}

}
