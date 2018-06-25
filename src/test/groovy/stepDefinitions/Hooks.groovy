package stepDefinitions

import org.openqa.selenium.Capabilities
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait

import java.util.concurrent.TimeUnit

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before
import static stepDefinitions.cucumberRunner.actions
import static stepDefinitions.cucumberRunner.driver
import static helpers.TestProperties.*

/**
 * Delete all cookies at the start of each scenario to avoid
 * shared state between tests
 */
Before() { scenario ->
    def tags = scenario.getSourceTagNames()
    Capabilities chromeCapabilities = DesiredCapabilities.chrome()
    ChromeOptions chromeOptions = new ChromeOptions()

    if (tags.contains("@mobile")) {
        Map<String, Object> mobileEmulation = new HashMap<>()
        mobileEmulation.put("deviceName", "iPhone X")
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation)
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions)
    }
    if (System.getProperty("headless") == null || System.getProperty("headless") == "true") {
        println("-------------- Running in headless mode --------------")
        chromeOptions.addArguments("--headless")
        chromeOptions.addArguments("--disable-gpu")
        chromeOptions.addArguments("--no-sandbox")
        chromeOptions.addArguments("--lang=en")
        chromeOptions.addArguments("--window-size=1920,1080")
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36")
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions)
    }

    if(System.getProperty("runInDocker") == "true"){
        driver = new RemoteWebDriver(
            new URL("http://localhost:4444/wd/hub"), chromeCapabilities)
    } else {
        driver = new ChromeDriver(chromeCapabilities)
    }
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS)

    def uri = getBaseURI()
    driver.get(uri)
    wait = new WebDriverWait(driver, 10)
    actions = new Actions(driver)

}

/**
 * Embed a screenshot in test report if test is marked as failed
 */
After() { scenario ->
    if (scenario.isFailed()) {
        try {
            scenario.write("Current Page URL is " + driver.getCurrentUrl())
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
            scenario.embed(screenshot, "image/png")
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage())
        }
    }
    driver.quit()
}

