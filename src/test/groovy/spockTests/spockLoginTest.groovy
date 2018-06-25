package spockTests

import helpers.TestProperties
import org.openqa.selenium.By
import org.openqa.selenium.Capabilities
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.TimeUnit

import static helpers.TestProperties.getBaseURI
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import cucumber.api.CucumberOptions.*
import pageObjects.loginPage.login

import java.nio.file.Files

import static stepDefinitions.cucumberRunner.driver
import static stepDefinitions.cucumberRunner.driver
import static stepDefinitions.cucumberRunner.driver
import static stepDefinitions.cucumberRunner.driver
import static helpers.TestProperties.*

class spockLoginTest extends Specification {
    @Shared
    public static ChromeDriver driver
    def setupSpec() {

        Capabilities chromeCapabilities = DesiredCapabilities.chrome()
        driver = new ChromeDriver(chromeCapabilities)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS)
        def uri = getBaseURI()
        driver.get(uri)

    }
    def "#TestLogin"() {
        given: "I am on the mercury home page"
            assertThat("homepage",driver.getCurrentUrl(),is("http://newtours.demoaut.com/"))


        when: "I enter my user name and password"
            driver.findElement(By.name("userName")).sendKeys("austin")
            driver.findElement(By.name("password")).sendKeys("tester")
            driver.findElement(By.name("password")).sendKeys(Keys.ENTER)


        then: "I am not logged in"
        assertThat("homepage",driver.getCurrentUrl(),containsString("http://newtours.demoaut.com/"))

    }
}
