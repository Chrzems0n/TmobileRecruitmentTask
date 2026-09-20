package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private static final By CART_TITLE = By.xpath("//*[contains(normalize-space(.), 'Twój koszyk') or contains(normalize-space(.), 'Koszyk')][1]");

    public boolean isCartVisible() {
        return isDisplayed(CART_TITLE);
    }

    public boolean containsProduct(String productName) {
        List<WebElement> matches = driver.findElements(By.xpath("//*[contains(normalize-space(.), '" + escapeXPath(productName) + "')]"));
        return !matches.isEmpty();
    }

    public String getCartSummaryText() {
        return getText(CART_TITLE);
    }
}
