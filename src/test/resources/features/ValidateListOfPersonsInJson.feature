Feature: this will validate List Of persons in json file

  Scenario: to verify that person info in json file
    Given json file with filename "src\\main\\resources\\response.json"
    And csv file with filename "src\\main\\resources\\userData.csv"
    When parse json file and get actual user list
    And parse csv file and get expected user list
    Then the user lists should be equal