package com.example.steps;

import com.example.pages.CartPage;
import com.example.pages.ProductPage;
import com.example.pages.SmartphonesPage;
import com.example.pages.TMobileHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenPageSteps {

    private final TMobileHomePage homePage = new TMobileHomePage();
    private final SmartphonesPage smartphonesPage = new SmartphonesPage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();

    private String selectedDeviceName;
    private int ProductPriceOnStart;
    private int ProductPriceMonthly;

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

  //  @Given("wybierz gornego paska {string}")
   // @And("wybierz gornego paska {string}")
   // @When("wybierz gornego paska {string}")
   // public void wybierzGornegoPaska(String itemName) {
     //   homePage.clickTopBarItem(itemName);
    //}

    @And("Kliknij \"Bez abonamentu\" z sekcji \"Smartfony\"")
    public void choosesBezAbonamentuOptionInSmartfonySection() {
        homePage.openSmartphonesWithoutContract();
    }

    @And("Kliknij element o nazwie {string}")
    public void selectsDevice(String deviceName) {

        smartphonesPage.clickDeviceFromList(deviceName);
     //   this.selectedDeviceName = deviceName;
       // smartphonesPage.openDeviceByName(deviceName);
    }

    @Then("product page is visible")
    public void productPageIsVisible() {
        assertTrue(productPage.isProductPageVisible(), "Product page should be visible");
    }

    @And("Z górnej belki wybierz {string}")
        public void addItemsss(String itemName) throws InterruptedException {
        homePage.clickByPattern(itemName);
    }

    @When("Dodaj produkt do koszyka")
    public void theUserAddsTheProductToTheCart() {
        ProductPriceOnStart = productPage.getProductOnStartPriceValue();
        ProductPriceMonthly = productPage.getProductMonthlyPriceValue();

        System.out.println("Product price on start: " + ProductPriceOnStart);
        System.out.println("Product monthly price: " + ProductPriceMonthly);
        productPage.addToCart();
    }

    @Then("cart page is visible")
    public void cartPageIsVisible() {
        assertTrue(cartPage.isCartVisible(), "Cart page should be visible");
    }

//    @And("the product price matches the added product price")
//    public void theProductPriceMatchesTheAddedProductPrice() {
//        assertTrue(ProductPriceOnStart == cartPage.getAddedProductOnStartPrice(), "Product price on start should match");
//        assertTrue(ProductPriceMonthly == cartPage.getAddedProductMonthlyPrice(), "Product monthly price should match");
//    }

    @And("the user goes to home page")
    public void theUserGoesToHomePage() {
        homePage.open();
    }

    @And("opens cart")
    public void opensCart() {
        homePage.openCart();
    }

    @Then("the cart contains \"([^\"]+)\"")
    public void theCartContains(String productName) {
        assertTrue(cartPage.containsProduct(productName), "Cart should contain product: " + productName);
    }
}
