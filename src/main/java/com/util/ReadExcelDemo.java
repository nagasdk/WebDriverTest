package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelDemo 
{
    public static void main(String[] args) 
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("src/main/resources/TestData.xls"));
            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int iIndex=-1;
            while (rowIterator.hasNext()) 
            {
            	iIndex++;	            	
                Row row = rowIterator.next();
                
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if(iIndex>0){
                	System.out.print(iIndex+":"+" ");
                	
                	int jIndex=-1;
                	String uniqueIdentifier=null;
                	String tradeID=null;
                	String CMV=null;
                	
                    while (cellIterator.hasNext()) 
                    {
                    	
                    	jIndex++;
                    	String sCellValue = null;

                    	
                        Cell currentCell = cellIterator.next();
                        if (currentCell.getCellTypeEnum() == CellType.STRING) {
    	                       sCellValue=currentCell.getStringCellValue();
    	                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
    	                    	sCellValue=currentCell.getNumericCellValue()+"";
    	                }
                        if(jIndex==1){
                        	uniqueIdentifier=sCellValue;
                        }else if(jIndex==5){
                        	tradeID=sCellValue;
                        }else if(jIndex==6){
                        	CMV=sCellValue;
                        }
                        
                        //System.out.print(sCellValue + "t");                        
                    }
                    System.out.println("uniqueIdentifier:"+uniqueIdentifier+ " tradeID:"+tradeID+ " , CMV:"+CMV);
                }
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}