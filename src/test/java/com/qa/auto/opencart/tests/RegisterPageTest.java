package com.qa.auto.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.auto.opencart.base.BaseTest;
import com.qa.auto.opencart.utilities.Constants;
import com.qa.auto.opencart.utilities.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerationPageSetup() {
		registerPage = loginPage.navigateToRegisterLink();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object[][] data = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegisterationTest(String fname, String lname, String email, String phoneNo, String pwd,
			String subscribe) {
		registerPage.accountRegistration(fname, lname, email, phoneNo, pwd, subscribe);
	}
}
