package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductStorePage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of the web element
    String prodOneElement = prop.getProperty("ProductStorePage","one.product.element");

    public ProductPage clickOneProduct() throws InterruptedException {

        Thread.sleep(4000);

        TestBase.getInstance().waitUntilNextElementAppears(By.xpath(prodOneElement),20);
        driver.findElement(By.xpath(prodOneElement)).click();
        return new ProductPage();
    }


}
