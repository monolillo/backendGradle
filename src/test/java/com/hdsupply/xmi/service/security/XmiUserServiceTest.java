package com.hdsupply.xmi.service.security;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.config.security.UserDetailsServiceConfig;
import com.hdsupply.xmi.domain.XmiUser;
import com.hdsupply.xmi.repository.DaoDbTestBase;

@ContextConfiguration(classes = {XmiUserServiceTest.class, UserDetailsServiceConfig.class })
@Configuration
public class XmiUserServiceTest extends DaoDbTestBase {
	
	@Autowired
	private UserDetailsService fixture;

	@Test
	public void testLoadUsersEmailBySiteId() {
		
		List<XmiUser> users = ((XmiUserService)fixture).loadUsersEmailBySiteId(2, "STOCK_PUSH_ALERTS");
		
		assertEquals("Only one user should be returned", 1, users.size());
		assertEquals("User fields should be filled.", "superv_usr", users.get(0).getUsername());
		assertEquals("User fields should be filled.", true, users.get(0).isEnabled());
		assertEquals("User fields should be filled.", "superv_usr@test.com", users.get(0).getEmail());
		assertEquals("User fields should be filled.", "4444444444", users.get(0).getPhone());
	}
	
	@Test
	public void testLoadUsersEmailBySiteIdNotFound() {
		
		List<XmiUser> users = ((XmiUserService)fixture).loadUsersEmailBySiteId(3, "STOCK_PUSH_ALERTS");
		
		assertEquals("No users should be returned", 0, users.size());
	}
	

}
