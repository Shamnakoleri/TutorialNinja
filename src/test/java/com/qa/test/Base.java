package com.qa.test;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utilities.tutorial.Utilities;


public class Base {
	WebDriver driver;
	public Properties prop;
	
	public Base() {
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\properties\\qa\\config.properties");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(propfile);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public WebDriver initialSetup(String browserName) {
		
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIMEOUT));
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	
	}
	
