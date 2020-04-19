@kanto
Feature: Verify GET operations on PokeAPI using REST-Assured

	Scenario: Verify locations call
		Given I perform GET operation for "/locations"
		And I perform GET for the location number "1"
		Then I SHOULD see the location name as "celadon-city"
		
	Scenario: Verify collection of locations 		
		Given I perform GET operation for "/locations"
		Then I SHOULD see the location names
		
	Scenario: Verify Parameter of Get
		Given I perform GET operation for "/locations"
		Then I SHOULD see verify GET parameter