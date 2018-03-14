package com.hdsupply.xmi.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes=PropertiesConfig.class)
@TestPropertySource({"classpath:testSource.properties"})
public class PropertiesConfigTest {
	
	@Value("${test.value}")
	private String testValue;

	@Test
	public void testPropertySourcesPlaceholderConfigurer() {
		
		assertEquals("Value should be wired", "A Value", testValue);
	}

}
