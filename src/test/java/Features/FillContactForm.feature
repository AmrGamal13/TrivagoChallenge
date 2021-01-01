Feature: Contact Form
  
  Scenario: Fill the contact form successfully
    Given I am on the trivago magazine home page
    When 	I click on contact link in the footer
    And 	I fill the cobtact form
    Then 	I get a successfull message