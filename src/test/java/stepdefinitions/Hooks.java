package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.DriverManager;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setUp() {
        String browser = ConfigReader.get("browser", "chrome");
        DriverManager.initDriver(browser);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
