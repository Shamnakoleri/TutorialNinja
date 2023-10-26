package com.utilities.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateReport() {
	
	ExtentReports extentReports = new ExtentReports();
	File reportFile = new File (System.getProperty("user.dir")+"\\test-output\\ExtentReports\\Reports.html");
	ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
	spark.config().setTheme(Theme.DARK);
	spark.config().setReportName("Hybrid Project");
	spark.config().setDocumentTitle("NinjaProject");
	spark.config().setTimeStampFormat("dd-mmm-YYYY hh:mm:ss");
	extentReports.attachReporter(spark);
	File file = new File (System.getProperty("user.dir")+"\\src\\main\\java\\com\\properties\\qa\\config.properties");
	Properties prop = new Properties();
	
	FileInputStream fis = null;
	try {
		fis = new FileInputStream(file);
		prop.load(fis);
		
	} catch (Throwable e) {
		e.printStackTrace();
	}
	
	extentReports.setSystemInfo("Application URL:", prop.getProperty("url"));
	extentReports.setSystemInfo("Browser Name :", prop.getProperty("browser"));
	extentReports.setSystemInfo("Operating Syatem :",System.getProperty("os.name"));
	extentReports.setSystemInfo("User information :", prop.getProperty("user.name"));
	extentReports.setSystemInfo("Java Version :", prop.getProperty("java.version"));
	return extentReports;
	
	
	}
}
