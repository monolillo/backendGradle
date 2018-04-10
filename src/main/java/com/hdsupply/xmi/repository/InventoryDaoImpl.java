package com.hdsupply.xmi.repository;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hdsupply.xmi.domain.CheckIn;
import com.hdsupply.xmi.domain.CheckOut;
import com.hdsupply.xmi.domain.Inventory;

@Repository
public class InventoryDaoImpl implements InventoryDao{
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${inventoryDao.getInventoryByIdSql}")
	private String getInventoryByIdSql;
	
	@Value("${inventoryDao.existProductInInventorySql}")
	private String existProductInInventorySql;
	
	@Value("${inventoryDao.newProductSql}")
	private String newProductSql;

	@Value("${inventoryDao.updateProductSql}")
	private String updateProductSql;
	
	@Value("${inventoryDao.getQuantitySql}")
	private String getQuantitySql;
	
	@Value("${inventoryDao.newCheckInSql}")
	private String newCheckInSql;
	
	@Value("${inventoryDao.getNextCheckinIdSql}")
	private String getNextCheckinIdSql;
	
	@Value("${inventoryDao.getCheckInByIdSql}")
	private String getCheckInByIdSql;
	
	@Value("${inventoryDao.updateCheckOutProductSql}")
	private String updateCheckOutProductSql;
	
	@Value("${inventoryDao.newCheckOutSql}")
	private String newCheckOutSql;
	
	@Value("${inventoryDao.getNextCheckoutIdSql}")
	private String getNextCheckoutIdSql;
	
	@Value("${inventoryDao.getCheckOutByIdSql}")
	private String getCheckOutByIdSql;
	
	@Override
	public Inventory getInventoryById(Integer productId, Integer shopId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			return jdbcTemplate.queryForObject(getInventoryByIdSql, new Object[] { productId, shopId }, new BeanPropertyRowMapper<Inventory>(Inventory.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	} 
	
	@Override
	public void newInventoryProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(newProductSql, inventory.getQuantity(), inventory.getShopId(),
				inventory.getProductId(), inventory.getLocationId());
		
	}

	@Override
	public void updateInventoryProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//Verify if shopId is modified
		jdbcTemplate.update(updateProductSql, inventory.getQuantity(), inventory.getLocationId(), 
				inventory.getShopId(), inventory.getProductId());	
	}
	
	@Override
	public Boolean existProductInInventory(Integer shopId, Integer productId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(existProductInInventorySql, new Object[] { shopId, productId }, Boolean.class);
	}

	@Override
	public Integer getQuantity(Integer productId, Integer siteId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getQuantitySql, new Object[] { productId, siteId }, Integer.class);
	}

	@Override
	public void newCheckIn(Inventory inventory, String user, Integer checkInId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(newCheckInSql, checkInId, inventory.getQuantity(), user, new Date(), inventory.getShopId(),
				inventory.getLocationId(), inventory.getProductId());
		
	}
	
	@Override
	public Integer getNextCheckinId() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getNextCheckinIdSql, Integer.class);
		
	}

	@Override
	public CheckIn getCheckInById(Integer checkInId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			return jdbcTemplate.queryForObject(getCheckInByIdSql, new Object[] { checkInId }, new BeanPropertyRowMapper<CheckIn>(CheckIn.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	

	@Override
	public void updateCheckOutInventoryProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(updateCheckOutProductSql, inventory.getQuantity(), inventory.getShopId(), inventory.getProductId());
	}
	
	@Override
	public void newCheckOut(Inventory inventory, String user, Integer checkOutId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(newCheckOutSql, checkOutId, inventory.getQuantity(), user, new Date(), inventory.getShopId(),
				inventory.getLocationId(), inventory.getProductId());
		
	}
	
	@Override
	public Integer getNextCheckOutId() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getNextCheckoutIdSql, Integer.class);
		
	}
	
	@Override
	public CheckOut getCheckOutById(Integer checkOutId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			return jdbcTemplate.queryForObject(getCheckOutByIdSql, new Object[] { checkOutId }, new BeanPropertyRowMapper<CheckOut>(CheckOut.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

}
