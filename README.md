# Java-Maven tutorial using Selenium Webdriver, Cucumber, and REST Assured
> By: Joshua Cadavez

This was done on MacOS. And I'll post a YouTube tutorial eventually. So, if you have alternative methods for Windows, please post such suggestions in the videos.

Will be testing the [Pokemon website](https://www.pokemon.com/us/) but the API Testing will be done with [JSON Server](https://github.com/typicode/json-server).

However, I'll be using real JSON data from [PokeAPI](https://pokeapi.co/docs/v2.html/#types). *Note, please be polite when using it because as of April 14 2020, it fixed limit of100 API requests per IP address per minute*

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

Scenario: Sunday isn't Friday        # src/test/java/features/is_it_friday_yet.feature:4
  Given today is Sunday              # null
  When I ask whether it's Friday yet # null
  Then I should be told "Nope"       # null

Undefined scenarios:
file:///Users/macpro/eclipse-workspace/java-selenium-cucumber-restassured/src/test/java/features/is_it_friday_yet.feature:4# Sunday isn't Friday

1 Scenarios (1 undefined)
3 Steps (3 undefined)
0m0.129s

## Helpful Resources for building a Webdriver-Cucumber-RESTAssured Test Framework

1. [Cucumber Anti-patterns](http://www.thinkcode.se/blog/2016/06/22/cucumber-antipatterns)
2. [9 Tips for Improving Cucumber test readability](https://www.foreach.be/blog/9-tips-improving-cucumber-test-readability)
