Feature: Checkout Functionality

  Scenario: Proceed to checkout
    Given user has at least one product in cart
    When user proceeds to checkout
    Then checkout page should open
