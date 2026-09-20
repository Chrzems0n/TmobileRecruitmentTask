package com.example.service;

import com.example.model.CurrencyRate;
import io.restassured.response.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CurrencyRateService {

    public List<CurrencyRate> parseRates(Response response) {
        List<Map<String, Object>> rawRates = response.jsonPath().getList("[0].rates");
        return rawRates.stream()
                .map(rate -> new CurrencyRate(
                        (String) rate.get("currency"),
                        (String) rate.get("code"),
                        new BigDecimal(rate.get("mid").toString())))
                .toList();
    }

    public CurrencyRate findByCode(List<CurrencyRate> rates, String code) {
        return rates.stream()
                .filter(rate -> rate.code().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Nie znaleziono waluty o kodzie: " + code));
    }

    public CurrencyRate findByCurrencyName(List<CurrencyRate> rates, String currencyName) {
        return rates.stream()
                .filter(rate -> rate.currency().equalsIgnoreCase(currencyName))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Nie znaleziono waluty o nazwie: " + currencyName));
    }

    public List<CurrencyRate> findRatesAbove(List<CurrencyRate> rates, double threshold) {
        return rates.stream()
                .filter(rate -> rate.mid().compareTo(BigDecimal.valueOf(threshold)) > 0)
                .toList();
    }

    public List<CurrencyRate> findRatesBelow(List<CurrencyRate> rates, double threshold) {
        return rates.stream()
                .filter(rate -> rate.mid().compareTo(BigDecimal.valueOf(threshold)) < 0)
                .toList();
    }
}
