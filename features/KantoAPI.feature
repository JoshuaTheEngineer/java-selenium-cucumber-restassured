@kanto
Feature: Verify GET operations on PokeAPI using REST-Assured

	Scenario: Verify locations call
		Given I perform GET operation for "/locations"
		And I perform GET for the location number "1"
		Then I SHOULD see the location name as "celadon-city"