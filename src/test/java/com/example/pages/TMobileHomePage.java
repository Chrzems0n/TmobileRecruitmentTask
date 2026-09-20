package com.example.pages;

import org.openqa.selenium.By;

public class TMobileHomePage extends BasePage {

    private static final String HOME_URL = "https://www.t-mobile.pl";
    private static final String TOP_BAR_ITEM_TEMPLATE = "//button[contains(@class,'ods-typography') and normalize-space()='%s']";
    private static final By LOGO_T_MOBILE = By.xpath("//*[@id='carousel-heading-Najlepsze oferty na abonament, internet światłowodowy, TV i smartfony']");
    private static final By SHOP_MENU = By.xpath("//button[normalize-space()='Sklep']");
    private static final By SMARTPHONES_SECTION = By.xpath("//div[contains(normalize-space(.), 'Smartfony')]//*[self::a or self::button or self::span][normalize-space()='Smartfony']");
    private static final By WITHOUT_CONTRACT_LINK = By.xpath("//a[normalize-space()='Bez abonamentu' or contains(normalize-space(.), 'Bez abonamentu')]");
    private static final By IKONA_KOSZYKA = By.xpath("//a[@aria-label='Koszyk']");

    public void open() {
        open(HOME_URL);
        waitForElementIsVisible(LOGO_T_MOBILE);
    }

    public void acceptCookies() {
        acceptCookiesIfVisible();
    }

    public void openShopMenu() {
        click(SHOP_MENU);
    }


    private By topBarItemLocator(String itemName) {
        return By.xpath(String.format(TOP_BAR_ITEM_TEMPLATE, escapeXPath(itemName)));
    }

    public void clickByPattern(String itemName) {
        driver.findElement(topBarItemLocator(itemName)).click();
        waitForElementIsVisible(SMARTPHONES_SECTION);
    }

    public void openSmartphonesWithoutContract() {
        waitForElementIsClickable(SMARTPHONES_SECTION);
        waitForElementIsClickable(WITHOUT_CONTRACT_LINK).click();
    }


    public void clickOnBasketIcon() {
        clickByAction(IKONA_KOSZYKA);
        waitForUrlContains("/sklep/basket");
    }
}
