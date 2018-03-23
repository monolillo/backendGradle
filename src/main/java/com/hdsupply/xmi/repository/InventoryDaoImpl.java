package com.hdsupply.xmi.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
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
	
	@Override
	public Inventory getInventoryById(Integer productId) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(getInventoryByIdSql, new Object[] { productId }, new BeanPropertyRowMapper<Inventory>(Inventory.class));

	} 
	
	@Override
	public void newProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(newProductSql, inventory.getQuantity(), inventory.getCheckedOutQuantity(), inventory.getShopId(),
				inventory.getProductId(), inventory.getLocationId());
		
	}

	@Override
	public void updateProduct(Inventory inventory) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//Verify if shopId is modified
		jdbcTemplate.update(updateProductSql, inventory.getQuantity(), inventory.getCheckedOutQuantity(), inventory.getShopId(),
				inventory.getLocationId(), inventory.getShopId(), inventory.getProductId());	
	}
	
	@Override
	public Integer existProductInInventory(Integer shopId, Integer productId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.queryForObject(existProductInInventorySql, new Object[] { shopId, productId }, Integer.class);
	}

}
