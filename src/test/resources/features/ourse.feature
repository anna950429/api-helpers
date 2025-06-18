Feature: User API
  In order to test the user API
  As a QA engineer
  I want to validate the user endpoints

  Scenario: Get all users
    Given the stub server is running
    When I send a GET request to "/user/get/all"
    Then the response status code should be 200
    And the response should contain a list of users
