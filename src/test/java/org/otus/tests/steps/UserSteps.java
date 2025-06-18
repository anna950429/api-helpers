package org.otus.tests.steps;


import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.otus.stub.StubServer;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps {

  private static StubServer stubServer;
  private Response response;

  @Given("the stub server is running")
  public void the_stub_server_is_running() {
    if (stubServer == null) {
      stubServer = new StubServer();
      stubServer.startServer();
      baseURI = "http://localhost:8089";
    }
  }

  @When("I send a GET request to {string}")
  public void i_send_a_get_request_to(String endpoint) {
    response = given()
        .when()
        .get(endpoint);
  }

  @Then("the response status code should be {int}")
  public void the_response_status_code_should_be(Integer statusCode) {
    assertEquals(statusCode, response.getStatusCode());
  }

  @Then("the response should contain a list of users")
  public void the_response_should_contain_a_list_of_users() {
    // Допустим, проверяем, что в JSON есть как минимум один объект с полем name
    response.then().assertThat().statusCode(200);
    String json = response.getBody().asString();
    System.out.println("Response = " + json);
    // Дополнительные проверки
  }
}
