Feature: Cart Management

  Scenario: Add single product to cart
    Given user is on products page
    When user adds product to cart
    Then cart count should increase

  Scenario: Remove product from cart
    Given user has product in cart
    When user removes product
    Then cart should be empty

  Scenario Outline: Add multiple products
    Given user is on products page
    When user adds "<product>" to cart
    Then cart should reflect "<count>"

    Examples:
      | product    | count |
      | Laptop     | 1     |
      | Phone      | 2     |
      | Tablet     | 3     |
      | Headphones | 4     |
