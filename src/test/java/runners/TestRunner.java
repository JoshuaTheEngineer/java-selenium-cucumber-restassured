package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="features",
		glue={"step_definitions"},
		tags= "@kanto",
		plugin= {"pretty","html:test-output"})
public class TestRunner { }
