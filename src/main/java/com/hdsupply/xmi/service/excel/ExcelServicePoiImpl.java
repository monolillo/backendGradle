package com.hdsupply.xmi.service.excel;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.hdsupply.xmi.domain.ProductCatalog;

@Service
public class ExcelServicePoiImpl implements ExcelService {

    private static final String[] COLUMNS = {"ItemNumber", "Name", "Price", "In Stock", "Minimun"};

    /* (non-Javadoc)
	 * @see com.hdsupply.xmi.service.excel.ExcelService#convertToExcel(java.util.List)
	 */
    @Override
	public byte[] convertToExcel(List<ProductCatalog> products) throws IOException {
    	
    	byte[] resultBytes = null;
    	
    	try(Workbook workbook = new XSSFWorkbook()) {	// new HSSFWorkbook() for generating `.xls` file

	        /* CreationHelper helps us create instances for various things like DataFormat, 
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	        CreationHelper createHelper = workbook.getCreationHelper();
	
	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("Low Stock");
	
	        // Create a Font for styling header cells
	        XSSFFont headerFont = (XSSFFont) workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 12);
	        XSSFColor customColor = new XSSFColor(new Color(254, 203, 68));
	        headerFont.setColor(customColor);
	
	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	
	        // Create a Row
	        Row headerRow = sheet.createRow(0);
	
	        // Creating cells
	        for(int i = 0; i < COLUMNS.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(COLUMNS[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	
	        // Create Cell Style for formatting Date
	        CellStyle dateCellStyle = workbook.createCellStyle();
	        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	        
	        CellStyle priceCellStyle = workbook.createCellStyle();
	        priceCellStyle.setDataFormat((short)7);
	
	        // Create Other rows and cells with employees data
	        int rowNum = 1;
	        for(ProductCatalog product: products) {
	            Row row = sheet.createRow(rowNum++);
	
	            row.createCell(0)
	                    .setCellValue(product.getItemNumber());
	
	            row.createCell(1)
	                    .setCellValue(product.getName());
	            
	            Cell priceCell = row.createCell(2);
	            priceCell.setCellValue(product.getPrice().doubleValue());
	            priceCell.setCellStyle(priceCellStyle);
	            
	            row.createCell(3)
	            	.setCellValue(product.getQuantity());
	
	            row.createCell(4)
	        		.setCellValue(product.getMin());
	
	        }
	
			// Resize all columns to fit the content size
	        for(int i = 0; i < COLUMNS.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	
	        // Write the output to a file
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        workbook.write(output);
	        resultBytes = output.toByteArray();
	
	        // Closing the workbook
	        workbook.close();
    	}
        
        return resultBytes;
    }
}

