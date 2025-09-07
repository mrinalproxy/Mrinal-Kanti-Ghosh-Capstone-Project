Feature: Cart Functionality

  Scenario: Verify product in cart
    Given user has at least one product in cart
    Then product should be added to cart
