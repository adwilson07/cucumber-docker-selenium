package stepDefinitions

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import helpers.TestProperties
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

@RunWith(Cucumber.class)
@CucumberOptions(
        format = ["pretty", "json:Reports/Cucumber/TestResults.json"],
        tags = ["~@focus"],
        features = "src/test/resources"
)

class cucumberRunner {
    public static ChromeDriver driver
    public static Actions actions
    public static TestProperties testProperties



    @BeforeClass
    static void beforeAllTests() {

    }

    @AfterClass
    static void afterAllTests() {

    }
}
