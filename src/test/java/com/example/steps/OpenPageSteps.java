package com.example.steps;

import com.example.pages.CartPage;
import com.example.pages.ProductPage;
import com.example.pages.SmartphonesPage;
import com.example.pages.TMobileHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenPageSteps {

    private final TMobileHomePage homePage = new TMobileHomePage();
    private final SmartphonesPage smartphonesPage = new SmartphonesPage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();

    private int PRICE_FROM_PRODUCT_PAGE_ON_START;
    private int PRICE_FROM_PRODUCT_PAGE_MONTHLY;
    private int PRODUCT_PRICE;
    private String DEVICE_NAME;


    @Given("Otwórz stronę główną T-Mobile")
    public void theUserOpensTheTMobileHomepage() {
        homePage.open();
        homePage.acceptCookies();
    }

    @And("Kliknij \"Bez abonamentu\" z sekcji \"Smartfony\"")
    public void choosesBezAbonamentuOptionInSmartfonySection() {
        homePage.openSmartphonesWithoutContract();
    }

    @And("Kliknij element o nazwie {string}")
    public void selectsDevice(String deviceName) {

        smartphonesPage.clickDeviceFromList(deviceName);
    }

    @And("Z górnej belki wybierz {string}")
    public void addItemsss(String itemName) {
        homePage.clickByPattern(itemName);
    }

    @When("Dodaj produkt do koszyka")
    public void theUserAddsTheProductToTheCart() {

        productPage.waitForProductPageToBeVisible();
        PRICE_FROM_PRODUCT_PAGE_ON_START = productPage.getProductOnStartPriceValue();
        PRICE_FROM_PRODUCT_PAGE_MONTHLY = productPage.getProductMonthlyPriceValue();
        DEVICE_NAME = productPage.getProductName();
        PRODUCT_PRICE = productPage.getDeviceTotalPriceValue();
        productPage.addToCart();
    }


    @And("wyswietl ceny")
    public void wyswietlCeny() {
        System.out.println("Product name: " + cartPage.getProductName());
        System.out.println("Product price on start: " + cartPage.getProductOnStartPrice());
        System.out.println("Product monthly price: " + cartPage.getProductMonthlyPrice());
        System.out.println("Product total price: " + PRODUCT_PRICE);
    }

    @Then("Zweryfikuj ceny na stronie koszyka")
    public void verifyPricesOnCartPage() {
        assertEquals(PRICE_FROM_PRODUCT_PAGE_ON_START, cartPage.getProductOnStartPrice(), "Cena na start produktu  nie zgadza się");
        assertEquals(PRICE_FROM_PRODUCT_PAGE_MONTHLY, cartPage.getProductMonthlyPrice(), "Cena miesięczna produktu  nie zgadza się");
        assertEquals(PRODUCT_PRICE, cartPage.getProductTotalPrice(), "Cena za urządzenie nie zgadza się");
    }

    @And("Kliknij ikonę koszyka")
    public void clickOnBasketIcon() {
        homePage.clickOnBasketIcon();
        cartPage.waitForCartToBeVisible();
    }

    @Then("Zweryfikuj czy urządzenie jest widoczne w koszyku")
    public void verifyDeviceInCart() {
        assertTrue(cartPage.getProductName().contains(DEVICE_NAME), "Product should be in cart");
    }
}