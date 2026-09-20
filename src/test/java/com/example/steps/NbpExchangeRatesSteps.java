package com.example.steps;

import com.example.client.NbpApiClient;
import com.example.model.CurrencyRate;
import com.example.service.CurrencyRateService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.util.Locale;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NbpExchangeRatesSteps {

    private final NbpApiClient apiClient = new NbpApiClient();
    private final CurrencyRateService currencyRateService = new CurrencyRateService();
    private List<CurrencyRate> rates;

    @When("Pobierz kursy walut")
    public void fetchExchangeRates() {
        Response response = apiClient.fetchTableA();
        int statusCode = response.statusCode();
        String responseBody = response.asPrettyString();

        System.out.println("Kod odpowiedzi API NBP: " + statusCode);
        Allure.addAttachment("Kod odpowiedzi API NBP", "text/plain", String.valueOf(statusCode));
        Allure.addAttachment("Odpowiedź API NBP", "application/json", responseBody);

        response.then().statusCode(200);

        rates = currencyRateService.parseRates(response);
        assertFalse(rates.isEmpty(), "API NBP powinno zwrócić co najmniej jeden kurs");
    }

    @Then("Wyświetl kurs dla waluty o kodzie: {string}")
    public void printRateForCode(String code) {
        CurrencyRate rate = currencyRateService.findByCode(rates, code);
        String message = String.format(
                "Kurs dla kodu %s (%s): %s", rate.code(), rate.currency(), rate.mid());
        System.out.println(message);
        addLogToAllure("Kurs dla kodu " + code, message);
    }

    @And("Wyświetl kurs dla waluty o nazwie: {string}")
    public void printRateForCurrencyName(String currencyName) {
        CurrencyRate rate = currencyRateService.findByCurrencyName(rates, currencyName);

        String message = String.format(
                "Kurs dla waluty %s (%s): %s", rate.currency(), rate.code(), rate.mid());
        System.out.println(message);
        addLogToAllure("Kurs dla waluty " + currencyName, message);
    }

    @And("Wyświetl waluty o kursie powyżej: {double}")
    public void printRatesAbove(double threshold) {
        List<CurrencyRate> matchingRates = currencyRateService.findRatesAbove(rates, threshold);

        assertFalse(matchingRates.isEmpty(), "Brak walut o kursie powyżej " + threshold);
        String message = formatRates(
                String.format("Waluty o kursie powyżej %s", threshold), matchingRates);
        System.out.println(message);
        addLogToAllure("Waluty powyżej " + threshold, message);
    }

    @And("Wyświetl waluty o kursie poniżej: {double}")
    public void printRatesBelow(double threshold) {
        List<CurrencyRate> matchingRates = currencyRateService.findRatesBelow(rates, threshold);

        assertFalse(matchingRates.isEmpty(), "Brak walut o kursie poniżej " + threshold);
        String message = formatRates(
                String.format("Waluty o kursie poniżej %s", threshold), matchingRates);
        System.out.println(message);
        addLogToAllure("Waluty poniżej " + threshold, message);
    }

    private String formatRates(String title, List<CurrencyRate> ratesToFormat) {
        StringBuilder result = new StringBuilder(title)
                .append(System.lineSeparator())
                .append(String.format("%-35s %-6s %12s%n", "Waluta", "Kod", "Kurs"))
                .append("-".repeat(58))
                .append(System.lineSeparator());

        ratesToFormat.forEach(rate -> result.append(String.format(
                Locale.ROOT,
                "%-35s %-6s %12s%n",
                rate.currency(),
                rate.code(),
                rate.mid())));

        return result.toString().stripTrailing();
    }

    private void addLogToAllure(String name, String message) {
        Allure.addAttachment(name, "text/plain", message);
    }
}
