package com.hdsupply.xmi.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.FilterNotification;
import com.hdsupply.xmi.domain.ProductCatalog;
import com.hdsupply.xmi.domain.XmiUser;
import com.hdsupply.xmi.repository.AzureBlobDao;
import com.hdsupply.xmi.repository.IftttDao;
import com.hdsupply.xmi.service.excel.ExcelService;

@Service
public class NotificationEmailServiceImpl implements NotificationEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(NotificationEmailServiceImpl.class);
	
	private static final String DATE_TEMPLATE = "MM-dd-yyyy_HH-mm-ss";
	private static final String FILENAME_TEMPLATE = "Product-list_{0}.xlsx";
	
	private static final String IFTTT_EVENT = "xmi_product_list";
	private static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	@Autowired
	private AzureBlobDao blobDao;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private IftttDao iftttDao;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void emailNotifications(FilterNotification filter) {
		
		List<ProductCatalog> listProductCatalog = notificationService.getNotifications(filter);
        
        byte[] excelFileBytes = null;
        try {
        	excelFileBytes = excelService.convertToExcel(listProductCatalog);
		} catch (IOException e) {
			LOG.error("Error generating Excel representation of product list.", e);
			
			throw new UncheckedIOException("Error generating Excel representation of product list.", e);
		}
        
        XmiUser user = (XmiUser) userDetailsService.loadUserByUsername(filter.getUser());
        
		String currTime = new SimpleDateFormat(DATE_TEMPLATE).format(new Date());
        String fileUrl = blobDao.uploadBlob(MessageFormat.format(FILENAME_TEMPLATE, currTime), 
        		excelFileBytes, EXCEL_MIME_TYPE);
        
        iftttDao.tiggerEvent(IFTTT_EVENT, user.getEmail(), "BBB", fileUrl);
		
	}

}
