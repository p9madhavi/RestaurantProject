Feature: this will validate single person in json file

  Scenario: to verify that single person info in json file
    Given uri is "https://reqres.in"
    And path for single user is "/api/users/2"
    And csv file with list of all twelve users filename "src\\main\\resources\\userData1.csv"
    And parse csv file and get expected user list
    When get method is called for single user
    Then status is 200
    And users are parsed from the response to get actual user
    And the expected user should be equal to actual user