package com.hdsupply.xmi.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.hdsupply.xmi.domain.Site;

@ContextConfiguration(classes=SiteDaoImplTest.class)
@Configuration
public class SiteDaoImplTest extends DaoDbTestBase{
	
	@Autowired
	private SiteDao fixture;	
	
	@Test
	public void testGetSiteByUser() {
		
		List<Site> site = fixture.getSiteByUser("admin");
		
		assertEquals((Integer) 2, site.get(0).getId());
		assertEquals("Courtyard by Marriott Atlanta Cumberland/Galleria", site.get(0).getName());
		assertEquals((Integer) 2, site.get(0).getCompanyId());
		
		
	}

	@Bean
	public SiteDao fixture() {
		return new SiteDaoImpl();
	}
}
