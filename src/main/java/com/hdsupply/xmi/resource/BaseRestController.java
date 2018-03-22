package com.hdsupply.xmi.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base REST controller that controls CORS access 
 * and maps REST controllers to /rest base URI.
 * 
 * @author julian.nunez
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest")
public class BaseRestController {

}