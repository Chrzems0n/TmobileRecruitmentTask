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

    private static final By DEVICE_TOTAL_PRICE= By.xpath("//article[contains(@class,'Price')]//span");

    private static final  By PRODUCT_PAGE_PRODUCT_NAME= By.xpath("//*[contains(@data-qa,'PRD_ProductName')]");

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
        return TextParser.parseInt(getProductOnStartPriceText().trim());
    }



    public String getProductMonthlyPriceText() {
        WebElement priceElement = waitForElementIsVisible(DO_ZAPLATY_MIESIECZNIE);
        return priceElement.getText().trim();
    }

    public int getProductMonthlyPriceValue() {
        return TextParser.parseInt(getProductMonthlyPriceText().trim());
    }

    public String getProductName() {
        waitForElementIsVisible( PRODUCT_PAGE_PRODUCT_NAME);
        String productName = getText( PRODUCT_PAGE_PRODUCT_NAME);
        return productName.trim();
    }

    public String getDeviceTotalPriceText() {
        WebElement priceElement = waitForElementIsVisible(DEVICE_TOTAL_PRICE);
        return priceElement.getText().trim();
    }

    public int getDeviceTotalPriceValue() {
        return TextParser.parseInt(getDeviceTotalPriceText().trim());
    }




}
