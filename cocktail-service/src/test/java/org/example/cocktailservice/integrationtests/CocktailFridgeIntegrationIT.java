package org.example.cocktailservice.integrationtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CocktailFridgeIntegrationIT {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void testGetCocktails() {
        Response response = given()
                .port(8081)  // Port für Cocktail Service
                .get("/rest/cocktails");
        
        response.then().statusCode(200)
                .body("size()", equalTo(69));
    }

}
