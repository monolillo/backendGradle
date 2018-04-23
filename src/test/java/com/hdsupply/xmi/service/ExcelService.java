package com.hdsupply.xmi.service;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hdsupply.xmi.domain.ProductCatalog;

public class ExcelService {

    private static String[] columns = {"ItemNumber", "Name", "Price", "In Stock", "Minimun"};
    private static List<ProductCatalog> results =  new ArrayList<>();

	// Initializing employees data to insert into the excel file
    static {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);
        
        ProductCatalog product1 = new ProductCatalog();
        product1.setItemNumber(123456);
        product1.setName("Dishwasher");
        product1.setQuantity(10);
        product1.setPrice(new BigDecimal("10.50"));
        product1.setMin(5);
        
        results.add(product1);

        dateOfBirth.set(1965, 10, 15);
        results.add(product1);

        dateOfBirth.set(1987, 4, 18);
        results.add(product1);
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

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
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        
        CellStyle priceCellStyle = workbook.createCellStyle();
        priceCellStyle.setDataFormat((short)7);

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(ProductCatalog product: results) {
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
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("c:\\dev\\poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
}

