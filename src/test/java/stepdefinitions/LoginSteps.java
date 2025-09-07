package stepdefinitions;

import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("I open the browser")
    public void i_open_the_browser() {
        System.out.println("Browser opened");
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        System.out.println("Entered valid credentials");
    }

    @Then("I should see the dashboard")
    public void i_should_see_the_dashboard() {
        System.out.println("Dashboard displayed");
    }
}
