package com.listener.qa;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utilities.tutorial.ExtentReporter;
import com.utilities.tutorial.Utilities;

public class ProjectListener implements ITestListener{
	ExtentReports extentReport;
	WebDriver driver;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
	 extentReport = ExtentReporter.generateReport();		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		ExtentTest eTest = extentReport.createTest(testName);
		eTest.log(Status.INFO, testName+" => The test started to execute");
		 
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest eTest = extentReport.createTest(testName);
		eTest.log(Status.PASS, testName+" => successfully Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest eTest = extentReport.createTest(testName);
		
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		String destPath = Utilities.captureScreenshot(driver,testName );
		eTest.addScreenCaptureFromPath(destPath);
		eTest.log(Status.INFO,result.getThrowable());
		eTest.log(Status.FAIL, testName+" =>  Failed");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest eTest = extentReport.createTest(testName);
		eTest.log(Status.SKIP, testName+" =>  Skipped");
		eTest.log(Status.INFO,result.getThrowable());
		
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ExtentTest eTest = extentReport.createTest(testName);
		eTest.log(Status.FAIL, testName+" => Failed due to timeout");
		eTest.log(Status.INFO,result.getThrowable());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentREports\\Reports.html";
		File extentReportPath = new File (pathOfExtentReport);				
		try {
			Desktop.getDesktop().browse(extentReportPath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
