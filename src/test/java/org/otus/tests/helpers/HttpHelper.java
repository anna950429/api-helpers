package org.otus.tests.helpers;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HttpHelper {

  // Базовый URL можно задавать через конструктор или статическую переменную
  private static final String BASE_URL = "http://localhost:8089";

  public static Response get(String path) {
    return RestAssured
        .given()
        .baseUri(BASE_URL)
        .when()
        .get(path)
        .then()
        .extract()
        .response();
  }

  public static Response post(String path, Object body) {
    return RestAssured
        .given()
        .baseUri(BASE_URL)
        .body(body)
        .when()
        .post(path)
        .then()
        .extract()
        .response();
  }

  // put, delete, etc. - по аналогии
}
