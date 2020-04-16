package runners;

import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/*
 * Added TestRunner, this will run feature files 
 * based on the step definitions.
 * 
 * The plugin allows it to output the results via HTML.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features="features",
		glue={"step_definitions"},
		plugin= {"pretty","html:test-output"})
public class TestRunner { }
