package com.example.pages;

import com.example.helpers.TextParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    private static final By PRODUCT_TITLE = By.xpath("//h1 | //h2");
    private static final By DO_ZAPLATY_MIESIECZNIE = By.xpath(
            "//aside//*[@id='dyt_priceValue' and not(@data-qa)]"
    );
    private static final By DO_ZAPLATY_NA_START = By.xpath(
            "//aside//*[@data-qa='PRD_TotalUpfront']"
    );
    private static final By DODAJ_DO_KOSZYKA = By.xpath(
            "//aside//button[@id='dyt_addDeviceToCart' " +
                    "and not(@disabled) " +
                    "and normalize-space(.)='Dodaj do koszyka']"
    );

    public boolean isProductPageVisible() {
        return isDisplayed(PRODUCT_TITLE);
    }

    public void addToCart() {
        click(DODAJ_DO_KOSZYKA);
    }

    public String getProductOnStartPriceText() {
        WebElement priceElement = waitForElementIsVisible(DO_ZAPLATY_NA_START);
        return priceElement.getText().trim();
    }

    public int getProductOnStartPriceValue() {
        WebElement priceElement = waitForElementIsVisible(DO_ZAPLATY_NA_START);
        return TextParser.parseInt(getProductOnStartPriceText().trim());
    }



    public String getProductMonthlyPriceText() {
        WebElement priceElement = waitForElementIsVisible(DO_ZAPLATY_MIESIECZNIE);
        return priceElement.getText().trim();
    }

    public int getProductMonthlyPriceValue() {
        WebElement priceElement = waitForElementIsVisible(DO_ZAPLATY_MIESIECZNIE);
        return TextParser.parseInt(getProductMonthlyPriceText().trim());
    }




}
