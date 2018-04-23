package com.hdsupply.xmi.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.repository.AzureBlobDao;
import com.hdsupply.xmi.repository.IftttDao;
import com.hdsupply.xmi.service.excel.ExcelService;

@Service
public class NotificationEmailServiceImpl implements NotificationEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(NotificationEmailServiceImpl.class);
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
	private static final String FILENAME_TEMPLATE = "Product-list_{0}.xlsx";
	
	private static final String IFTTT_EVENT = "xmi_product_list";
	private static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	@Autowired
	private AzureBlobDao blobDao;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private IftttDao iftttDao;
	
	@Override
	public void emailNotifications() {
		
		
        ProductCatalog product1 = new ProductCatalog();
        product1.setItemNumber(123456);
        product1.setName("Dishwasher");
        product1.setQuantity(10);
        product1.setPrice(new BigDecimal("10.50"));
        product1.setMin(5);
        
		List<ProductCatalog> products =  new ArrayList<>();
        products.add(product1);
        products.add(product1);
        products.add(product1);
        
        byte[] excelFileBytes = null;
        try {
        	excelFileBytes = excelService.convertToExcel(products);
		} catch (IOException e) {
			LOG.error("Error generating Excel representation of product list.", e);
			
			throw new RuntimeException("Error generating Excel representation of product list.", e);
		}
        
		String currTime = DATE_FORMAT.format(new Date());
        String fileUrl = blobDao.uploadBlob(MessageFormat.format(FILENAME_TEMPLATE, currTime), 
        		excelFileBytes, EXCEL_MIME_TYPE);
        
        iftttDao.tiggerEvent(IFTTT_EVENT, "julianf.nunez@gmail.com", "BBB", fileUrl);
		
	}

}
