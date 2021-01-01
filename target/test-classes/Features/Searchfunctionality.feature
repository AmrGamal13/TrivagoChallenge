Feature: Search using the search bar
  
  Scenario: Search for any location on Magazine by using the search bar
    Given I am on the trivago magazine home page
    When 	I click on the search icon
    And 	I search by 'Canada' country
    Then 	I get 'Canada' in the search results