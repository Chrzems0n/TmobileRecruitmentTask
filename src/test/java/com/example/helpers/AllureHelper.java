package com.example.helpers;

import io.qameta.allure.Allure;

public final class AllureHelper {

    private AllureHelper() {
    }

    public static void addTextAttachment(String name, String message) {
        Allure.addAttachment(name, "text/plain", message);
    }
}
