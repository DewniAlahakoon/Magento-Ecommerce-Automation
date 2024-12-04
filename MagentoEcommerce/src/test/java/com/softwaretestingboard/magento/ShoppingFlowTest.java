package com.softwaretestingboard.magento;

import com.softwaretestingboard.magento.pages.*;
import com.softwaretestingboard.magento.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingFlowTest {

    HomePage homePage;
    ProductStorePage productStorePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckOutShippingPage checkOutShippingPage;
    CheckOutPaymentPage checkOutPaymentPage;
    CheckOutSuccessPage checkOutSuccessPage;


    @BeforeClass
    public void setUp() throws InterruptedException {
        TestBase.getInstance().openBrowser();
        TestBase.getInstance().navigateToURL();
        homePage = new HomePage();

    }

    @Test(description = "Verify that a user can select a product and add it to the cart")
    public void testAddProductsToCart() throws InterruptedException {

        homePage.clickOnLoginLink().setEmail("demokevin1@gmail.com").setPassword("Demo@1234").clickOnLogin();

        productStorePage = homePage.
                hoverOverWomenDropDown()
                .hoverOverTopsDropDown()
                .clickOnJackets();

        productPage = productStorePage.clickOneProduct();

        cartPage = productPage.setSize()
                .setColor()
                .setQuantity()
                .clickOnAddToCart()
                .clickOnCartLink();

        String expectedUrl = "https://magento.softwaretestingboard.com/checkout/cart/";
        Assert.assertEquals(cartPage.getActualUrl(), expectedUrl,
                "The user was not redirected to the expected 'Shopping Cart' page.");

    }

    @Test(dependsOnMethods = {"testAddProductsToCart"},description = "Verify that a user can proceed to Checkout Page")
    public void testProceedToCheckout() throws InterruptedException {

        checkOutShippingPage = cartPage.clickOnCheckOut();
        Assert.assertEquals(checkOutShippingPage.isOnCheckoutPage(), "https://magento.softwaretestingboard.com/checkout/#shipping",
                "The user was not redirected to the expected 'Checkout Shipping' page");

    }

    @Test(dependsOnMethods = {"testProceedToCheckout"},description = "Verify that a user can fill out shipping details and validate added product details on the Checkout Shipping page")
    public void testCheckoutShipping() throws InterruptedException {

        checkOutShippingPage
                .setStreet("Flower Street")
                .setCity("Perth")
                .setPostCode("245")
                .selectCountry()
                .setState()
                .setPhone("011234053")
                .selectFixedShippingMethod();

        Assert.assertEquals(checkOutShippingPage.getItemCount(), "1",
                "Checkout page did not correctly display the number of added items");
        Assert.assertEquals(checkOutShippingPage.getItemName(), "Olivia 1/4 Zip Light Jacket",
                "Product Name Mismatched");
        Assert.assertEquals(checkOutShippingPage.getItemQuantity(), "1",
                "Product Quantity Mismatched");
        Assert.assertEquals(checkOutShippingPage.getItemPrice(), "$77.00",
                "Product Price Mismatched");

    }

    @Test(dependsOnMethods = {"testCheckoutShipping"},description = "Verify that a user can proceed to the Payment page and validate order details")
    public void testCheckOutPayment() throws InterruptedException{

        checkOutPaymentPage = checkOutShippingPage.clickOnNextButton();
        Assert.assertEquals(checkOutPaymentPage.isOnPaymentPage(), "https://magento.softwaretestingboard.com/checkout/#payment",
                "The user was not redirected to the expected 'Checkout Payment' page");


        Assert.assertEquals(checkOutPaymentPage.getSubTotal(),"$77.00","The 'Cart Subtotal' price does not match expected value");
        Assert.assertEquals(checkOutPaymentPage.getShippingPrice(),"$5.00","The 'Shipping' price does not match expected value");
        Assert.assertEquals(checkOutPaymentPage.getTotalPrice(),"$82.00","The 'Order Total' price does not match expected value");
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
        Assert.assertEquals(checkOutPaymentPage.getShippingAddress(),"Kevin James\n" +
                        "Flower Street\n" +
                        "Perth, South Australia 245\n" +
                        "Australia\n" +
                        "011234053",
                "Shipping Address does not match the expected value");
        Assert.assertTrue(checkOutPaymentPage.isAddressChecked(),"The address checkbox is not selected");
        Assert.assertEquals(checkOutPaymentPage.getShippingMethod(),"Flat Rate - Fixed",
                "Shipping Method Mismatched");

       checkOutSuccessPage = checkOutPaymentPage.clickOnPlaceOrder();
        Assert.assertEquals(checkOutSuccessPage.isOnCheckoutSuccessPage(), "https://magento.softwaretestingboard.com/checkout/onepage/success/",
                "The user was not redirected to the 'Checkout Success' page");

    }
    @Test(dependsOnMethods = {"testCheckOutPayment"},description = "Verify that a user can successfully place an order")
    public void testPlaceOrder() throws InterruptedException {

        Assert.assertEquals(checkOutSuccessPage.getSuccessMessage(), "Thank you for your purchase!",
                "The successful purchase message does NOT match the expected message");

        Assert.assertEquals(checkOutSuccessPage.confirmPrintLink(), "Print receipt",
                "Print receipt' link is NOT present on the Order Success Page.");

        Assert.assertEquals(checkOutSuccessPage.confirmContinueButton(), "Continue Shopping",
                "Continue Shopping' button is NOT present on Order Success Page");

    }

}