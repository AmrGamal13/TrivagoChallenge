Feature: Navigate to a destination
  
  Scenario: Nagiate to a destinnation through the menu on the top left successfully
    Given I am on the trivago magazine home page
    When 	I click on the top left menu
    And   I click on the destinations link 
    And   I choose a certain destination
    Then 	The destinnation page opens successfully