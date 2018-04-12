package com.hdsupply.xmi.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hdsupply.xmi.domain.Shop;
import com.hdsupply.xmi.domain.Site;
import com.hdsupply.xmi.repository.ShopDao;
import com.hdsupply.xmi.repository.SiteDao;

@RunWith(EasyMockRunner.class)
public class SiteServiceImplTest extends EasyMockSupport{
	
	@TestSubject
	private SiteServiceImpl fixture = new SiteServiceImpl();
	
	@Mock
	private SiteDao siteDao;
	
	@Mock
	private ShopDao shopDao;
	
	@Test
	public void testGetSiteByUser() {
		
		Site site = new Site();
		site.setId(2);
		site.setName("Courtyard by Marriott Atlanta Cumberland/Galleria");
		site.setCompanyId(2);
		
		List<Site> listSite = Arrays.asList(new Site[] {site});
		
		Shop shop1 = new Shop();
		shop1.setId(2);
		shop1.setName("Maintenance");
		shop1.setSiteId(2);

		Shop shop2 = new Shop();
		shop2.setId(3);
		shop2.setName("Storage");
		shop2.setSiteId(2);
				
		List<Shop> listShop = Arrays.asList(new Shop[] {shop1, shop2});
		
		site.setShops(listShop);
		
		EasyMock.expect(siteDao.getSiteByUser("admin")).andReturn(listSite);
		EasyMock.expect(shopDao.getShopBySiteId(2)).andReturn(listShop);
		
		replayAll();
		
		fixture.getSiteByUser("admin");
		
		verifyAll();
		
		assertEquals((Integer) 2, listShop.get(0).getId());
		assertEquals("Maintenance", listShop.get(0).getName());
		assertEquals((Integer) 2, listShop.get(0).getSiteId());
		
		assertEquals((Integer) 3, listShop.get(1).getId());
		assertEquals("Storage", listShop.get(1).getName());
		assertEquals((Integer) 2, listShop.get(1).getSiteId());
		
		
		
	}

}
