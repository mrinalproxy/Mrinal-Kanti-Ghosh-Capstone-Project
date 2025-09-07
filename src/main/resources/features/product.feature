Feature: Product Search and Navigation

  Scenario: Search for a product
    Given user is on the homepage
    When user searches for a product "Laptop"
    Then search results should display at least one product

  Scenario: Navigate to Computers → Desktops
    Given user is on the homepage
    When user navigates to Computers category and then Desktops
    Then Desktops category should display products

  Scenario: Add first product to cart
    Given user is on the homepage
    When user searches for a product "Laptop"
    And user adds the first product to the cart
    Then the cart should have at least one item
