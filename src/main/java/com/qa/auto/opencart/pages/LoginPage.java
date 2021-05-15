package com.qa.auto.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.auto.opencart.utilities.Constants;
import com.qa.auto.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgotPwd = By.cssSelector("div.form-group a");
	private By register = By.linkText("Register");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	@Step("get Login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}

	@Step("checking forgot password link exists")
	public Boolean isForgotPwdLinkDisplayed() {
		return elementUtil.doIsDisplayed(forgotPwd);
	}

	@Step("Login username is {0} and password is {1}")
	public AccountsPage doLogin(String uname, String pass) {
		elementUtil.doSendKeys(username, uname);
		elementUtil.doSendKeys(password, pass);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);

	}

	@Step("Navigate to Register link")
	public RegisterPage navigateToRegisterLink() {
		System.out.println("Navigate to Register Page");
		elementUtil.doClick(register);
		return new RegisterPage(driver);
	}

}
