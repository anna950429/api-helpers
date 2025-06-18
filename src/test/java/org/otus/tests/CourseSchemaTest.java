package org.otus.tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.otus.stub.StubServer;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseSchemaTest {

  private static StubServer server;

  @BeforeAll
  static void init() {
    server = new StubServer();
    server.startServer();
    baseURI = "http://localhost:8089";
  }

  @AfterAll
  static void shutdown() {
    server.stopServer();
  }

  @Test
  @Order(1)
  void validateCourseArraySchema() {
    given()
        .when().get("/cource/get/all")
        .then().statusCode(200)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
            "schemas/course-array-schema.json"));
  }
}
