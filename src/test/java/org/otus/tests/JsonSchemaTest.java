package org.otus.tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.otus.stub.StubServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class JsonSchemaTest {

    private static StubServer stubServer;

    @BeforeAll
    static void setUp() {
        stubServer = new StubServer();
        stubServer.startServer();
        baseURI = "http://localhost:8089";
    }

    @AfterAll
    static void tearDown() {
        stubServer.stopServer();
    }

    @Test
    void validateUserListSchema() {
        given()
            .when()
            .get("/user/get/all")
            .then()
            .statusCode(200)
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-array-schema.json"));
    }



}
