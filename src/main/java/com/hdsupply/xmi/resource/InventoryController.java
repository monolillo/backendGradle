package com.hdsupply.xmi.resource;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hdsupply.xmi.domain.Inventory;
import com.hdsupply.xmi.service.InventoryService;

@RestController
@CrossOrigin(origins = "*")
public class InventoryController extends BaseRestController{
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value="/shop/{shopId}/product/{productId}/checkin", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CHECK_IN_PRODUCT')")
	public Integer checkInProduct(@RequestBody Inventory inventory, @PathVariable("shopId") Integer shopId, @PathVariable("productId") Integer productId, Principal userDetail) {
		
		inventory.setProductId(productId);
		inventory.setShopId(shopId);
		
		return inventoryService.checkInProduct(inventory, userDetail.getName());
		
	}

}