package org.otus.tests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.otus.stub.StubServer;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WireMockTest {
  private static StubServer stubServer;

  @BeforeAll
  static void beforeAll() {
    stubServer = new StubServer();
    stubServer.startServer();
    RestAssured.baseURI = "http://localhost:8089";
  }

  @AfterAll
  static void afterAll() {
    stubServer.stopServer();
  }

  @Test
  @DisplayName("Тест: получение списка всех пользователей")
  void testGetAllUsers() {
    Response response = given()
        .when()
        .get("/user/get/all")
        .then()
        .statusCode(200)
        .extract().response();

    System.out.println("Response: " + response.asPrettyString());

    // Проверяем, что вернулся массив JSON
    response
        .then()
        .body("[0].name", equalTo("Test user"))
        .body("[0].cource", equalTo("QA"))
        .body("[0].email", equalTo("test@test.test"))
        .body("[0].age", equalTo(23));

  }

  @Test
  @DisplayName("Тест: получение списка курсов")
  void testGetAllCourses() {
    Response response = given()
        .when()
        .get("/cource/get/all")
        .then()
        .statusCode(200)
        .extract().response();

    System.out.println("Response: " + response.asPrettyString());

    response
        .then()
        .body("[0].name", equalTo("QA java"))
        .body("[0].price", equalTo(15000))
        .body("[1].name", equalTo("Java"))
        .body("[1].price", equalTo(12000));
  }

  @Test
  @DisplayName("Тест: получение оценки пользователя (по ID)")
  void testGetUserScoreById() {
    Response response = given()
        .when()
        .get("/user/get/12345")
        .then()
        .statusCode(200)
        .extract().response();

    System.out.println("Response: " + response.asPrettyString());

    response
        .then()
        .body("name", equalTo("Test user"))
        .body("score", equalTo(78));
  }
}
