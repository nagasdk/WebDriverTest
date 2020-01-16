package com.util;


	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Iterator;

	public class ReadExcel_ApachePoi {

	    private static final String FILE_NAME = "src/main/resources/TestData.xls";

	    public static void main(String[] args) {

	        try {

	            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	            //Workbook workbook = new XSSFWorkbook(excelFile);
	            Workbook workbook = new HSSFWorkbook(excelFile);
	            Sheet datatypeSheet = workbook.getSheetAt(0);
	            Iterator<Row> iterator = datatypeSheet.iterator();
	            boolean flag= false;
	            int lIndex=-1;
	            while (iterator.hasNext()) {
	            	lIndex++;	            	
	                Row currentRow = iterator.next();	
	                if(lIndex>-1){
		                Iterator<Cell> cellIterator = currentRow.iterator();
		                while (cellIterator.hasNext()) {
		                    Cell currentCell = cellIterator.next();
		                    //getCellTypeEnum shown as deprecated for version 3.15
		                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
		                    String svar = null;
		                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
		                       svar=currentCell.getStringCellValue();
		                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
		                    	svar=currentCell.getNumericCellValue()+"";
		                    }
		                    if(svar.equals("Script_2")) flag=true;
		                    System.out.print( svar + "--");
		                }
		                System.out.println();	                	
	                }
	                
	                if (flag==true) break;
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	}