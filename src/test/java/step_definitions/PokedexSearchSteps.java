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


public class PokedexSearchSteps {
	
	SearchPOF sPOF;
	
	@Before
	public void setUp() {
		sPOF = new SearchPOF("chrome", 10);
		System.out.println("------------------------------");
	}
	
	/*
	 * Created Background Steps 
	 * 
	 * https://www.toolsqa.com/cucumber/background-in-cucumber/
	 */
	@Given("we're in the Pokedex page")
	public void we_re_in_the_Pokedex_page() {
		sPOF.go_to_url("https://www.pokemon.com/us/pokedex/");
	}
	
	@Given("we dismissed the cookies")
	public void we_dismissed_the_cookies() {
		sPOF.dismiss_cookies();
	}
	
	@When("the Log In link is available")
	public void the_Log_In_link_is_available() {
	    sPOF.wait_for_login_link();
	}

	@When("the Sign Up link is available")
	public void the_Sign_Up_link_is_available() {
	    sPOF.wait_for_signup_link();
	}

	@Given("you typed nothing in the Search bar")
	public void you_typed_nothing_in_the_Search_bar() {
		sPOF.clear_text();
	}
	
	@Given("you typed Bulbasaur in the Search bar")
	public void you_typed_Bulbasaur_in_the_Search_bar() {
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
