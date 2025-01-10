package com.softwaretestingboard.magento;

import com.softwaretestingboard.magento.pages.HomePage;
import com.softwaretestingboard.magento.pages.LoginPage;
import com.softwaretestingboard.magento.pages.LoginSuccessPage;
import com.softwaretestingboard.magento.utils.ExtentManager;
import com.softwaretestingboard.magento.utils.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

@Listeners(com.softwaretestingboard.magento.listeners.Listeners.class)

public class T2_LoginPageTest {

    HomePage homePage;
    LoginPage loginPage;
    LoginSuccessPage loginSuccessPage = null;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        TestBase.getInstance().openBrowser();
        ExtentManager.pass("Browser opened successfully.");
        TestBase.getInstance().navigateToURL();
        ExtentManager.pass("Navigated to application URL successfully.");
        homePage = new HomePage();
        loginPage = homePage.clickOnLoginLink();
        ExtentManager.pass("Navigated to Login Page successfully.");

    }

    @Test(description = "Verify that a user can login to the application using the correct credentials")
    public void testValidLogin() throws InterruptedException, IOException {

        ExtentManager.log("Starting ValidLoginTest...");

        //opening the Excel file from the specified path
        FileInputStream workbookLoc = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\credentials.xlsx");

        //using Apche POI class to access data in the workbook
        XSSFWorkbook workbookdata = new XSSFWorkbook(workbookLoc);

        //accessing the sheet within the workbook
        XSSFSheet sheet = workbookdata.getSheetAt(0);

        //accessing actual data within the sheet
        /****************************************************************************
         * Excel Spreadsheet Layout Reminder (teaching purposes only)
         *
         * |Row=0 -->| Email Address (Cell 0) Password (Cell 1) *
         * --------------------------------------------------------------------
         * |Row=1 -->| demolucas1@gmail.com (Cell 0) Demo@1234 (Cell 1)
         * |Row=2 -->| demolucas1@gmail.com (Cell 0) Demo@1235 (Cell 1)
         * |Row=3 -->| demosmith@gmail.com (Cell 0) Sdemo@1234 (Cell 1)
         * |Row=4 -->| demohenry@gmail.com (Cell 0) Hdemo@1234 (Cell 1)
         ****************************************************************************/

        Row row1 = sheet.getRow(1);
        Cell cellR1C0 = row1.getCell(0);
        Cell cellR1C1 = row1.getCell(1);

        String emailRow1 = cellR1C0.toString();
        String passwordRow1 = cellR1C1.toString();

        //passing data to the email and password fields and login to the application
        loginSuccessPage = loginPage.setEmail(emailRow1)
                .setPassword(passwordRow1)
                .clickOnLogin();

        ExtentManager.pass("User has entered valid credentials and clicked on login.");

        String expectedURL = "https://magento.softwaretestingboard.com/";
        Assert.assertEquals(loginSuccessPage.getActualURL(),expectedURL ,"Login Failed with valid credentials");
        ExtentManager.pass("Validated successful login and confirmed redirection to the correct URL.");

    }

    @Test(description = "Verify that a user can't login to the application using the correct credentials")
    public void testInvalidLogin() throws InterruptedException, IOException {

        ExtentManager.log("Starting InvalidLoginTest...");

        //accessing credentials workbook
        FileInputStream workbookLoc = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\credentials.xlsx");

        //using Apche POI class to access data in the workbook
        XSSFWorkbook workbookdata = new XSSFWorkbook(workbookLoc);

        //accessing the sheet within the workbook
        XSSFSheet sheet = workbookdata.getSheetAt(0);

        //accessing actual data within the sheet
        /****************************************************************************
         * Excel Spreadsheet Layout Reminder (teaching purposes only)
         *
         * |Row=0 -->| Email Address (Cell 0) Password (Cell 1) *
         * --------------------------------------------------------------------
         * |Row=1 -->| demolucas1@gmail.com (Cell 0) Demo@1234 (Cell 1)
         * |Row=2 -->| demolucas1@gmail.com (Cell 0) Demo@1235 (Cell 1)
         * |Row=3 -->| demosmith@gmail.com (Cell 0) Sdemo@1234 (Cell 1)
         * |Row=4 -->| demohenry@gmail.com (Cell 0) Hdemo@1234 (Cell 1)
         ****************************************************************************/

        Row row1 = sheet.getRow(2);
        Cell cellR1C0 = row1.getCell(0);
        Cell cellR1C1 = row1.getCell(1);

        String emailRow2 = cellR1C0.toString();
        String passwordRow2 = cellR1C1.toString();

        //passing data to the email and password fields and login to the application
        loginSuccessPage = loginPage.setEmail(emailRow2)
                .setPassword(passwordRow2)
                .clickOnLogin();
        ExtentManager.pass("User has entered invalid credentials and clicked on login.");

        String expectedURL = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
        Assert.assertEquals(loginSuccessPage.getActualURL(),expectedURL ,"Login succeeded with invalid credentials");
        ExtentManager.pass("Validated unsuccessful login and confirmed redirection to the login page.");

    }

    @AfterMethod
    public void tearDown() {

        TestBase.getInstance().closeBrowser();
        ExtentManager.pass("Browser closed successfully.");
    }
}
