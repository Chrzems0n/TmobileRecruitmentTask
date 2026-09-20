package com.example.pages;

import com.example.helpers.TextParser;
import org.openqa.selenium.By;


public class CartPage extends BasePage {

    private static final By CART_TITLE = By.xpath("//*[@id='basket-main-middle-section']//h1[contains(.,'Twój koszyk')]");
    private static final By CART_PRODUCT_NAME=By.xpath("//*[contains(@data-qa,'BKT_ItemTitle')]");
    private static final By CART_PRODUCT_ON_START_PRICE=By.xpath("//*[@data-qa='BKT_ItemUpFrontCurrCOde']");
    private static final By CART_PRODUCT_MONTHLY_PRICE=By.xpath("//*[@data-qa='BKT_ItemMonthlyCurrCOde']");
    private static final By CART_PRODUCT_TOTAL_PRICE=By.xpath("//*[@data-qa='BKT_Activation']");

    public boolean isCartVisible() {
        return isDisplayed(CART_TITLE);
    }

    public void waitForCartToBeVisible() {
        waitForElementIsVisible(CART_TITLE);
    }

    public String getProductName() {
        waitForElementIsVisible(CART_PRODUCT_NAME);
        String productName = getText(CART_PRODUCT_NAME);

        System.out.println("Product name in cart: " + productName);
        return productName.trim();
    }

    public int getProductOnStartPrice() {
        waitForElementIsVisible(CART_PRODUCT_ON_START_PRICE);
        String price = getText(CART_PRODUCT_ON_START_PRICE);
        System.out.println("Product price on start in cart: " + price);
        return TextParser.parseInt(price.trim());
    }

    public int getProductMonthlyPrice() {
        waitForElementIsVisible(CART_PRODUCT_MONTHLY_PRICE);
        String price = getText(CART_PRODUCT_MONTHLY_PRICE);
        System.out.println("Product monthly price in cart: " + price);
        return TextParser.parseInt(price.trim());
    }

    public int getProductTotalPrice() {
        waitForElementIsVisible(CART_PRODUCT_TOTAL_PRICE);
        String price = getText(CART_PRODUCT_TOTAL_PRICE);
        System.out.println("Product total price in cart: " + price);
        return TextParser.parseInt(price.trim());
    }

}
