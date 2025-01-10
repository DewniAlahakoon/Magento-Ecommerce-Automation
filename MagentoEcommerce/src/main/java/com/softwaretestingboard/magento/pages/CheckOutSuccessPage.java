package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutSuccessPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of each web element
    String successMessageElement = prop.getProperty("CheckOutSuccessPage","success.message.element");
    String continueButtonElement = prop.getProperty("CheckOutSuccessPage","continue.button.element");
    String printLinkElement = prop.getProperty("CheckOutSuccessPage","print.link");

    public String getSuccessMessage() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(successMessageElement),20);
        return driver.findElement(By.xpath(successMessageElement)).getText();

    }
    public String confirmPrintLink() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(printLinkElement),20);
        return  driver.findElement(By.xpath(printLinkElement)).getText();

    }
    public String confirmContinueButton() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(continueButtonElement),20);
        return driver.findElement(By.xpath(continueButtonElement)).getText();


    }
    public String isOnCheckoutSuccessPage() throws InterruptedException {
        Thread.sleep(4000);
       return(driver.getCurrentUrl());

    }


}
