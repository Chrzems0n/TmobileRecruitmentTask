package com.example.helpers;

import com.example.model.CurrencyRate;

import java.util.List;
import java.util.Locale;

public final class CurrencyRateFormatter {

    private CurrencyRateFormatter() {
    }

    public static String formatRates(String title, List<CurrencyRate> rates) {
        StringBuilder result = new StringBuilder(title)
                .append(System.lineSeparator())
                .append(String.format("%-35s %-6s %12s%n", "Waluta", "Kod", "Kurs"))
                .append("-".repeat(58))
                .append(System.lineSeparator());

        rates.forEach(rate -> result.append(String.format(
                Locale.ROOT,
                "%-35s %-6s %12s%n",
                rate.currency(),
                rate.code(),
                rate.mid())));

        return result.toString().stripTrailing();
    }
}
