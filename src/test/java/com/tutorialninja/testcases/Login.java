package com.tutorialninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobject.pages.qa.AccountPage;
import com.pageobject.pages.qa.HomePage;
import com.pageobject.pages.qa.LoginPage;
import com.qa.test.Base;
import com.utilities.tutorial.Utilities;

public class Login extends Base{
	
	
	public WebDriver driver;
	LoginPage loginPage ;
	
	public Login() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver=initialSetup(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccounts();
		loginPage = homePage.clickLogin();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit(); 	
		}
	
	
	@Test(priority =1,dataProvider="validTestData")
	public void verifyValidLoginWithCredentials(String email,String pwd) {
		loginPage.setEmail(email);
		loginPage.setPassword(pwd);
		AccountPage accPage = loginPage.clickLogin();
		Assert.assertTrue(accPage.editAccount());
	
	}
	
	@DataProvider(name="validTestData")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getDataFromExcel("Login");
		return data;
	}
	
	@Test(priority =2)
	public void verifyInvalidLoginUsingInvalidEmailandInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.dateTimeEmail());
		driver.findElement(By.id("input-password")).sendKeys("passwor");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
			
	}
	
	@Test(priority =3)
	public void verifyInvalidLoginUsingValidEmailandInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys("passwor");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
			
		
	}
	
	@Test(priority =4)
	public void verifyInvalidLoginUsingInvalidEmailandValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.dateTimeEmail());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
			
		
	}
	@Test(priority =5)
	public void verifyInvalidLoginUsingEmptyEmailandPassword() {
		driver.findElement(By.id("input-email")).sendKeys(" ");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
		
		
	}
	
	

	
	

}
