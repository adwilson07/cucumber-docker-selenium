Feature: Availability


  Scenario: Log into mercury flights
    Given I am on the mercury home page
    When I enter my user name and password
    Then I am not logged in
