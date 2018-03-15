package com.hdsupply.xmi.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hdsupply.xmi.config.DatasourceConfig;
import com.hdsupply.xmi.config.PropertiesConfig;

/**
 * Base class to set some things up for DB tests.
 * 
 * @author Julian F. Nunez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource({"classpath:testDatasource.properties"})
@ContextConfiguration(classes = {DatasourceConfig.class, PropertiesConfig.class})
public abstract class DaoDbTestBase {

}
