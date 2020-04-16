Feature: Pokemon Pokedex Search

  Scenario: Search without query, the Search Results will show the first 12 Pokemon based on their Pokedex IDs.
    Given we're in the Pokedex page
    When you type nothing in the Search bar
    And you click the Search button
    Then the Search Results SHOULD return the first 12 Pokemon based on their Pokedex IDs
    
  Scenario: Search Bulbasaur, the Search Results will only show Bulbasaur 
    Given we're in the Pokedex page
    When you type Bulbasaur in the Search bar
    And you click the Search button
    Then the Search Results SHOULD return only Bulbasaur