package com.sgic.leavesystem.test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sgic.leavesystem.util.DataStore;
import com.sgic.leavesystem.util.Log;

public class LoginTest extends BaseTest {

	  private static Logger logger = LogManager.getLogger(LoginTest.class);

	  boolean flag = false;

	  @Test
	  public void testOrangeHRM() throws Exception {
	    // driver.get("https://opensource-demo.orangehrmlive.com/");

	    DataStore.readExcelFile(excelFilePath);
	    DataStore.loadData();
	    for (int i = 0; i < DataStore.testData.get("username").size(); i++) {
	      try {
	        extentTest = extentReport.startTest("Admin Login");
	        driver.get(prop.getProperty("baseUrl"));
	        Log.startTestCase();
	        driver.findElement(By.xpath(
	            "(.//*[normalize-space(text()) and normalize-space(.)='LOGIN Panel'])[1]/following::span[1]"))
	        ///html/body/app-root/app-login/div/div/div/div/div[1]/h4
	            .click();
	        driver.findElement(By.id("txtUsername")).clear();
	        driver.findElement(By.id("txtUsername"))
	            .sendKeys(DataStore.testData.get("username").get(i));
	        extentTest.log(LogStatus.PASS, "Entered username");
	        logger.log(Level.INFO, "********* Entered username **********");
	        driver.findElement(By.id("txtPassword")).clear();
	        driver.findElement(By.id("txtPassword"))
	            .sendKeys(DataStore.testData.get("password").get(i));
	        extentTest.log(LogStatus.PASS, "Entered password");
	        logger.log(Level.INFO, "********* Entered password **********");
	        driver.findElement(By.id("btnLogin")).click();
	        extentTest.log(LogStatus.PASS, "Login button clicked");
	        logger.log(Level.INFO, "********* Login button clicked **********");
	        Thread.sleep(500);
	        driver.findElement(By.id("welcome")).click();
	        Thread.sleep(500);
	        try {
	          assertEquals(driver.findElement(By.id("welcome")).getText(), "Welcomex Admin");
	          logger.log(Level.INFO, "Verified Welcome Message");
	        } catch (Error e) {
	          String file = captureScreeShot();
	          extentTest.log(LogStatus.FAIL, "Failed to find the message");
	          logger.log(Level.ERROR, "Failed to find mesasge. \n" + "Screenshot file: " + file);
	        }

	        driver.findElement(By.linkText("Logout")).click();
	        Log.endTestCase();
	        extentReport.endTest(extentTest);
	      } catch (Exception e) {
	        // TODO: handle exception
	      }
	    }



	    // extentTest = extentReport.startTest("Admin Login");
	    // driver.get(prop.getProperty("baseUrl"));
	    // Log.startTestCase();
	    // driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='LOGIN
	    // Panel'])[1]/following::span[1]")).click();
	    // driver.findElement(By.id("txtUsername")).clear();
	    // driver.findElement(By.id("txtUsername")).sendKeys("Admin");
	    // extentTest.log(LogStatus.PASS, "Entered username");
	    // logger.log(Level.INFO, "********* Entered username **********");
	    // driver.findElement(By.id("txtPassword")).clear();
	    // driver.findElement(By.id("txtPassword")).sendKeys("admin123");
	    // extentTest.log(LogStatus.PASS, "Entered password");
	    // logger.log(Level.INFO, "********* Entered password **********");
	    // driver.findElement(By.id("btnLogin")).click();
	    // extentTest.log(LogStatus.PASS, "Login button clicked");
	    // logger.log(Level.INFO, "********* Login button clicked **********");
	    // Thread.sleep(2000);
	    // driver.findElement(By.id("welcome")).click();
	    // Thread.sleep(2000);
	    // try {
	    // assertEquals(driver.findElement(By.id("welcome")).getText(), "Welcomex Admin");
	    // logger.log(Level.INFO,"Verified Welcome Message");
	    // } catch (Error e) {
	    // String file = captureScreeShot();
	    // extentTest.log(LogStatus.FAIL, "Failed to find the message");
	    // logger.log(Level.ERROR, "Failed to find mesasge. \n" + "Screenshot file: " + file);
	    // }
	    // driver.findElement(By.linkText("Logout")).click();
	    // Log.endTestCase();
	    // extentReport.endTest(extentTest);
	  }

	}
