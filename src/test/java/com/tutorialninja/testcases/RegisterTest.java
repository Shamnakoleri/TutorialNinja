package com.tutorialninja.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.Base;
import com.utilities.tutorial.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver=initialSetup(prop.getProperty("browser"));
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit(); 	
		}
	
	@Test(priority=1)
	public void verifyRegisterByProvidingMandatoryFields() throws InterruptedException {
		String email = Utilities.dateTimeEmail();
		driver.findElement(By.id("input-firstname")).sendKeys("sk1");
		driver.findElement(By.id("input-lastname")).sendKeys("you");
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("567890543");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value='Continue']")).submit();
		String expectedMessage = "Your Account Has Been Created!";
		System.out.println(expectedMessage);
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		System.out.println(actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage),"The Expected message is not matching");
		
		
		Utilities.writeCredentialstoFile(email,prop.getProperty("validPassword"));
		
	  
	}
	
	@Test(priority=2)
	public void verifyRegisterByProvidingAllFields() {
		String email = Utilities.dateTimeEmail();
		driver.findElement(By.id("input-firstname")).sendKeys("sk1");
		driver.findElement(By.id("input-lastname")).sendKeys("you");
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("567890543");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input[value='Continue']")).submit();
		String expectedMessage = "Your Account Has Been Created!";
		System.out.println(expectedMessage);
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		System.out.println(actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage),"The Expected message is not matching");

		Utilities.writeCredentialstoFile(email,prop.getProperty("validPassword"));
		
	  
	}
	
	@Test(priority=3)
	public void verifyRegisterByProvidingExistingEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys("sk11");
		driver.findElement(By.id("input-lastname")).sendKeys("you1");
		driver.findElement(By.id("input-email")).sendKeys("skFri_Oct_20_22_10_54_PDT_2023@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("567890543");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input[value='Continue']")).submit();
		String expectedMessage = "Warning: E-Mail Address is already registered!";
		System.out.println(expectedMessage);
		String actualMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		System.out.println(actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage),"The Expected message is not matching");
			  
	}
	
	@Test(priority=4)
	public void verifyRegisterByProvidingEmptyFields() {
		driver.findElement(By.cssSelector("input[value='Continue']")).submit();
		String expectedMessagePrivacy = "Warning: You must agree to the Privacy Policy!";
		System.out.println(expectedMessagePrivacy);
		
		String actualMessagePrivacy = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		System.out.println(actualMessagePrivacy);
		Assert.assertTrue(actualMessagePrivacy.contains(expectedMessagePrivacy),"The Expected message is not matching");
		String expectedMessageFirstName = "First Name must be between 1 and 32 characters!";
		String actualMessageFirstName = driver.findElement(By.xpath("//div[text()='First Name must be between 1 and 32 characters!']")).getText();
		String expectedMessageLastName = "Last Name must be between 1 and 32 characters!";
		String actualMessageLastName = driver.findElement(By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']")).getText();
		String expectedMessageEmail = "E-Mail Address does not appear to be valid!";
		String actualMessageEmail = driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
		String expectedMessageTelephone = "Telephone must be between 3 and 32 characters!";
		String actualMessageTelephone  = driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']")).getText();
		String expectedMessagePassword = "Password must be between 4 and 20 characters!";
		String actualMessagePassword = driver.findElement(By.xpath("//div[text()='Password must be between 4 and 20 characters!']")).getText();
		System.out.println(expectedMessageLastName);
		Assert.assertTrue(actualMessagePrivacy.contains(expectedMessagePrivacy),"The Expected message is not matching");
		System.out.println(actualMessageFirstName);
		Assert.assertTrue(actualMessageFirstName.contains(expectedMessageFirstName),"The Expected message for First Name is not matching");
		System.out.println(actualMessageLastName);
		Assert.assertTrue(actualMessageLastName.contains(expectedMessageLastName),"The Expected message for First Name is not matching");
		System.out.println(actualMessageEmail);
		Assert.assertTrue(actualMessageEmail.contains(expectedMessageEmail),"The Expected message for First Name is not matching");
		System.out.println(actualMessageTelephone);
		Assert.assertTrue(actualMessageTelephone.contains(expectedMessageTelephone),"The Expected message for First Name is not matching");
		System.out.println(actualMessagePassword);
		Assert.assertTrue(actualMessagePassword.contains(expectedMessagePassword),"The Expected message for First Name is not matching");
		
	}



}
