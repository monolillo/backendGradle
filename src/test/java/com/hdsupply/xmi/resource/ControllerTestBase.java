package com.hdsupply.xmi.resource;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hdsupply.xmi.config.PropertiesConfig;
import com.hdsupply.xmi.config.security.SecurityConfig;

/**
 * Base test class for controllers.
 * Wires up a spring MVC context with security ready to go.
 * 
 * @author Julian F. Nunez <julian.nunez@neoris.com>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, SecurityConfig.class})
@WebAppConfiguration
@Configuration
@EnableWebMvc
public abstract class ControllerTestBase {
	
    @Autowired
    protected WebApplicationContext ctx;
	
	protected MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		
		// Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders
        		.webAppContextSetup(ctx)
        		.apply(springSecurity())
        		.build();		
        
	}		

}