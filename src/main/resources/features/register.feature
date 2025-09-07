Feature: Registration

  Scenario: Register with valid data
    Given user is on registration page
    When user enters valid registration details
    Then registration should be successful

  Scenario: Register with existing email
    Given user is on registration page
    When user enters already registered email
    Then error message should be displayed

  Scenario Outline: Register multiple users
    Given user is on registration page
    When user registers with "<email>" and "<password>"
    Then result should be "<status>"

    Examples:
      | email         | password | status  |
      | a@a.com       | pass123  | success |
      | b@b.com       | pass123  | failure |
      | c@c.com       | pass123  | success |
      | d@d.com       | pass123  | failure |
