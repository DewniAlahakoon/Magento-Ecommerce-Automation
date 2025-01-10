package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.PropertyFileReader;
import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {


    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    //creating an object from PropertyFileReader class
    PropertyFileReader prop = new PropertyFileReader();

    //accessing locator value of web elements
    String firstNameElement = prop.getProperty("RegisterPage","firstname.element");
    String lastNameElement = prop.getProperty("RegisterPage","lastname.element");
    String emailElement = prop.getProperty("RegisterPage","email.element");
    String passwordElement = prop.getProperty("RegisterPage","password.element");
    String confirmPasswordElement = prop.getProperty("RegisterPage","confirm.password.element");
    String createAccountButtonElement = prop.getProperty("RegisterPage","create.button.element");


    public RegisterPage setFirstName(String firstName) throws InterruptedException {
        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.id(firstNameElement),10);
        driver.findElement(By.id(firstNameElement)).sendKeys(firstName);
        return this;

    }
    public RegisterPage setLastName(String lastName) throws InterruptedException {
        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.id(lastNameElement),10);
        driver.findElement(By.id(lastNameElement)).sendKeys(lastName);
        return this;

    }
    public RegisterPage setEmail(String email) throws InterruptedException {
        Thread.sleep(1000);
        TestBase.getInstance().waitUntilNextElementAppears(By.id(emailElement),10);
        driver.findElement(By.id(emailElement)).sendKeys(email);
        return this;

    }
    public RegisterPage setPassword(String password){

        TestBase.getInstance().waitUntilNextElementAppears(By.id(passwordElement),10);
        driver.findElement(By.id(passwordElement)).sendKeys(password);
        return this;

    }
    public RegisterPage setConfirmPassword(String confirmPassword){

        TestBase.getInstance().waitUntilNextElementAppears(By.id(confirmPasswordElement),10);
        driver.findElement(By.id(confirmPasswordElement)).sendKeys(confirmPassword);
        return this;

    }
    public RegisterSuccessPage clickOnCreateAnAccount(){

        TestBase.getInstance().waitUntilNextElementAppears(By.cssSelector(createAccountButtonElement),40);
        driver.findElement(By.cssSelector(createAccountButtonElement)).click();
        return new RegisterSuccessPage();

    }
}
