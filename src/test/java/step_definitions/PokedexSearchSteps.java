package step_definitions;

import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.SearchPOF;


/*
 * I modified my feature files so they are flexible 
 * to use the values from the Scenario Outline
 * 
 * https://cucumber.netlify.app/docs/gherkin/reference/
 * https://www.baeldung.com/cucumber-scenario-outline
 */
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

	// replaced duplicate methods
	@Given("you typed {string} in the Search bar")
	public void you_typed_in_the_Search_bar(String str) {
	    if (str.isEmpty()) sPOF.clear_text();
	    else sPOF.type_search_query(str);
	}

	@When("you click the Search button")
	public void you_click_the_Search_button() {
		sPOF.click_search_btn();
	}
	
	@Then("the Search Results SHOULD return {int} Pokemon")
	public void the_Search_Results_SHOULD_return_Pokemon(Integer targetNum) {
	    sPOF.wait_for_results(targetNum);
	    Assert.assertEquals("There SHOULD be "+targetNum+" results.", targetNum.intValue(), sPOF.get_all_pokedex_results().size());
	}
	
	@Then("SHOULD ascend starting from {int}")
	public void should_ascend_starting_from(Integer id) {
	    List<Integer> list = sPOF.get_all_pokedex_ids();
	    for(Integer pokedexId : list) {
	    	Assert.assertEquals(pokedexId, id);
	    	id++;
	    }
	}
	
	@Then("they SHOULD have {string} in their name")
	public void they_SHOULD_have_in_their_name(String text) {
	    List<String> list = sPOF.get_all_pokedex_names();
	    for(String name : list) {
	    	Assert.assertTrue(name + " SHOULD have " + text + " in its name.", name.contains(text));
	    }
	}
	
	@After
	public void tearDown() {
		sPOF.end_session();
	}
}
