package com.hdsupply.xmi.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	
	@Override
	public Inventory getInventoryById(Integer productId, Integer shopId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getInventoryByIdSql, new Object[] { productId, shopId }, new BeanPropertyRowMapper<Inventory>(Inventory.class));

	} 
	
	@Override
	public void newInventoryProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(newProductSql, inventory.getQuantity(), inventory.getCheckedOutQuantity(), inventory.getShopId(),
				inventory.getProductId(), inventory.getLocationId());
		
	}

	@Override
	public void updateInventoryProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//Verify if shopId is modified
		jdbcTemplate.update(updateProductSql, inventory.getQuantity(), inventory.getCheckedOutQuantity(), inventory.getShopId(),
				inventory.getLocationId(), inventory.getShopId(), inventory.getProductId());	
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

}
