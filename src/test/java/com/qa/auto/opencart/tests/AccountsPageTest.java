package com.qa.auto.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.auto.opencart.base.BaseTest;
import com.qa.auto.opencart.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 300 - Feature name : Account Page for Demo Shop Application")
@Story("Story - 400 : Design Account Page test with dfifferent test cases")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void doAccountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	@Description("Accounts Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	@Description("Accounts Page Header Test...")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageHeaderTest() {
		String header = accountsPage.getAccountsPageHeader();
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test(priority = 3)
	@Description("Accounts Page Seaction count Test...")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageSectionsCount() {
		int count = accountsPage.getAccountLinksCount();
		Assert.assertTrue(count == Constants.ACCOUNT_PAGE_SECTION_COUNT);
	}

	@Test(priority = 4)
	@Description("Accounts Page Section Test...")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageSectionsList() {
		List<String> sectionList = accountsPage.getAccountLinksValue();
		System.out.println(sectionList);
	}

//	@Test(priority = 5)
//	@Description("Product Search with iMac...")
//	@Severity(SeverityLevel.CRITICAL)
//	public void searchTest_iMac() {
//		Assert.assertTrue(accountsPage.doSearch("iMac"));
//	}

	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "iMac" }, { "MacBook Air" } };
	}

	@Test(priority = 5, dataProvider = "getProductName")
	@Description("Product Search with Macbook Air...")
	@Severity(SeverityLevel.CRITICAL)
	public void searchTest_MacBook(String productName) {
		Assert.assertTrue(accountsPage.doSearch(productName));
	}

	@Test(priority = 6)
	public void verifySearchResultsTest() {
		// accountsPage.doSearch("MacBook Air");
		accountsPage.selectProductFromResults("MacBook Air");
	}

}
