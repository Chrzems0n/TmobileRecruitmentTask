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

    private int priceFromProductPageOnStart;
    private int priceFromProductPageMonthly;
    private int productPrice;
    private String deviceName;


    @Given("Otwórz stronę główną T-Mobile")
    public void theUserOpensTheTMobileHomepage() {
        homePage.open();
        homePage.acceptCookies();
    }

    @And("accepts cookies")
    public void acceptsCookies() {
        homePage.acceptCookies();
    }

    @And("AddSomething {string}")
    public void addSomething(String something) {
        System.out.println("Adding something: " + something);

    }

    @And("selects shop menu")
    public void selectsShopMenu() {
        homePage.openShopMenu();
    }

    @And("Kliknij \"Bez abonamentu\" z sekcji \"Smartfony\"")
    public void choosesBezAbonamentuOptionInSmartfonySection() {
        homePage.openSmartphonesWithoutContract();
    }

    @And("Kliknij element o nazwie {string}")
    public void selectsDevice(String deviceName) {

        smartphonesPage.clickDeviceFromList(deviceName);
    }

    @Then("product page is visible")
    public void productPageIsVisible() {
        assertTrue(productPage.isProductPageVisible(), "Product page should be visible");
    }

    @And("Z górnej belki wybierz {string}")
    public void addItemsss(String itemName) {
        homePage.clickByPattern(itemName);
    }

    @When("Dodaj produkt do koszyka")
    public void theUserAddsTheProductToTheCart() {
        priceFromProductPageOnStart = productPage.getProductOnStartPriceValue();
        priceFromProductPageMonthly = productPage.getProductMonthlyPriceValue();
        deviceName = productPage.getProductName();
        productPrice = productPage.getDeviceTotalPriceValue();

        productPage.addToCart();
    }


    @And("wyswietl ceny")
    public void wyswietlCeny() {
        System.out.println("Product name: " + cartPage.getProductName());
        System.out.println("Product price on start: " + cartPage.getProductOnStartPrice());
        System.out.println("Product monthly price: " + cartPage.getProductMonthlyPrice());
        System.out.println("Product total price: " + productPrice);
    }

    @Then("Zweryfikuj ceny na stronie koszyka")
    public void verifyPricesOnCartPage() {
        assertEquals(priceFromProductPageOnStart, cartPage.getProductOnStartPrice(), "Cena na start produktu  nie zgadza się");
        assertEquals(priceFromProductPageMonthly, cartPage.getProductMonthlyPrice(), "Cena miesięczna produktu  nie zgadza się");
        assertEquals(productPrice, cartPage.getProductTotalPrice(), "Cena za urządzenie nie zgadza się");
    }

    @And("Kliknij ikonę koszyka")
    public void clickOnBasketIcon() {
        homePage.clickOnBasketIcon();
        cartPage.waitForCartToBeVisible();
    }

    @Then("Zweryfikuj czy urządzenie jest widoczne w koszyku")
    public void verifyDeviceInCart() {
        assertTrue(cartPage.getProductName().contains(deviceName), "Product should be in cart");
    }
}