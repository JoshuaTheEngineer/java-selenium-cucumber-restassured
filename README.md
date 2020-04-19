# Java-Maven tutorial using Selenium Webdriver, Cucumber, and REST Assured
> By: Joshua Cadavez

This was done on MacOS. And I'll post a YouTube tutorial eventually. So, if you have alternative methods for Windows, please post such suggestions in the videos.

Will be testing the [Pokemon website](https://www.pokemon.com/us/) but the API Testing will be done with [JSON Server](https://github.com/typicode/json-server).

However, I'll be using real JSON data from [PokeAPI](https://pokeapi.co/docs/v2.html/#types). *Note, please be polite when using it because as of April 14 2020, it fixed limit of 100 API requests per IP address per minute*

## Step 0: Setup Tools

1. I used Eclipse Photon as my IDE because I can download Cucumber plugins and generate a Maven project. Feel free to use another.

- If you use Eclipse, install the [Cucumber Plugin](http://marketplace.eclipse.org/content/cucumber-eclipse-plugin) via Eclipse Marketplace.

2. With NodeJS and NPM, install [JSON Server](https://github.com/typicode/json-server).

	Install with below command.
	> npm install -g json-server

	I had to use below command because it required admin permissions.
	> sudo npm install -g json-server

3. Install [Maven](https://maven.apache.org/install.html)

## Step 1: Initial Setup and Dependencies

Visit tag ***v1.0.0-cucumberSetup***

1. Create a Maven project

2. Add the below dependencies into your pom.xml file. You can find them in the [Maven repository](https://mvnrepository.com/artifact/io.cucumber).
	- Cucumber-java
	- Cucumber-junit
	- Cucumber-core
	- Gherkin
	- Cucumber-jvm-deps
	- Cucumber-html
	- Cucumber-reporting
	- JUnit

3. To check if it's running correctly, add a sample feature file (I used this [one](https://cucumber.io/docs/guides/10-minute-tutorial/#verify-cucumber-installation)). Then, run the Feature file.
It should show similar results below

> Scenario: Sunday isn't Friday        # src/test/java/features/is_it_friday_yet.feature:4
  Given today is Sunday              # null
  When I ask whether it's Friday yet # null
  Then I should be told "Nope"       # null

> Undefined scenarios:
> file:///Users/macpro/eclipse-workspace/java-selenium-cucumber-restassured/src/test/java/features/is_it_friday_yet.feature:4# Sunday isn't Friday

> 1 Scenarios (1 undefined)
> 3 Steps (3 undefined)
> 0m0.129s

## Step 2: Add Selenium Webdriver

Visit tag ***v2.0.0-seleniumScripting***

0. Remove the App.java and AppTest.java files if you haven't already.

1. Add Selenium-Java dependency. See *pom.xml*

2. Write a few scenarios for your feature. See *pokedex_search.feature* 

3. Write step definitions and the test runner. See *PokedexSearchSteps.java*

- Great articles about [Cucumber hooks](https://zsoltfabok.com/blog/2012/09/cucumber-jvm-hooks/) and [Practical usage of Before and After](http://www.automationtestinghub.com/cucumber-hooks-before-after/)

4. Try running ChromeDriver. If you face Chrome compatibility issues, download the right one [here](https://chromedriver.storage.googleapis.com/index.html)

## Step 3: Implement Page Object Model with Page Factory

Visit tag ***v3.0.0-PageObjectFactory***

1. Create a BrowserFactory file that will give it flexibility for choosing WebDrivers. See the factory files under ***util*** directory.

2. Create a Page Object file. See the files under **pages** directory. 

3. Implement step definitions based off Page Object file. See the files under ***step_definitions*** directory.

## Step 4: Added more Cucumber hooks

1. Added Background in feature file. See [reference](https://www.toolsqa.com/cucumber/background-in-cucumber/)

2. Implemented the [Scenario Outline with Examples](https://cucumber.netlify.app/docs/gherkin/reference/). Had to remove duplicate methods. See files under **features** and **step_definitions**

3. I added [tags](https://cucumber.netlify.app/docs/cucumber/api/#tags) so we can reference which tests to run. See files under **features** directory. And how to call them with testrunner under **runners** directory

## Step 5: Setting up JSON data

1. Extract JSON data with your API call and save it as ***kanto.json*** in the **src/test/java/resources** directory. 
	- https://pokeapi.co/api/v2/region/1/
		- I had to modify my json to get it to work.
			- I removed most data except those under keys
				- locations
				- version_groups
			- I had to ID each of them due to constraints of 'json-server'. So, I trimmed down the JSON file 
	- WARNING #1: You may get a ".json is not supported" issue. To resolve this issue, wrap your json data under the key "data". See mine as an example or read this [StackOverflow thread](https://stackoverflow.com/questions/46195599/type-of-id-number-in-user-json-is-not-supported-use-objects-or-arrays-of-ob)
	- WARNING #2: JSON Server [doesn't support Nested data](https://stackoverflow.com/questions/56582634/not-able-to-get-object-key-data-in-json-server) 
	
	
2. With the NPM Server (that I installed in *Step 0*), I started it pointing to the file above. 

> json-server --watch kanto.json

3. To test if it works, in a browser, type and enter

>  http://localhost:3000/locations
>  http://localhost:3000/version_groups

## Step 6: REST Assured (GET calls with Path and Query Params)

1. Install the latest version (that works) of [REST Assured](https://github.com/rest-assured/rest-assured/wiki/GettingStarted#documentation) via inserting the dependency into the **pom.xml** file 

2. To make running API Tests quicker, annotate the tags in your TestRunner to be '@{insert_name}' and tag your feature file the same. 

	- In my example, see my **TestRunner.java** and **KantoAPI.feature** files. I tagged them with @kanto
3. When you run your test runner, you can add the generated methods to a step definition file

4. Create Feature files based off your API. See mine as an example under the **features** directory.

5. You'll need the below imports in the step definition file

> import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItems;
import io.restassured.http.ContentType;

5. Since the REST Assured methods have their own BDD format, you may implement some of Feature files' Given-When-Then statements while others might be left blank. Use REST Assured's GET [Query](https://github.com/rest-assured/rest-assured/wiki/Usage#parameters) + [Path](https://github.com/rest-assured/rest-assured/wiki/Usage#path-parameters) calls if needed

## Helpful Resources for building a Webdriver-Cucumber-RESTAssured Test Framework

1. [Cucumber Anti-patterns](http://www.thinkcode.se/blog/2016/06/22/cucumber-antipatterns)
2. [9 Tips for Improving Cucumber test readability](https://www.foreach.be/blog/9-tips-improving-cucumber-test-readability)
3. [API Testing with REST-assured and Cucumber](https://www.youtube.com/playlist?list=PL6tu16kXT9PpgqfMbMdzUzDenYgb0gbk0)