Feature: this will validate the Single person in json file

  Scenario: to verify that single person info in json file
    Given json file with single user filename "src\\main\\resources\\singleUserResponse.json"
    And csv file with list of all twelve users filename "src\\main\\resources\\userData1.csv"
    When parse json file and get actual user
    And parse csv file and get expected user list
    Then the expected user should be equal to actual user