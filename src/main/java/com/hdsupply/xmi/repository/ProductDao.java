package com.hdsupply.xmi.repository;

import com.hdsupply.xmi.domain.Product;

public interface ProductDao {
	
	Product getProductById(Integer idProduct);

}
