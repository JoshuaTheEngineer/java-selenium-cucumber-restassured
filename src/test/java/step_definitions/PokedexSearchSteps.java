package step_definitions;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.SearchPOF;


/*
 * Implemented POM factory methods via Page Factory methods
 * 
 * also reordered steps to be 
 * Before
 * Given
 * When
 * Then
 * After
 */
public class PokedexSearchSteps {
	
	SearchPOF sPOF;
	
	@Before
	public void setUp() {
		sPOF = new SearchPOF("chrome", 10);
		System.out.println("------------------------------");
	}
	
	@Given("we're in the Pokedex page")
	public void we_re_in_the_Pokedex_page() {
		sPOF.go_to_url("https://www.pokemon.com/us/pokedex/");
	}

	@When("you type nothing in the Search bar")
	public void you_type_nothing_in_the_Search_bar() {
		sPOF.clear_text();
	}
	
	@When("you type Bulbasaur in the Search bar")
	public void you_type_Bulbasaur_in_the_Search_bar() {
		sPOF.type_search_query("Bulbasaur");
	}

	@When("you click the Search button")
	public void you_click_the_Search_button() {
		sPOF.click_search_btn();
	}

	@Then("the Search Results SHOULD return the first {int} Pokemon based on their Pokedex IDs")
	public void the_Search_Results_SHOULD_return_the_first_Pokemon_based_on_their_Pokedex_IDs(int expectedNum) { 
	    sPOF.wait_for_results(expectedNum);
		List<WebElement> searchResults = sPOF.get_all_pokedex_results();
	    List<WebElement> searchIDs = sPOF.get_all_pokedex_ids();
	    String[] expectedIDs = { "001","002","003","004","005","006","007","008","009","010","011","012"};
	    Assert.assertEquals(expectedNum, searchResults.size());
	    for(int i=0; i<12; i++) {
	    	Assert.assertTrue("The ID SHOULD be "+expectedIDs[i], searchIDs.get(i).getText().contains(expectedIDs[i]));
	    }
	}

	@Then("the Search Results SHOULD return only Bulbasaur")
	public void the_Search_Results_SHOULD_return_only_Bulbasaur() {
		sPOF.wait_for_results(1);
		List<WebElement> names = sPOF.get_all_pokedex_names(); 
		Assert.assertEquals("Bulbsaur SHOULD be the only Search Result", "Bulbasaur", names.get(0).getText());
	}
	
	@After
	public void tearDown() {
		sPOF.end_session();
	}
}
