package com.example.model;

import java.math.BigDecimal;

public record CurrencyRate(String currency, String code, BigDecimal mid) {
}
