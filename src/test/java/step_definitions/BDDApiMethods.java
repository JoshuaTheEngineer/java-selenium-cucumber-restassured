package step_definitions;

// had to manually type these imports
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.hasItem;
import io.restassured.http.ContentType;

public class BDDApiMethods {

	public static void simpleGETLocation(String locNum) {
		// content type SHOULD be JSON
		given().contentType(ContentType.JSON).
			// accesses the location
			when().get(String.format("http://localhost:3000/locations/%s",locNum)).
			// checks if the body has celadon-city
			then().body("name", is("celadon-city"));
	}
	
	public static void performContainsCollection() { 
		given().contentType(ContentType.JSON)
			.when()
			.get(String.format("http://localhost:3000/locations/"))
			.then()
			// checks if it contains items
			.body("name", hasItems("celadon-city","cerulean-city","cinnabar-island","digletts-cave"));
	}
	
	// GET request with Path param
	public static void performPathParameter() {
		given().contentType(ContentType.JSON)
			.with().pathParams("locId", 1) // creates key-value to insert into when's GET call
			.when().get("http://localhost:3000/locations/{locId}")
			.then().body("name", containsString("celadon-city"));
	}
	
	// GET request with Query param
	public static void performQueryParameter() {
		given().contentType(ContentType.JSON)
			.queryParam("id", 1) // adds to the end of given url in when's GET call
			.when().get("http://localhost:3000/locations/")
			.then().body("name", hasItem("celadon-city"));
	}
}
