package com.softwaretestingboard.magento;

import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.RegisterPage;
import com.softwaretestingboard.magento.pages.RegisterSuccessPage;
import com.softwaretestingboard.magento.utils.ExtentManager;
import com.softwaretestingboard.magento.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.softwaretestingboard.magento.listeners.Listeners.class)

public class T1_RegisterPageTest {


    HomePage homePage;
    RegisterPage registerPage;
    RegisterSuccessPage registerSuccessPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {

        TestBase.getInstance().openBrowser();
        ExtentManager.pass("Browser opened successfully.");
        TestBase.getInstance().navigateToURL();
        ExtentManager.pass("Navigated to application URL successfully.");
        homePage = new HomePage();
        registerPage = homePage.clickOnCreateAccount();
        ExtentManager.pass("Navigated to Sign Up Page successfully.");

    }

    @Test(description = "Verify that a new user can successfully register an account")
    public void testRegisterNewUser() throws InterruptedException {

        ExtentManager.log("Starting Register New User...");
        registerSuccessPage = registerPage
                .setFirstName("Keem")
                .setLastName("Paul")
                .setEmail("demokeem1@gmail.com")
                .setPassword("Kdemo@1234")
                .setConfirmPassword("Kdemo@1234")
                .clickOnCreateAnAccount();


        Assert.assertEquals(registerSuccessPage.getSuccessMessage(),"Thank you for registering with Main Website Store.","Registration was not successful");
        ExtentManager.pass("Validated successful registration and confirmed redirection to the correct URL.");

    }

    @AfterMethod
    public void tearDown() {

        TestBase.getInstance().closeBrowser();
        ExtentManager.pass("Browser closed successfully.");
    }


}

