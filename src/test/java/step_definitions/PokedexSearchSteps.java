package step_definitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;


/*
 * These step definitions are the code implementations
 * of the behaviors defined by the Feature files.
 */
public class PokedexSearchSteps {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setUp() {
		System.out.println("------------------------------");
		driver = new ChromeDriver(); // chromedriver's in "src/test/resources"
		// maximize browser
		driver.manage().window().maximize();
		// implicit wait for 15 seconds
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// This is an explicit wait that waits for only one element for 10 seconds
		wait = new WebDriverWait(driver, 10);
	}
	
	@Given("we're in the Pokedex page")
	public void we_re_in_the_Pokedex_page() {
		// goes to the Pokedex webpage
		driver.get("https://www.pokemon.com/us/pokedex/");
	}

	@When("you type nothing in the Search bar")
	public void you_type_nothing_in_the_Search_bar() {
		driver.findElement(By.id("searchInput")).clear();
	}

	@Then("the Search Results SHOULD return the first {int} Pokemon based on their Pokedex IDs")
	public void the_Search_Results_SHOULD_return_the_first_Pokemon_based_on_their_Pokedex_IDs(Integer expectedNum) { 
	    List<WebElement> searchResults = driver.findElements(By.cssSelector(".results .animating"));
	    List<WebElement> searchIDs = driver.findElements(By.cssSelector(".pokemon-info .id"));
	    String[] expectedIDs = { "001","002","003","004","005","006","007","008","009","010","011","012"};
	    Assert.assertEquals(expectedNum.intValue(), searchResults.size());
	    for(int i=0; i<12; i++) {
	    	Assert.assertTrue("The ID SHOULD be "+expectedIDs[i], searchIDs.get(i).getText().contains(expectedIDs[i]));
	    }
	}

	@When("you type Bulbasaur in the Search bar")
	public void you_type_Bulbasaur_in_the_Search_bar() {
		driver.findElement(By.id("searchInput")).sendKeys("Bulbasaur");
	}

	@When("you click the Search button")
	public void you_click_the_Search_button() {
		driver.findElement(By.id("search")).click();
	}

	@Then("the Search Results SHOULD return only Bulbasaur")
	public void the_Search_Results_SHOULD_return_only_Bulbasaur() throws InterruptedException {
		// This is an explicit wait that waits for only one element for 10 seconds
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".pokemon-info h5"), 1));
		List<WebElement> names = driver.findElements(By.cssSelector(".pokemon-info h5"));
		// If there's more than one element, it will check if at least Bulbasaur is in the list. 
		boolean findBulbasaur = false; 
		for(WebElement name : names) {
			if(name.getText().equals("Bulbasaur")) findBulbasaur = true;
		}
		Assert.assertTrue("Bulbsaur SHOULD be in the Search Results", findBulbasaur);
		Assert.assertEquals("There SHOULD be only one result", 1, names.size());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
