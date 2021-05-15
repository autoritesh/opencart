package com.qa.auto.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.auto.opencart.utilities.Constants;
import com.qa.auto.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private By searchText = By.cssSelector("input[name='search']");
	private By searchButton = By.cssSelector("span.input-group-btn");
	private By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
	private By resultItems = By.cssSelector("div.product-thumb h4 a");

	private By accountLinksList = By.cssSelector("div#content h2");
	private By accountHeader = By.cssSelector("div#logo a");
	private WebDriver driver;
	private ElementUtil elementUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	@Step("get Account page title")
	public String getAccountsPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	@Step("get Account page Header")
	public String getAccountsPageHeader() {
		return elementUtil.doGetText(accountHeader);
	}

	@Step("get Account Links Count")
	public int getAccountLinksCount() {
		return elementUtil.getElements(accountLinksList).size();
	}

	@Step("get Account Links")
	public List<String> getAccountLinksValue() {
		List<String> accountsList = new ArrayList<String>();
		List<WebElement> links = elementUtil.getElements(accountLinksList);
		for (WebElement e : links) {
			accountsList.add(e.getText());
		}
		return accountsList;
	}

	@Step("Search Product Name")
	public Boolean doSearch(String productName) {
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if (elementUtil.getElements(searchItemResult).size() > 0) {
			return true;
		}
		return false;
	}

	@Step("Select Product From Results")
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList = elementUtil.getElements(resultItems);
		System.out.println("Total no. of items displayed " + resultItemsList.size());

		for (WebElement e : resultItemsList) {
			if (e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
