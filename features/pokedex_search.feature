Feature: Pokemon Pokedex Search

  Background: The user is not logged in
  	Given we're in the Pokedex page
  	And we dismissed the cookies
  	When the Log In link is available
  	And the Sign Up link is available
  
  Scenario: Search without query, the Search Results will show the first 12 Pokemon based on their Pokedex IDs.
    Given you typed "" in the Search bar
    When you click the Search button
    Then the Search Results SHOULD return 12 Pokemon
    And SHOULD ascend starting from 001
  
  Scenario Outline: Search "<text>" , the Search Results will show <num> Pokemon with "<text>" in its name 
    Given you typed "<text>" in the Search bar
    When you click the Search button
    Then the Search Results SHOULD return <num> Pokemon
    And they SHOULD have "<text>" in their name
    
  Examples:
  	| text 		| num 	|
  	| Bulbasaur | 1		|
  	| lax		| 2 	|
  	| Mew		| 2		|
  	| Dragon	| 2		|
  	