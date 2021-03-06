package pages;

import java.util.ArrayList;
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
	
	@FindBy(how=How.LINK_TEXT,using="Log In")
	WebElement logInLink;
	
	@FindBy(how=How.LINK_TEXT,using="Sign Up")
	WebElement signUpLink;
	
	@FindBy(how=How.ID,using="cookie-dismisser")
	WebElement dismissCookieBtn;
	
	@FindAll(@FindBy(how=How.CSS,using=".results .animating"))
	List<WebElement> pokedexResults; 
	
	@FindAll(@FindBy(how=How.CSS,using=".pokemon-info .id"))
	List<WebElement> pokedexResultIds;
	
	@FindAll(@FindBy(how=How.CSS,using=".pokemon-info h5"))
	List<WebElement> pokedexResultNames;
	
	/*
	 * Shortcut methods
	 */
	
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
	
	public List<Integer> get_all_pokedex_ids() {
		List list = new ArrayList<Integer>();
		for(WebElement elem : pokedexResultIds) {
			System.out.println("Element Text: " + elem.getText());
			list.add(Integer.valueOf(elem.getText().substring(1)));
		}
		return list;
	}
	
	public List<String> get_all_pokedex_names() {
		List list = new ArrayList<String>();
		for(WebElement elem : pokedexResultNames) {
			list.add(elem.getText());
		}
		return list;
	}
	
	public void wait_for_signup_link() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign Up")));
	}
	
	public void wait_for_login_link() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log In")));
	}
	
	public void wait_for_results(int targetNum) {
		wait.until(ExpectedConditions.numberOfElementsToBe(
			By.cssSelector(".results .animating"), 
			targetNum));
	}
	
	public void dismiss_cookies() {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-dismisser")));
			dismissCookieBtn.click();
		} catch(Exception e) {
			System.out.println("Error with cookies: " + e.getMessage());
		}
	}
	
	public void end_session() {
		this.driver.quit();
	}
}
