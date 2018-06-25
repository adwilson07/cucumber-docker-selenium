package pageObjects.loginPage

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.ExpectedConditions
import stepDefinitions.cucumberRunner

class login {
    protected static final String usernameBox = "userName"
    protected static final String passwordBox = "password"


    static enterUsername(String username) {
        cucumberRunner.driver.findElement(By.name(usernameBox)).sendKeys(username)
    }

    static enterPassword(String password) {
        cucumberRunner.driver.findElement(By.name(passwordBox)).sendKeys(password)
    }
    static hitEnter() {
        cucumberRunner.driver.findElement(By.name(passwordBox)).sendKeys(Keys.ENTER)
    }
    static getURI(){
        cucumberRunner.driver.getCurrentUrl()
    }


}
