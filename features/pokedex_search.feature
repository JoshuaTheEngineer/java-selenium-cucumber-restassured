Feature: Pokemon Pokedex Search

  Background: The user is not logged in
  	Given we're in the Pokedex page
  	And we dismissed the cookies
  	When the Log In link is available
  	And the Sign Up link is available
  
  Scenario: Search without query, the Search Results will show the first 12 Pokemon based on their Pokedex IDs.
    Given you typed nothing in the Search bar
    When you click the Search button
    Then the Search Results SHOULD return the first 12 Pokemon based on their Pokedex IDs
  
  Scenario: Search Bulbasaur, the Search Results will only show Bulbasaur 
    Given you typed Bulbasaur in the Search bar
    When you click the Search button
    Then the Search Results SHOULD return only Bulbasaur