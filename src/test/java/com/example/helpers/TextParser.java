package com.example.helpers;

public class TextParser {
    public static int parseInt(String text) {
        String cleaned = text.replaceAll("[^0-9-]", "");

        if (cleaned.isEmpty() || cleaned.equals("-")) {
            throw new IllegalArgumentException("Brak wartości liczbowej w: " + text);
        }

        return Integer.parseInt(cleaned);
    }

    public static double parseDouble(String text) {
        String cleaned = text
                .replace("\u00A0", "") // non-breaking space
                .replace(" ", "")
                .replace(",", ".")
                .replaceAll("[^0-9.\\-]", "");

        if (cleaned.isEmpty() || cleaned.equals("-")) {
            throw new IllegalArgumentException("Brak wartości liczbowej w: " + text);
        }

        return Double.parseDouble(cleaned);
    }

}
