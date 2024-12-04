package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPaymentPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of each web element
    String subTotalElement = prop.getProperty("PaymentPage","subtotal.element");
    String shippingElement = prop.getProperty("PaymentPage","shipping.element");
    String totalElement = prop.getProperty("PaymentPage","total.element");
    String itemCountElement = prop.getProperty("PaymentPage","item.count.element");
    String productNameElement = prop.getProperty("PaymentPage","product.name.element");
    String productQuantityElement = prop.getProperty("PaymentPage","product.qty.element");
    String productPriceElement = prop.getProperty("PaymentPage","product.price.element");
    String viewDetailsElement = prop.getProperty("PaymentPage","view.details.element");
    String sizeElement = prop.getProperty("PaymentPage","size.element");
    String colorElement = prop.getProperty("PaymentPage","color.element");
    String shippingAddressElement = prop.getProperty("PaymentPage","shipping.address.element");
    String shippingMethodElement = prop.getProperty("PaymentPage","shipping.method.element");
    String addressCheckBoxElement = prop.getProperty("PaymentPage","address.checkbox.element");
    String placeOrderButtonElement = prop.getProperty("PaymentPage","place.order.button");

    public String getSubTotal() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(subTotalElement),20);
        return driver.findElement(By.xpath(subTotalElement)).getText();

    }
    public String getShippingPrice() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(shippingElement),20);
        return driver.findElement(By.xpath(shippingElement)).getText();

    }
    public String getTotalPrice() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(totalElement),20);
        return driver.findElement(By.xpath(totalElement)).getText();


    }
    public String getItemCount() throws InterruptedException {

        Thread.sleep(2000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(itemCountElement),20);
        return driver.findElement(By.xpath(itemCountElement)).getText();

    }
    public String getItemName() throws InterruptedException {

        Thread.sleep(2000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(productNameElement),20);
        return driver.findElement(By.xpath(productNameElement)).getText();

    }
    public String getItemQuantity() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(productQuantityElement),20);
        return driver.findElement(By.xpath(productQuantityElement)).getText();

    }
    public String getItemPrice() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.cssSelector(productPriceElement),20);
        return driver.findElement(By.cssSelector(productPriceElement)).getText();

    }

    public CheckOutPaymentPage clickViewDetails() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(viewDetailsElement),20);
        driver.findElement(By.xpath(viewDetailsElement)).click();
        return this;

    }
    public String getSize() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(sizeElement),20);
        return  driver.findElement(By.xpath(sizeElement)).getText();


    }
    public String  getColor() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(colorElement),20);
        return  driver.findElement(By.xpath(colorElement)).getText();


    }
    public String getShippingAddress() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(shippingAddressElement),20);
        return driver.findElement(By.xpath(shippingAddressElement)).getText();

    }
    public String getShippingMethod() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(shippingMethodElement),20);
        return driver.findElement(By.xpath(shippingMethodElement)).getText();

    }
    public boolean isAddressChecked() throws InterruptedException {

        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.name(addressCheckBoxElement),20);
        return driver.findElement(By.name(addressCheckBoxElement)).isSelected();

    }
    public String isOnPaymentPage() throws InterruptedException {
        Thread.sleep(2000);
        return(driver.getCurrentUrl());

    }
    public CheckOutSuccessPage clickOnPlaceOrder() throws InterruptedException {

        Thread.sleep(3000);
        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(placeOrderButtonElement),20);
        driver.findElement(By.xpath(placeOrderButtonElement)).click();
        return new CheckOutSuccessPage();

    }



}
