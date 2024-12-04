package com.softwaretestingboard.magento.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
public class TestBase {

    private WebDriver driver;
    private static TestBase myObj;
    private WebDriverWait wait;
    PropertyFileReader property = new PropertyFileReader();
    public static String screenShotDestinationPath;

    //implementing singleton pattern to ensure that only one drive instance of 'BaseTest' exists
    public static TestBase getInstance() {

        if(myObj == null) {

            myObj = new TestBase();

        }
        return myObj;
    }

    //returning the current web driver instance
    public WebDriver getDriver() {

        return driver;
    }

    //setting the  web driver instance
    private void setDriver(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //changing the Singleton instance if needed
    public static void setMyObj(TestBase myObj) {

        TestBase.myObj = myObj;
    }

    //opening the browser and maximizing the window
    public void openBrowser() {

        String browserName  = property.getProperty("config", "browser.name");

        if(browserName.equalsIgnoreCase("chrome")){

            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();


        }else if (browserName.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        }else {

            System.out.println("Provide a valid browser name");
        }

        driver.manage().window().maximize();
    }

    //navigating to the URL
    public void navigateToURL() {

        String url = property.getProperty("config", "url");
        driver.get(url);
    }

    //closing the browser
    public void closeBrowser() {
        driver.quit();

    }

    //waiting until the specified elements appears
    public WebElement waitUntilNextElementAppears(By locator, int timeOut) {
        WebElement element = new WebDriverWait(TestBase.getInstance().getDriver(), Duration.ofSeconds(timeOut)).until
                (ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }
    // Getter for WebDriverWait instance
    public WebDriverWait getWait() {
        return wait;
    }


}
