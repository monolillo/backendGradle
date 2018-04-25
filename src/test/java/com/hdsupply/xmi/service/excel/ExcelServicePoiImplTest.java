package com.hdsupply.xmi.service.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.hdsupply.xmi.domain.ProductCatalog;

public class ExcelServicePoiImplTest {
	
	ExcelService fixture = new ExcelServicePoiImpl();

	@Test
	public void testConvertToExcel() throws IOException, DataSetException, DatabaseUnitException {
		
		ProductCatalog product1 = new ProductCatalog();
		product1.setName("Product 1");
		product1.setCritical(true);
		product1.setMin(5);
		product1.setMax(15);
		product1.setQuantity(10);
		product1.setItemNumber(123456);
		product1.setPrice(new BigDecimal("10.10"));
		
		ProductCatalog product2 = new ProductCatalog();
		product2.setName("Product 2");
		product2.setCritical(true);
		product2.setMin(4);
		product2.setMax(14);
		product2.setQuantity(9);
		product2.setItemNumber(123457);
		product2.setPrice(new BigDecimal("20.20"));
		
		ProductCatalog product3 = new ProductCatalog();
		product3.setName("Product 3");
		product3.setCritical(false);
		product3.setMin(10);
		product3.setMax(30);
		product3.setQuantity(20);
		product3.setItemNumber(123458);
		product3.setPrice(new BigDecimal("3.10"));	
		
		List<ProductCatalog> products = Arrays.asList(new ProductCatalog[] {product1, product2, product3});
		
		File file = ResourceUtils.getFile("classpath:excel/3products.xlsx");
		byte[] expectedFile = Files.readAllBytes(file.toPath());
		
		byte[] result = fixture.convertToExcel(products);
		
		Assertion.assertEquals(new XlsDataSet(new ByteArrayInputStream(expectedFile)), 
				new XlsDataSet(new ByteArrayInputStream(result)));

	}

}
