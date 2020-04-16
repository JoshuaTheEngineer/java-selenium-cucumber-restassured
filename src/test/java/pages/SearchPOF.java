package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.BrowserFactory;

public class SearchPOF {
	
	final WebDriver driver;
	final WebDriverWait wait;
	
	public SearchPOF(String driverName, int waitTime) {
		this.driver = BrowserFactory.startBrowser(driverName);
		this.wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how=How.ID,using="searchInput")
	WebElement searchInput;
	
	@FindBy(how=How.ID,using="search")
	WebElement searchBtn;
	
	@FindAll(@FindBy(how=How.CSS,using=".results .animating"))
	List<WebElement> pokedexResults; 
	
	@FindAll(@FindBy(how=How.CSS,using=".pokemon-info .id"))
	List<WebElement> pokedexResultIds;
	
	@FindAll(@FindBy(how=How.CSS,using=".pokemon-info h5"))
	List<WebElement> pokedexResultNames;
	
	/*
	 * Shortcut methods
	 */
	private By byResults() {
		return By.cssSelector(".results .animating");
	}
	
	public void go_to_url(String url) {
		this.driver.get(url);
	}
	
	public void clear_text() {
		searchInput.clear();
	}
	
	public void type_search_query(String text) {
		searchInput.sendKeys(text);
	}
	
	public void click_search_btn() {
		searchBtn.click();
	}
	
	public List<WebElement> get_all_pokedex_results() {
		return pokedexResults;
	}
	
	public List<WebElement> get_all_pokedex_ids() {
		return pokedexResultIds;
	}
	
	public List<WebElement> get_all_pokedex_names() {
		return pokedexResultNames;
	}
	
	public void wait_for_results(int targetNum) {
		wait.until(ExpectedConditions.numberOfElementsToBe(
			byResults(), 
			targetNum));
	}
	
	public void end_session() {
		this.driver.quit();
	}
}
