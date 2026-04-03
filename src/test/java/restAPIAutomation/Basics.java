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

		
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");

		String address = "70 Summer Walk, USA";

		// update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\n" + "  \"place_id\": \"" + placeID + "\",\n" + "  \"address\": \"" + address + "\",\n"
						+ "  \"key\": \"qaclick123\"\n" + "}")
				.put("maps/api/place/update/json").then().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// add place -> update place-> get place to validate if updated place is present
		// in the response

		// getPlace

		given().queryParam("key", "qaclick123").queryParam("place_id", placeID).when().get("maps/api/place/get/json")
				.then().log().all().statusCode(200).body("address", equalTo(address));
	}

}
