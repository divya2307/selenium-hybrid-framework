package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	
	static XSSFWorkbook wbWorkbook;

  public static Object[][] getDataFromSheet(String sheetname){
	  
	  System.out.println("***************** Loading Data from Excel *************************");
	  
	  Object[][] arr = null;
	  try {
	  
	    wbWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/testdata/testData.xlsx")));
		
		XSSFSheet sheet = wbWorkbook.getSheet(sheetname);
		
		int row = sheet.getPhysicalNumberOfRows();		
		int col = sheet.getRow(0).getPhysicalNumberOfCells();	
		
		arr = new Object[row-1][col];
		
		for (int i = 1 ; i<row ; i++) {		
			for (int j=0 ; j<col ; j++) {	
				arr[i-1][j] = getData(sheetname, i, j);
			}
		}
		
		wbWorkbook.close();
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	  
	  return arr;
  }
  
  public static String getData(String sheetName, int row, int column) {
	  
	 XSSFCell cell =  wbWorkbook.getSheet(sheetName).getRow(row).getCell(column);
	 String data = "";
	 
	 if (cell.getCellType() == CellType.STRING) {
		 
		 data = cell.getStringCellValue();
		 
	 }
	 else if (cell.getCellType() == CellType.NUMERIC) {
		 
		 
		double num = cell.getNumericCellValue();
		data = String.valueOf(num);
		
	 }
	 else if (cell.getCellType() == CellType.BLANK) {
		 data = "";
	 }
	  
	  return data;
  }
  
  
  
}
