package com.utilities.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT = 10;
	public static final int PAGE_LOAD_TIMEOUT =10;
	
	
	public static String dateTimeEmail() {
		Date date = new Date();
		String timestamp = date.toString().replace(":", "_").replace(" ", "_");
		return "sk"+timestamp+"@gmail.com";
		
	}
	
	public static void writeCredentialstoFile(String email, String pwd) {
		
		String destination = System.getProperty("user.dir")+"\\usercredentials.txt";
		try {
			FileWriter file = new FileWriter(destination,true);
			file.write("\n");
			file.write(email);
			file.write(pwd);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcFile, new File(destPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destPath;
	}
	
	public static Object[][] getDataFromExcel(String sheetName) {
		String excelPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\testdata\\qa\\validData.xlsx";
		//System.out.println(excelPath);
		File file = new File(excelPath);
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rows][col];
		for(int i=0;i<rows;i++) {
			XSSFRow rowData = sheet.getRow(i+1);
			for (int j=0;j<col;j++) {
				XSSFCell cellData = rowData.getCell(j);
				CellType cellType = cellData.getCellType();
				switch(cellType) {
				case STRING:  
					data[i][j]=cellData.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cellData.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]= cellData.getBooleanCellValue();
					break;
				default:
					break;
				}
				
					
					
				}
			}
		return data;
		}
		
	}


