package com.hdsupply.xmi.service.excel;

import java.io.IOException;
import java.util.List;

import com.hdsupply.xmi.domain.ProductCatalog;

public interface ExcelService {

	byte[] convertToExcel(List<ProductCatalog> products) throws IOException;

}