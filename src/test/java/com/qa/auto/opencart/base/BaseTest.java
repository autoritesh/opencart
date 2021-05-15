package com.qa.auto.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.auto.opencart.factory.DriverFactory;
import com.qa.auto.opencart.pages.AccountsPage;
import com.qa.auto.opencart.pages.LoginPage;
import com.qa.auto.opencart.pages.ProductInfoPage;
import com.qa.auto.opencart.pages.RegisterPage;

public class BaseTest {

	public DriverFactory driverFactory;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;

	@BeforeTest
	public void setUp() {
		driverFactory = new DriverFactory();
		prop = driverFactory.initProp();
		driver = driverFactory.initDriver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
