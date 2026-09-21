package com.example.steps;

import com.example.client.NbpApiClient;
import com.example.helpers.AllureHelper;
import com.example.helpers.CurrencyRateFormatter;
import com.example.model.CurrencyRate;

import com.example.service.CurrencyRateService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NbpExchangeRatesSteps {

    private static final Logger log = LoggerFactory.getLogger(NbpExchangeRatesSteps.class);

    private final NbpApiClient apiClient = new NbpApiClient();
    private final CurrencyRateService currencyRateService = new CurrencyRateService();
    private List<CurrencyRate> rates;

    @When("Pobierz kursy walut")
    public void fetchExchangeRates() {
        Response response = apiClient.fetchTableA();
        int statusCode = response.statusCode();
        String responseBody = response.asPrettyString();
        log.info("Kod odpowiedzi API NBP: {}", statusCode);



        AllureHelper.addTextAttachment("Kod odpowiedzi API NBP", String.valueOf(statusCode));
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

        log.info("Kurs dla kodu {} ({}): {}", rate.code(), rate.currency(), rate.mid());
        AllureHelper.addTextAttachment("Kurs dla kodu " + code, message);
    }

    @And("Wyświetl kurs dla waluty o nazwie: {string}")
    public void printRateForCurrencyName(String currencyName) {
        CurrencyRate rate = currencyRateService.findByCurrencyName(rates, currencyName);

        String message = String.format(
                "Kurs dla waluty %s (%s): %s", rate.currency(), rate.code(), rate.mid());
        log.info(message);
        AllureHelper.addTextAttachment("Kurs dla waluty " + currencyName, message);
    }

    @And("Wyświetl waluty o kursie powyżej: {double}")
    public void printRatesAbove(double threshold) {
        List<CurrencyRate> matchingRates = currencyRateService.findRatesAbove(rates, threshold);

        assertFalse(matchingRates.isEmpty(), "Brak walut o kursie powyżej " + threshold);
        String message = CurrencyRateFormatter.formatRates(
                String.format("Waluty o kursie powyżej %s", threshold), matchingRates);
        log.info(message);
        AllureHelper.addTextAttachment("Waluty powyżej " + threshold, message);
    }

    @And("Wyświetl waluty o kursie poniżej: {double}")
    public void printRatesBelow(double threshold) {
        List<CurrencyRate> matchingRates = currencyRateService.findRatesBelow(rates, threshold);

        assertFalse(matchingRates.isEmpty(), "Brak walut o kursie poniżej " + threshold);
        String message = CurrencyRateFormatter.formatRates(
                String.format("Waluty o kursie poniżej %s", threshold), matchingRates);
        log.info(message);
        AllureHelper.addTextAttachment("Waluty poniżej " + threshold, message);
    }
}
