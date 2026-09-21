package com.example.pages;

import com.example.helpers.TextParser;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CartPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CartPage.class);

    private static final By CART_TITLE = By.xpath("//*[@id='basket-main-middle-section']//h1[contains(.,'Twój koszyk')]");
    private static final By CART_PRODUCT_NAME=By.xpath("//*[contains(@data-qa,'BKT_ItemTitle')]");
    private static final By CART_PRODUCT_ON_START_PRICE=By.xpath("//*[@data-qa='BKT_ItemUpFrontCurrCOde']");
    private static final By CART_PRODUCT_MONTHLY_PRICE=By.xpath("//*[@data-qa='BKT_ItemMonthlyCurrCOde']");
    private static final By CART_PRODUCT_TOTAL_PRICE=By.xpath("//*[@data-qa='BKT_Activation']");


    public void waitForCartToBeVisible() {
        waitForElementIsVisible(CART_TITLE);
    }

    public String getProductName() {
        String productName = getText(CART_PRODUCT_NAME);
        log.info("Product name in cart: {}", productName);
        return productName.trim();
    }

    public int getProductOnStartPrice() {;
        String price = getText(CART_PRODUCT_ON_START_PRICE);
        log.info("Product price on start in cart: {}", price);
        return TextParser.parseInt(price.trim());
    }

    public int getProductMonthlyPrice() {
        String price = getText(CART_PRODUCT_MONTHLY_PRICE);
        log.info("Product monthly price in cart: {}", price);
        return TextParser.parseInt(price.trim());
    }

    public int getProductTotalPrice() {
        String price = getText(CART_PRODUCT_TOTAL_PRICE);
        log.info("Product total price in cart: {}", price);
        return TextParser.parseInt(price.trim());
    }

}
