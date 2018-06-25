package stepDefinitions.navigationSteps

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import cucumber.api.CucumberOptions.*
import pageObjects.loginPage.login
import static cucumber.api.groovy.EN.*


Given(~/^I am on the mercury home page$/) { ->
    assertThat("homepage",login.getURI(),is("http://newtours.demoaut.com/"))
}

When(~/^I enter my user name and password$/) { ->
    login.enterUsername("austin")
    login.enterPassword("tester")
    login.hitEnter()
}

Then(~/^I am not logged in$/) { ->
    assertThat("homepage",login.getURI(),containsString("http://newtours.demoaut.com/"))
}

