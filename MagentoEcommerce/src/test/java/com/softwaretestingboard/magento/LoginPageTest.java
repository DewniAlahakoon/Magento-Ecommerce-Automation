package com.softwaretestingboard.magento;

import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.LoginPage;
import com.softwaretestingboard.magento.pages.LoginSuccessPage;
import com.softwaretestingboard.magento.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest {

    HomePage homePage;
    LoginPage loginPage;
    LoginSuccessPage loginSuccessPage = null;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        TestBase.getInstance().openBrowser();
        TestBase.getInstance().navigateToURL();
        homePage = new HomePage();
        loginPage = homePage.clickOnLoginLink();

    }

    @Test(description = "Verify that a user can login to the application using the correct credentials")
    public void testValidLogin() throws InterruptedException {

        loginSuccessPage =  loginPage
                .setEmail("demokevin1@gmail.com")
                .setPassword("Demo@1234")
                .clickOnLogin();

        String expectedURL = "https://magento.softwaretestingboard.com/";
        Assert.assertEquals(loginSuccessPage.getActualURL(),expectedURL ,"Login Failed with valid credentials");

    }

    @Test(description = "Verify that a user can't login to the application using the correct credentials")
    public void testInvalidLogin() throws InterruptedException {

        loginSuccessPage =  loginPage
                .setEmail("demokevin1@gmail.com")
                .setPassword("Demo")
                .clickOnLogin();

        String expectedURL = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
        Assert.assertEquals(loginSuccessPage.getActualURL(),expectedURL ,"Login succeeded with invalid credentials");

    }

    @AfterMethod
    public void tearDown() {

        TestBase.getInstance().closeBrowser();
    }
}
