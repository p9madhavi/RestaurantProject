Feature: this will validate create person in json file

  Scenario: to verify  the person data created by the createUser api
    Given uri is "https://reqres.in"
    And path for create user is "/api/users"
    And request file for createuser filename is "src\\main\\resources\\createRequest.json"
    When post method is called for create user
    Then statuscode for createUser is 201
    And users are parsed from the response to get a created user
    And the user in the response should matches the user in the request