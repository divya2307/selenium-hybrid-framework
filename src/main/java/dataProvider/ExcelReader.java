package dataProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	

  public static Object[][] getDataFromSheet(String sheetname){
	  
	  System.out.println("***************** Loading Data from Excel *************************");
	  
	  Object[][] arr = null;
	  try {
		  
		  InputStream inputStream = ExcelReader.class
				    .getClassLoader()
				    .getResourceAsStream("testdata/testData.xlsx");
		  
		  if (inputStream == null) {
	            throw new RuntimeException("testData.xlsx file not found in src/test/resources/testdata");
	        }
	  
		XSSFWorkbook wbWorkbook = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wbWorkbook.getSheet(sheetname);
		
		int row = sheet.getPhysicalNumberOfRows();		
		int col = sheet.getRow(0).getPhysicalNumberOfCells();	
		
		arr = new Object[row-1][col];
		
		for (int i = 1 ; i<row ; i++) {		
			for (int j=0 ; j<col ; j++) {	
				arr[i-1][j] = getData(sheet, i, j);
			}
		}
		
		wbWorkbook.close();
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	  
	  return arr;
  }
  
  public static String getData(XSSFSheet sheet, int row, int column) {
	  
	 XSSFCell cell =  sheet.getRow(row).getCell(column);
	 String data = "";
	 
	 if (cell == null || cell.getCellType() == CellType.BLANK) {
	        data = "";
	    } else if (cell.getCellType() == CellType.STRING) {
	        data = cell.getStringCellValue();
	    } else if (cell.getCellType() == CellType.NUMERIC) {
	        double num = cell.getNumericCellValue();
	        data = String.valueOf(num);
	    } else if (cell.getCellType() == CellType.BOOLEAN) {
	        data = String.valueOf(cell.getBooleanCellValue());
	  }
	  
	  return data;
  }
  
  
  
}
