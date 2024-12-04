package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of each web element
    String sizeElement = prop.getProperty("ProductPage","size.element");
    String colorElement = prop.getProperty("ProductPage","color.element");
    String qtyElement = prop.getProperty("ProductPage","quantity.element");
    String cartButtonElement = prop.getProperty("ProductPage","cart.button.element");
    String cartLinkElement = prop.getProperty("ProductPage","cart.link.element");


    public ProductPage setSize() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.id(sizeElement),20);
        driver.findElement(By.id(sizeElement)).click();
        return this;
    }
    public ProductPage setColor() throws InterruptedException {

        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(colorElement),10);
        driver.findElement(By.id(colorElement)).click();
        return this;
    }
    public ProductPage setQuantity() throws InterruptedException {

        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(qtyElement),10);
        driver.findElement(By.id(qtyElement)).click();
        return this;
    }
    public ProductPage clickOnAddToCart() throws InterruptedException {

        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(cartButtonElement),10);
        driver.findElement(By.id(cartButtonElement)).click();
        return this;
    }
    public CartPage clickOnCartLink() throws InterruptedException {

        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.linkText(cartLinkElement),10);
        driver.findElement(By.linkText(cartLinkElement)).click();
        return new CartPage();
    }
}
