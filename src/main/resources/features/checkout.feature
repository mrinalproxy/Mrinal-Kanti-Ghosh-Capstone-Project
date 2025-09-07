Feature: Checkout

  Scenario: Checkout with empty cart
    Given cart is empty
    When user tries to checkout
    Then error message should display

  Scenario Outline: Checkout multiple payment options
    Given cart has items
    When user pays with "<payment>"
    Then order status should be "<status>"

    Examples:
      | payment   | status   |
      | CreditCard| success  |
      | PayPal    | success  |
      | COD       | success  |
      | Invalid   | failure  |
