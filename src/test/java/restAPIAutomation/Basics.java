package restAPIAutomation;

import io.restassured.RestAssured;
import io.restassured.internal.mapping.JsonbMapper;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Payloads.AddPlace;

public class Basics {

	// validate if ADD place API is working as expected

	// given() - request setup (all inputs like base url, path and quesry params,
	// headers etc)
	// when() - sending request - resource and http method
	// then() - validating response

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(AddPlace.addPlacePayload()).when().post("maps/api/place/add/json").then().log().all()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract()
				.asString();

		

	}

}
