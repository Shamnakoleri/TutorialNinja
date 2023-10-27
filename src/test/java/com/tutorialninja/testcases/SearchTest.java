package com.tutorialninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.Base;

public class SearchTest extends Base{
	
	public WebDriver driver;
	
	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver=initialSetup(prop.getProperty("browser"));
				
	}

	@AfterMethod
	public void tearDown() {
		driver.quit(); 	
		}
	
	@Test(priority=1)
	public void verifySearchingExistingProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"The result is not matching");
	}
	
	@Test(priority=2)
	public void verifySearchingNonExistingProduct(){
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).isDisplayed(),"The result is not matching");
	}
	
	@Test(priority=3)
	public void verifySearchingwithoutPassingAnyProduct(){
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).isDisplayed(),"The result is not matching");
	}

}
