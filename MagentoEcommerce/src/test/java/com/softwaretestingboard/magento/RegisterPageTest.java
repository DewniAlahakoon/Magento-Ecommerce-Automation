package com.softwaretestingboard.magento;

import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.RegisterPage;
import com.softwaretestingboard.magento.pages.RegisterSuccessPage;
import com.softwaretestingboard.magento.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPageTest {


    HomePage homePage;
    RegisterPage registerPage;
    RegisterSuccessPage registerSuccessPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {

        TestBase.getInstance().openBrowser();
        TestBase.getInstance().navigateToURL();
        homePage = new HomePage();
        registerPage = homePage.clickOnCreateAccount();
    }

    @Test(description = "Verify that a new user can successfully register an account")
    public void testRegisterNewUser() throws InterruptedException {

        registerSuccessPage = registerPage
                .setFirstName("Kevin")
                .setLastName("James")
                .setEmail("demokevin1@gmail.com")
                .setPassword("Demo@1234")
                .setConfirmPassword("Demo@1234")
                .clickOnCreateAnAccount();


        Assert.assertEquals(registerSuccessPage.getSuccessMessage(),"Thank you for registering with Main Website Store.","Registration was not successful");
  }

    @AfterMethod
    public void tearDown() {

        TestBase.getInstance().closeBrowser();
    }
}
