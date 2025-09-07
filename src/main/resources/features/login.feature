Feature: Login

  Scenario: Valid login
    Given user is on login page
    When user enters valid credentials
    Then user should be logged in

  Scenario: Invalid login
    Given user is on login page
    When user enters invalid credentials
    Then error message should be displayed

  Scenario Outline: Login with multiple users
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    Then user should see login result "<result>"

    Examples:
      | username  | password | result      |
      | user1     | pass1    | success     |
      | user2     | wrong    | failure     |
      | user3     | pass3    | success     |
      | user4     | wrong    | failure     |
