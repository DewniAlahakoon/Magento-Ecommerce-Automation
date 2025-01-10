package com.softwaretestingboard.magento;

import com.softwaretestingboard.magento.pages.*;
import com.softwaretestingboard.magento.utils.ExtentManager;
import com.softwaretestingboard.magento.utils.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

@Listeners(com.softwaretestingboard.magento.listeners.Listeners.class)

public class T3_ShoppingFlowTest {

    HomePage homePage;
    LoginPage loginPage;
    LoginSuccessPage loginSuccessPage;
    ProductStorePage productStorePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckOutShippingPage checkOutShippingPage;
    CheckOutPaymentPage checkOutPaymentPage;
    CheckOutSuccessPage checkOutSuccessPage;


    @BeforeClass
    public void setUp() throws InterruptedException {
        TestBase.getInstance().openBrowser();
        ExtentManager.pass("Browser opened successfully.");
        TestBase.getInstance().navigateToURL();
        ExtentManager.pass("Navigated to application URL successfully.");
        homePage = new HomePage();
    }

    @Test(description = "Verify that a user can select a product and add it to the cart")
    public void testAddProductsToCart() throws InterruptedException, IOException {

        ExtentManager.log("Starting test: add product to the cart...");

        loginPage = homePage.clickOnLoginLink();

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

        Row row1 = sheet.getRow(3);
        Cell cellR1C0 = row1.getCell(0);
        Cell cellR1C1 = row1.getCell(1);

        String emailRow1 = cellR1C0.toString();
        String passwordRow1 = cellR1C1.toString();

        //passing data to the email and password fields and login to the application
        loginSuccessPage = loginPage.setEmail(emailRow1)
                .setPassword(passwordRow1)
                .clickOnLogin();

        ExtentManager.pass("User logged in successfully.");

        productStorePage = homePage.
                hoverOverWomenDropDown()
                .hoverOverTopsDropDown()
                .clickOnJackets();
        ExtentManager.pass("Navigated to product store page.");

        productPage = productStorePage.clickOneProduct();
        ExtentManager.pass("Navigated to product  page.");
        cartPage = productPage.setSize()
                .setColor()
                .setQuantity()
                .clickOnAddToCart()
                .clickOnCartLink();
        ExtentManager.pass("Product added to cart and navigated to cart page.");

        String expectedUrl = "https://magento.softwaretestingboard.com/checkout/cart/";
        Assert.assertEquals(cartPage.getActualUrl(), expectedUrl,
                "The user was not redirected to the expected 'Shopping Cart' page.");
        ExtentManager.pass("Verified cart page URL successfully.");
    }

    @Test(dependsOnMethods = {"testAddProductsToCart"},description = "Verify that a user can proceed to Checkout Page")
    public void testProceedToCheckout() throws InterruptedException {

        ExtentManager.log("Starting test: Proceed to checkout...");
        checkOutShippingPage = cartPage.clickOnCheckOut();
        Assert.assertEquals(checkOutShippingPage.isOnCheckoutPage(), "https://magento.softwaretestingboard.com/checkout/#shipping",
                "The user was not redirected to the expected 'Checkout Shipping' page");
        ExtentManager.pass("Navigated to the checkout shipping page successfully ");

    }

    @Test(dependsOnMethods = {"testProceedToCheckout"},description = "Verify that a user can fill out shipping details and validate added product details on the Checkout Shipping page")
    public void testCheckoutShipping() throws InterruptedException {

        ExtentManager.log("Starting test: Fill shipping details and validate products.");

        checkOutShippingPage
                .setStreet("Freddy Street")
                .setCity("Perth")
                .setPostCode("2155")
                .selectCountry()
                .setState()
                .setPhone("011784052")
                .selectFixedShippingMethod();
        ExtentManager.pass("Entered Shipping details successfully.");

        Assert.assertEquals(checkOutShippingPage.getItemCount(), "1",
                "Checkout page did not correctly display the number of added items");
        ExtentManager.pass("Verified item count on checkout page.");
        Assert.assertEquals(checkOutShippingPage.getItemName(), "Olivia 1/4 Zip Light Jacket",
                "Product Name Mismatched");
        ExtentManager.pass("Verified item name on checkout page.");
        Assert.assertEquals(checkOutShippingPage.getItemQuantity(), "1",
                "Product Quantity Mismatched");
        ExtentManager.pass("Verified item quantity on checkout page.");
        Assert.assertEquals(checkOutShippingPage.getItemPrice(), "$77.00",
                "Product Price Mismatched");
        ExtentManager.pass("Verified item price on checkout page.");

    }

    @Test(dependsOnMethods = {"testCheckoutShipping"},description = "Verify that a user can proceed to the Payment page and validate order details")
    public void testCheckOutPayment() throws InterruptedException{
        ExtentManager.log("Starting test: Proceed to payment and validate order details.");

        checkOutPaymentPage = checkOutShippingPage.clickOnNextButton();
        Assert.assertEquals(checkOutPaymentPage.isOnPaymentPage(), "https://magento.softwaretestingboard.com/checkout/#payment",
                "The user was not redirected to the expected 'Checkout Payment' page");
        ExtentManager.pass("Navigated to the checkout payment page successfully");

        Assert.assertEquals(checkOutPaymentPage.getSubTotal(),"$77.00","The 'Cart Subtotal' price does not match expected value");
        ExtentManager.pass("Verified subtotal price on payment page");
        Assert.assertEquals(checkOutPaymentPage.getShippingPrice(),"$5.00","The 'Shipping' price does not match expected value");
        ExtentManager.pass("Verified shipping price on payment page");
        Assert.assertEquals(checkOutPaymentPage.getTotalPrice(),"$82.00","The 'Order Total' price does not match expected value");
        ExtentManager.pass("Verified total price on payment page");
        Assert.assertEquals(checkOutPaymentPage.getItemCount(),"1",
                "Checkout page did not correctly display the number of added items");
        Assert.assertEquals(checkOutPaymentPage.getItemName(),"Olivia 1/4 Zip Light Jacket",
                "Product Name Mismatched");
        Assert.assertEquals(checkOutPaymentPage.getItemQuantity(),"1",
                "Product Quantity Mismatched");
        Assert.assertEquals(checkOutPaymentPage.getItemPrice(),"$77.00",
                "Product Price Mismatched");
        checkOutPaymentPage.clickViewDetails();
        Assert.assertEquals(checkOutPaymentPage.getSize(),"S",
                "Product size Mismatched");
        Assert.assertEquals(checkOutPaymentPage.getColor(),"Blue",
                "Product color Mismatched");
        Assert.assertEquals(checkOutPaymentPage.getShippingAddress(),"John Smith\n" +
                        "Freddy Street\n" +
                        "Perth, South Australia 2155\n" +
                        "Australia\n" +
                        "011784052",
                "Shipping Address does not match the expected value");
        Assert.assertTrue(checkOutPaymentPage.isAddressChecked(),"The address checkbox is not selected");
        Assert.assertEquals(checkOutPaymentPage.getShippingMethod(),"Flat Rate - Fixed",
                "Shipping Method Mismatched");

        checkOutSuccessPage = checkOutPaymentPage.clickOnPlaceOrder();
        Assert.assertEquals(checkOutSuccessPage.isOnCheckoutSuccessPage(), "https://magento.softwaretestingboard.com/checkout/onepage/success/",
                "The user was not redirected to the 'Checkout Success' page");
        ExtentManager.pass("Navigated  to the checkout success page successfully");

    }
    @Test(dependsOnMethods = {"testCheckOutPayment"},description = "Verify that a user can successfully place an order")
    public void testPlaceOrder() throws InterruptedException {
        ExtentManager.log("Starting test: Validate order placement.");

        Assert.assertEquals(checkOutSuccessPage.getSuccessMessage(), "Thank you for your purchase!",
                "The successful purchase message does NOT match the expected message");

        Assert.assertEquals(checkOutSuccessPage.confirmPrintLink(), "Print receipt",
                "Print receipt' link is NOT present on the Order Success Page.");

        Assert.assertEquals(checkOutSuccessPage.confirmContinueButton(), "Continue Shopping",
                "Continue Shopping' button is NOT present on Order Success Page");
        ExtentManager.pass("Order placed successfully");

    }
    @AfterMethod
    public void tearDown() {

        TestBase.getInstance().closeBrowser();
        ExtentManager.pass("Browser closed successfully.");
    }


}