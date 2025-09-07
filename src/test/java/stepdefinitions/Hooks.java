package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().get("https://demo.nopcommerce.com");
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
