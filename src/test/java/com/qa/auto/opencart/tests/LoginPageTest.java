package com.qa.auto.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.auto.opencart.base.BaseTest;
import com.qa.auto.opencart.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100 - Feature name : Login Page for Demo Shop Application")
@Story("Story - 200 : Design Login Page test with dfifferent test cases")
public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	@Description("Login Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {
		String loginTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	@Description("Verify forgot password link test...")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		boolean forgotPwdLink = loginPage.isForgotPwdLinkDisplayed();
		Assert.assertEquals(forgotPwdLink, true);
		System.out.println("Verify the forgot password test");
	}

	@Test(priority = 3)
	@Description("Verify login test...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

}
