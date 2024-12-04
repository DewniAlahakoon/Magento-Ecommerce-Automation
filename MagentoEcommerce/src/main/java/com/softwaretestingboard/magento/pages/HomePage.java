package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of each web element
    String accountElement = prop.getProperty("HomePage","create.account.element");
    String loginElement = prop.getProperty("HomePage","login.link.element");
    String womenElement = prop.getProperty("HomePage","women.element");
    String topsElement = prop.getProperty("HomePage","tops.element");
    String jacketsElement = prop.getProperty("HomePage","jackets.element");


    public RegisterPage clickOnCreateAccount() throws InterruptedException {

        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.linkText(accountElement),10);
        driver.findElement(By.linkText(accountElement)).click();
        return new RegisterPage();
    }
    public LoginPage clickOnLoginLink() throws InterruptedException {

        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.linkText(loginElement),10);
        driver.findElement(By.linkText(loginElement)).click();
        return new LoginPage();
    }
    public HomePage  hoverOverWomenDropDown() throws InterruptedException {
        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(womenElement),20);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(womenElement))).perform();
        return this;

    }
    public HomePage  hoverOverTopsDropDown() throws InterruptedException {
        Thread.sleep(2000);
        TestBase.getInstance().waitUntilNextElementAppears(By.id(topsElement),20);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(topsElement))).perform();

        return this;

    }
    public ProductStorePage clickOnJackets() throws InterruptedException {
        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(jacketsElement),10);
        driver.findElement(By.id(jacketsElement)).click();

        return new ProductStorePage();

    }


}
