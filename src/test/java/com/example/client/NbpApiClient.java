package com.example.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class NbpApiClient {

    private static final String NBP_API_URL = "http://api.nbp.pl";
    private static final String TABLE_A_ENDPOINT = "/api/exchangerates/tables/A";

    public Response fetchTableA() {
        return given()
                .baseUri(NBP_API_URL)
                .accept("application/json")
                .when()
                .get(TABLE_A_ENDPOINT);


    }
}
