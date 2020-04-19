package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class KantoApiSteps {
	
	@Given("I perform GET operation for {string}")
	public void i_perform_GET_operation_for(String url) {	}
	
	@Given("I perform GET for the location number {string}")
	public void i_perform_GET_for_the_location_number(String locNum) {	
		BDDApiMethods.simpleGETLocation(locNum);
	}

	@Then("I SHOULD see the location name as {string}")
	public void i_SHOULD_see_the_location_name_as(String string) {	}
	
	@Then("I SHOULD see the location names")
	public void i_SHOULD_see_the_location_names() {
		BDDApiMethods.performContainsCollection();
	}
	
	@Then("I SHOULD see verify GET parameter")
	public void i_SHOULD_see_verify_GET_parameter() {
	    //BDDApiMethods.performPathParameter();
		BDDApiMethods.performQueryParameter();
	}
}
