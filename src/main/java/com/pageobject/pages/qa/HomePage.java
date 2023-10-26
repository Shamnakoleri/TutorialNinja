package com.pageobject.pages.qa;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "a[title='My Account']")
	private WebElement myAccountDropDown;
	
	@FindBy(linkText = "Login")
	private WebElement loginButton;
	
	@FindBy(linkText = "Register")
	private WebElement registerButton;
	
	
	public void clickMyAccounts() {
		myAccountDropDown.click();
	}
	
	public LoginPage clickLogin() {
		loginButton.click();
		return new LoginPage(driver);
	}
	
	public void clickRegister() {
		registerButton.click();
	}
	
}
