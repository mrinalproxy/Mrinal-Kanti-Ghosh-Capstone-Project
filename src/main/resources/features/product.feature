Feature: Products

  Scenario Outline: Search products
    Given user is on homepage
    When user searches for "<product>"
    Then search results should display "<product>"

    Examples:
      | product      |
      | Laptop       |
      | Phone        |
      | Tablet       |
      | Headphones   |

  Scenario: Filter products by category
    Given user is on products page
    When user filters by "Electronics"
    Then only electronics products are shown
