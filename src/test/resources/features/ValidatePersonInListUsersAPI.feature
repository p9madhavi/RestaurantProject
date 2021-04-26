Feature: this will validate persons in json file

  Scenario: to verify that person info in json file
    Given uri is "https://reqres.in"
    And path is "/api/users"
    And param "page" is 2
    And csv file with filename "src\\main\\resources\\userData.csv"
    And parse csv file and get expected user list
    When get method is called
    Then status is 200
    And users are parsed from the response to get actual user list
    And the user lists should be equal