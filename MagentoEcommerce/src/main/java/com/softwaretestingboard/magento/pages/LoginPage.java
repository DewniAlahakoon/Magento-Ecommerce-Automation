package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //retrieving locator values of each web element
    String emailElement = prop.getProperty("LoginPage","email.element");
    String passwordElement = prop.getProperty("LoginPage","password.element");
    String loginElement = prop.getProperty("LoginPage","login.button.element");


    public LoginPage setEmail(String userName) throws InterruptedException {
        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(emailElement),10);
        driver.findElement(By.id(emailElement)).sendKeys(userName);
        return this;

    }
    public LoginPage setPassword(String password) throws InterruptedException {
        Thread.sleep(1000);

        TestBase.getInstance().waitUntilNextElementAppears(By.id(passwordElement),10);
        driver.findElement(By.id(passwordElement)).sendKeys(password);
        return this;

    }
    public LoginSuccessPage clickOnLogin() throws InterruptedException {
        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.id(loginElement),10);
        driver.findElement(By.id(loginElement)).click();
        return new LoginSuccessPage();

    }

}
