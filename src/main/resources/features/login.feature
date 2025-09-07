Feature: Login Functionality

  Scenario: Successful login
    Given user is on login page
    When user enters valid email and password
    And clicks login button
    Then user should be logged in successfully

  Scenario: Unsuccessful login with invalid credentials
    Given user is on login page
    When user enters invalid email and valid password
    And clicks login button
    Then error message should be displayed
