Feature: Product Browsing and Search

  Scenario: Search for a laptop
    Given user is on homepage
    When user searches for "Laptop"
    Then search results should be displayed

  Scenario: Browse desktops category
    Given user navigates to Desktops category
    Then search results should be displayed

  Scenario: Add a product from search to cart
    Given user is on homepage
    When user searches for "Laptop"
    And user adds first product to cart
    Then product should be added to cart
