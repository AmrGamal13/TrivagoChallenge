Feature: Datalayer Events
  
  Scenario: Make sure some events are triggered
    Given I am on the trivago magazine home page
    When 	Click on Read more button
    Then 	some events are triggered such as 'OneTrustLoaded', 'OptanonLoaded', 'OneTrustGroupsUpdated'
    Then 	every event includes some parameters
    Then 	'contentLoaded' event is triggered
    Then 	hotelIds event is not empty
    Then  target-properties has same path in the URL