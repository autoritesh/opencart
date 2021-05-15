package com.qa.auto.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.auto.opencart.utilities.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By productNameHeader = By.cssSelector("#content h1");
	private By productMetaData = By.xpath("//div[@class='col-sm-4']//ul[1]/li");
	private By productPrice = By.xpath("//div[@class='col-sm-4']//ul[2]/li");
	private By quantity = By.cssSelector("#input-quantity");
	private By addToCart = By.cssSelector("#button-cart");
	private By productImg = By.cssSelector(".thumbnails img");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public Map<String, String> getProductInformation() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", elementUtil.doGetText(productNameHeader).trim());

		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		for (WebElement e : productMetaDataList) {
			// Brand: Apple
			// Product Code: Product 14
			// Availability: Out Of Stock
			String meta[] = e.getText().split(":");
			String metaName = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaName, metaValue);
		}

//		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
//		for (WebElement e : productMetaDataList) {
//			String meta[] = e.getText().split(":");
//			String metaName = meta[0].trim();
//			String metaValue = meta[1].trim();
//			productInfoMap.put(metaName, metaValue);
//		}

//		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
//		for (WebElement e : productPriceList) {
//			productInfoMap.put("price", e.getText().split(":")[0].trim());
//			productInfoMap.put("exTaxPrice", e.getText().split(":")[1].trim());
//		}

		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());

		return productInfoMap;
	}

	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);
	}

	public void addToCart() {
		elementUtil.doClick(addToCart);
	}

	public int getProductImages() {
		return elementUtil.getElements(productImg).size();
	}

	public String getProductInfoPageTitle(String productName) {
		return elementUtil.waitForPageTitlePresent(productName, 5);
	}

}
