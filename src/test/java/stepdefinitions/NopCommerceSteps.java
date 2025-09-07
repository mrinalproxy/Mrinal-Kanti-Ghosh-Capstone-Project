package stepdefinitions;

import io.cucumber.java.en.*;

public class NopCommerceSteps {

    @Given("user is on login page")
    public void user_is_on_login_page() {}

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {}

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials() {}

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {}

    @Then("user should be logged in")
    public void user_should_be_logged_in() {}

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {}

    @Then("user should see login result {string}")
    public void user_should_see_login_result(String result) {}

    // --- Registration ---
    @Given("user is on registration page")
    public void user_is_on_registration_page() {}

    @When("user enters valid registration details")
    public void user_enters_valid_registration_details() {}

    @When("user enters already registered email")
    public void user_enters_already_registered_email() {}

    @When("user registers with {string} and {string}")
    public void user_registers_with_and(String email, String password) {}

    @Then("registration should be successful")
    public void registration_should_be_successful() {}

    @Then("result should be {string}")
    public void result_should_be(String status) {}

    // --- Cart ---
    @Given("user is on products page")
    public void user_is_on_products_page() {}

    @Given("user has product in cart")
    public void user_has_product_in_cart() {}

    @When("user adds product to cart")
    public void user_adds_product_to_cart() {}

    @When("user removes product")
    public void user_removes_product() {}

    @When("user adds {string} to cart")
    public void user_adds_to_cart(String product) {}

    @Then("cart count should increase")
    public void cart_count_should_increase() {}

    @Then("cart should be empty")
    public void cart_should_be_empty() {}

    @Then("cart should reflect {string}")
    public void cart_should_reflect(String count) {}

    // --- Checkout ---
    @Given("cart is empty")
    public void cart_is_empty() {}

    @Given("cart has items")
    public void cart_has_items() {}

    @When("user tries to checkout")
    public void user_tries_to_checkout() {}

    @When("user pays with {string}")
    public void user_pays_with(String payment) {}

    @Then("order status should be {string}")
    public void order_status_should_be(String status) {}

    // --- Products ---
    @When("user searches for {string}")
    public void user_searches_for(String product) {}

    @Then("search results should display {string}")
    public void search_results_should_display(String product) {}

    @When("user filters by {string}")
    public void user_filters_by(String category) {}

    @Then("only electronics products are shown")
    public void only_electronics_products_are_shown() {}
}

