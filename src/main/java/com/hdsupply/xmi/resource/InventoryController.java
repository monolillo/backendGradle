package com.hdsupply.xmi.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.service.InventoryService;


@RestController
@CrossOrigin(origins = "*")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value="/shop/product/checkin", method=RequestMethod.POST)
	public void checkInProduct(@Valid @RequestBody Inventory inventory) {
		
		inventoryService.checkInProduct(inventory);
		
	}

}