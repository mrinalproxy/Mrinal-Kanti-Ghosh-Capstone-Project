package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import pages.RegisterPage;

import java.util.Map;
import static org.testng.Assert.*;

public class RegisterSteps {
    private RegisterPage registerPage = new RegisterPage();

    @Given("user opens the application")
    public void user_opens_the_application() {
        registerPage.goToHome();
    }

    @And("user navigates to registration page")
    public void user_navigates_to_registration_page() {
        registerPage.openRegister();
    }

    @When("user enters valid registration details")
    public void user_enters_valid_registration_details() {
        Map<String, String> data = Map.of(
                "gender", "male",
                "firstName", "John",
                "lastName", "Doe",
                "email", "john" + System.currentTimeMillis() + "@example.com",
                "password", "Password123"
        );
        registerPage.register(data);
    }

    @When("user registers with")
    public void user_registers_with(DataTable dataTable) {
        Map<String, String> input = dataTable.asMap(String.class, String.class);

        // Handle <unique> placeholder in email
        if (input.containsKey("email") && input.get("email").contains("<unique>")) {
            String uniqueEmail = input.get("email")
                                      .replace("<unique>", String.valueOf(System.currentTimeMillis()));
            input.put("email", uniqueEmail);
        }

        registerPage.register(input);
    }

    @When("user registers with mismatched passwords")
    public void user_registers_with_mismatched_passwords() {
        Map<String, String> data = Map.of(
                "gender", "male",
                "firstName", "Mismatch",
                "lastName", "User",
                "email", "mismatch" + System.currentTimeMillis() + "@example.com",
                "password", "pass1"
        );
        // confirm password different than password
        registerPage.registerWithConfirm(data, "differentPass");
    }

    @Then("registration should be successful")
    public void registration_should_be_successful() {
        assertTrue(registerPage.isRegistrationSuccessful(), "❌ Registration failed!");
    }

    @Then("registration should be {string}")
    public void registration_should_be(String status) {
        boolean success = registerPage.isRegistrationSuccessful();
        if ("success".equalsIgnoreCase(status)) {
            assertTrue(success, "❌ Expected success but got failure");
        } else {
            assertFalse(success, "❌ Expected failure but got success");
        }
    }
}
