package com.hdsupply.xmi.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hdsupply.xmi.config.security.AuthorizationServerConfig;
import com.hdsupply.xmi.config.security.ResourceServerConfig;
import com.hdsupply.xmi.config.security.SecurityConfig;
import com.hdsupply.xmi.config.security.SecurityWebApplicationInitializer;
import com.hdsupply.xmi.config.security.SimpleCORSFilter;
import com.hdsupply.xmi.config.security.UserDetailsServiceConfig;

/**
 * Base test class for controllers.
 * Wires up a spring MVC context with security ready to go.
 * 
 * @author Julian F. Nunez <julian.nunez@neoris.com>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatasourceConfig.class, PropertiesConfig.class, UserDetailsServiceConfig.class, SecurityConfig.class, SecurityWebApplicationInitializer.class, AuthorizationServerConfig.class, ResourceServerConfig.class, SecurityTest.class, SimpleCORSFilter.class})
@WebAppConfiguration
@Configuration
@EnableWebMvc
@TestPropertySource({"classpath:testDatasource.properties"})
public class SecurityTest {
	
    @Autowired
    protected WebApplicationContext ctx;
    
    @Autowired
    private SimpleCORSFilter filter;
	
	protected MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		
		// Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders
        		.webAppContextSetup(ctx)
        		.apply(springSecurity())
        		.addFilter(filter, "/*")
        		.build();		
        
	}		
	
	
	@Test
	public void testOauthLoginSuccess() throws Exception {
		
		mockMvc.perform(post("/oauth/token")
				.with(httpBasic("testjwtclientid","XY7kmzoNzl100"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("grant_type", "password")
				.param("username", "admin")
				.param("password", "admin_pwd")
				.header("Accept", "application/json"))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void testOauthLoginInvalidClientAuth() throws Exception {
		
		mockMvc.perform(post("/oauth/token")
				.with(httpBasic("testjwtclientid","WRONG_PWD"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("grant_type", "password")
				.param("username", "admin")
				.param("password", "admin_pwd")
				.header("Accept", "application/json"))
				.andExpect(status().isUnauthorized());
		
	}
	
	@Test
	public void testOauthLoginInvalidUserPassword() throws Exception {
		
		File file = ResourceUtils.getFile("classpath:json/security/invalidCredentials.json");
		String expectedJson = new String(Files.readAllBytes(file.toPath()));	
		
		mockMvc.perform(post("/oauth/token")
				.with(httpBasic("testjwtclientid","XY7kmzoNzl100"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("grant_type", "password")
				.param("username", "admin")
				.param("password", "INVALID_PASS")
				.header("Accept", "application/json"))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expectedJson));
		
	}	

}