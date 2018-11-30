package com.sgic.leavesystem.test;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sgic.leavesystem.util.Functions;


public class BaseTest {
	// String driverPathChrome =
	// "C:\\Users\\Dilshanth\\Documents\\GitHub\\eclipse\\automation\\webdrivers\\chromedriver.exe";
	// String driverPathFirefox =
	// "C:\\Users\\Dilshanth\\Documents\\GitHub\\eclipse\\automation\\webdrivers\\chromedriver.exe";
	protected static WebDriver driver;
	private String browser;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	protected static Properties prop = new Properties();

	// private static String screenShotFolderPath =
	// "C:\\Users\\Dilshanth\\Documents\\GitHub\\eclipse\\automation\\src\\test\\resources\\screenshots";
	private static String screenShotFolderPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\screenshots\\";
	private static String reportFolderPath = "\\src\\test\\resources\\results";

	// public static ExtentReports extentReport = new
	// ExtentReports(System.getProperty("user.dir") +
	// "\\src\\test\\resources\\results");
	public static ExtentReports extentReport = new ExtentReports(reportFolderPath);
	public static ExtentTest extentTest;

	public static String excelFilePath = "\\src\\test\\resources\\data\\credentials.xlsx";

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		baseUrl = "https://www.katalon.com/";

		InputStream inputStream = null;
		inputStream = this.getClass().getClassLoader().getResourceAsStream("config/config.properties");
		prop.load(inputStream);

		browser = System.getProperty("browser");
		if (browser == null || browser.isEmpty()) {
			browser = prop.getProperty("browser");
		}

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxDriverPath"));
			driver = new FirefoxDriver();
			break;
		default:
			driver = new ChromeDriver();
			break;
		}

//		    System.out.println("Launching Chrome Browser...");
//		    System.setProperty("webdriver.chrome.driver", driverPathChrome);   
//		    driver = new ChromeDriver();

//		    System.out.println("Launching Firefox Browser...");
//		    System.setProperty("webdriver.gecko.driver", driverPathFirefox);   
//		    driver = new FirefoxDriver();
	}

	public static String captureScreeShot() {
		String filename = Functions.getTimeStamp("yyyy-MM-dd_HH:mm:ss") + ".jpg";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File("e:\\screenshot\\screenshot.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		return filename;
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		extentReport.flush();
		driver.quit();
	}

}
