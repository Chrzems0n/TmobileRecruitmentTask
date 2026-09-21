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
    private static final By PRODUCT_PAGE_RADY_TO_ACTION=By.xpath("//*[@id='dyt_productViewDesktop']");

    public void addToCart() {
        click(DODAJ_DO_KOSZYKA);
    }

    public String getProductOnStartPriceText() {
        return getWebElement(DO_ZAPLATY_NA_START).getText().trim();
    }

    public int getProductOnStartPriceValue() {
        return TextParser.parseInt(getProductOnStartPriceText().trim());
    }



    public String getProductMonthlyPriceText() {
        return getWebElement(DO_ZAPLATY_MIESIECZNIE).getText().trim();
    }

    public int getProductMonthlyPriceValue() {
        return TextParser.parseInt(getProductMonthlyPriceText().trim());
    }

    public String getProductName() {
        String productName = getWebElement(PRODUCT_PAGE_PRODUCT_NAME).getText().trim();
        return productName.trim();
    }

    public String getDeviceTotalPriceText() {
        return getWebElement(DEVICE_TOTAL_PRICE).getText().trim();
    }

    public int getDeviceTotalPriceValue() {
        return TextParser.parseInt(getDeviceTotalPriceText().trim());
    }

    public void waitForProductPageToBeVisible() {
        waitForElementIsVisible(PRODUCT_PAGE_RADY_TO_ACTION);
    }


}
