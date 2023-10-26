package com.pageobject.pages.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement inputEmail;
	
	@FindBy(id="input-password")
	private WebElement inputPassword;
	
	@FindBy(css="input[type='submit']")
	private WebElement loginButton;
	
	public void setEmail(String email) {
		inputEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		inputPassword.sendKeys(password);
	}
	public AccountPage clickLogin() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	}
	


