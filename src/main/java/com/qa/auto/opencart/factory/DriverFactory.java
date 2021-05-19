package com.qa.auto.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

//	private WebDriver driver;
	private Properties prop;
	public static String highlight;
	private OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger LOGGER = Logger.getLogger(DriverFactory.class);

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		LOGGER.info("Browser name is " + browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			LOGGER.info("Initializing Chrome Broswer");
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser name");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream file = null;
		String env = System.getProperty("env");
		LOGGER.info("Current Envionment is " + env);
		try {
			switch (env) {
			case "qa":
				file = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				file = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				file = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "prod":
				file = new FileInputStream("./src/test/resources/config/prod.config.properties");
				break;
			default:
				break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File fileSrc = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File fileDestination = new File(path);
		try {
			FileUtils.copyFile(fileSrc, fileDestination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
