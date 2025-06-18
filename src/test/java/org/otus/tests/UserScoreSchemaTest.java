package org.otus.tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.otus.stub.StubServer;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

class UserScoreSchemaTest {

  private static StubServer server;

  @BeforeAll
  static void beforeAll() {
    server = new StubServer();
    server.startServer();
    baseURI = "http://localhost:8089";
  }

  @AfterAll
  static void afterAll() {
    server.stopServer();
  }

  @Test
  void validateUserScoreSchema() {
    given()
        .when().get("/user/get/12345")
        .then().statusCode(200)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
            "schemas/user-score-schema.json"));
  }
}
