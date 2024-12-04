package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckOutShippingPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of each web element
    String streetElement = prop.getProperty("CheckoutPage","street.element");
    String cityElement = prop.getProperty("CheckoutPage","city.element");
    String regionElement = prop.getProperty("CheckoutPage","region.element");
    String postCodeElement = prop.getProperty("CheckoutPage","postal.code.element");
    String countryElement = prop.getProperty("CheckoutPage","country.element");
    String phoneElement = prop.getProperty("CheckoutPage","phone.element");
    String fixedMethodElement = prop.getProperty("CheckoutPage","shipping.method.element");
    String nextButtonElement = prop.getProperty("CheckoutPage","next.button.element");
    String itemCountElement = prop.getProperty("CheckoutPage","item.count.element");
    String orderTitleElement = prop.getProperty("CheckoutPage","order.title.element");
    String itemNameElement = prop.getProperty("CheckoutPage","product.name.element");
    String itemQuantityElement = prop.getProperty("CheckoutPage","product.qty.element");
    String itemPriceElement = prop.getProperty("CheckoutPage","product.price.element");

    public CheckOutShippingPage setStreet(String street) throws InterruptedException {

        Thread.sleep(4000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(streetElement),20);
        driver.findElement(By.name(streetElement)).sendKeys(street);
        return this;
    }
    public CheckOutShippingPage setCity(String city) throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(cityElement),20);
        driver.findElement(By.name(cityElement)).sendKeys(city);
        return this;
    }
    public CheckOutShippingPage setState() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(regionElement),20);
        Select objSelect =new Select(driver.findElement(By.name(regionElement)));
        objSelect.selectByVisibleText("South Australia");
        return this;
    }
    public CheckOutShippingPage setPostCode(String code) throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(postCodeElement),20);
        driver.findElement(By.name(postCodeElement)).sendKeys(code);
        return this;
    }
    public CheckOutShippingPage selectCountry() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(countryElement),20);
        Select objSelect =new Select(driver.findElement(By.name(countryElement)));
        objSelect.selectByVisibleText("Australia");
        return this;
    }
    public CheckOutShippingPage setPhone(String phone) throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(phoneElement),20);
        driver.findElement(By.name(phoneElement)).sendKeys(phone);
        return this;
    }
    public CheckOutShippingPage selectFixedShippingMethod() throws InterruptedException {

        Thread.sleep(3000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(fixedMethodElement),20);
        driver.findElement(By.xpath(fixedMethodElement)).click();
        return this;
    }
    public String getItemCount() throws InterruptedException {

        Thread.sleep(2000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(itemCountElement),20);
        return driver.findElement(By.xpath(itemCountElement)).getText();

    }
    public CheckOutShippingPage clickOnOderTitle() throws InterruptedException {

        Thread.sleep(2000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(orderTitleElement),20);
        driver.findElement(By.xpath(orderTitleElement)).click();
        return this;

    }
    public String getItemName() throws InterruptedException {

        Thread.sleep(2000);
        clickOnOderTitle();
        TestBase.getInstance().waitUntilNextElementAppears(By.className(itemNameElement),20);
        return driver.findElement(By.className(itemNameElement)).getText();

    }
    public String getItemQuantity() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(itemQuantityElement),20);
        return driver.findElement(By.xpath(itemQuantityElement)).getText();

    }
    public String getItemPrice() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.cssSelector(itemPriceElement),20);
        return driver.findElement(By.cssSelector(itemPriceElement)).getText();

    }
    public String isOnCheckoutPage() throws InterruptedException {
        Thread.sleep(1000);
        return(driver.getCurrentUrl());


    }
    public CheckOutPaymentPage clickOnNextButton() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(nextButtonElement),20);
        driver.findElement(By.xpath(nextButtonElement)).click();
        return new CheckOutPaymentPage();
    }

}
