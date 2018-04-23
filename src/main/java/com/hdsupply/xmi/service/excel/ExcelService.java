package com.hdsupply.xmi.service.excel;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.hdsupply.xmi.domain.ProductCatalog;

public interface ExcelService {

	byte[] convertToExcel(List<ProductCatalog> products) throws IOException;

}