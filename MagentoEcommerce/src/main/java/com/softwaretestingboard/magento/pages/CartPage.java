package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of the web element
    String checkOutButtonElement = prop.getProperty("CartPage","checkout.button.element");

    public CheckOutShippingPage clickOnCheckOut() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(checkOutButtonElement),20);
        driver.findElement(By.xpath(checkOutButtonElement)).click();
        return new CheckOutShippingPage();
    }
    public String getActualUrl() {

        return(driver.getCurrentUrl());

    }

}
