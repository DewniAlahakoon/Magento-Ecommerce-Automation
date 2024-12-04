package com.softwaretestingboard.magento.pages;

import com.softwaretestingboard.magento.utils.TestBase;
import org.openqa.selenium.WebDriver;

public class LoginSuccessPage {

    //initializing the web driver instance
    WebDriver driver = TestBase.getInstance().getDriver();

    public String getActualURL() throws InterruptedException {
        Thread.sleep(1000);
        return   driver.getCurrentUrl();
    }

}
