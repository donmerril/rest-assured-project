package StepDefinitions;

import java.util.List;
import java.util.Set;

import org.testng.Assert;

import Payloads.Payload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restAPIAutomation.AddBookResult;
import restAPIAutomation.DynamicJson;
import utils.ConfigReader;

public class StepDefinitions {

	ConfigReader config;
	static Set<String> bookIDs;
	static Response addBookResponses;
	static List<Response> getBookResponse;
	static List<Response> deleteBookResponse;
	static AddBookResult addBookresult;

	// made all variable static because instance variables cannot be shared from one
	// scenario to other

	@Given("the {string} Properties And Payload are Ready")
	public void the_payload_is_ready(String payLoadType) {

		config = new ConfigReader();
		RestAssured.baseURI = config.getProperty("base.url");
		Assert.assertNotNull(RestAssured.baseURI, "Base URI is null!");
		Assert.assertFalse(RestAssured.baseURI.trim().isEmpty(), "Base URI is empty!");

		switch (payLoadType) {
		case "Addbook":

			String addBookPayload = Payload.addBook("", "", "", "");
			Assert.assertNotNull(addBookPayload, payLoadType + " payload is null");
			Assert.assertFalse(addBookPayload.trim().isEmpty(), payLoadType + " payload is empty!");

			break;

		case "DeleteBook":

			String deleteBookPayload = Payload.deleteBook("");

			Assert.assertNotNull(deleteBookPayload, payLoadType + " payload is null");
			Assert.assertFalse(deleteBookPayload.trim().isEmpty(), payLoadType + " payload is empty!");
		}

		// no need of getbook because it does not have a request body

	}

	@When("User calls the {string} Api")
	public void user_calls_the(String apiName) {

		switch (apiName) {

		case "AddBook":
			addBookresult = DynamicJson.addBookAPI(config);

			break;

		case "GetBook":
			bookIDs = addBookresult.getBooksIDs();
			getBookResponse = DynamicJson.getBookAPI(bookIDs);

			break;

		case "DeleteBook":

			deleteBookResponse = DynamicJson.deleteBook();
			break;
		}

	}

	@Then("{string} Api Response is successful")
	public void response_status_should_be(String apiName) {

		int expectedStatus = 200;

		switch (apiName) {

		case "AddBook":

			for (Response res : addBookresult.getAddBooksResponses()) {

				Assert.assertEquals(res.getStatusCode(), expectedStatus, "Status codes does not match");
				String statusMsg = res.jsonPath().getString("Msg");
				Assert.assertEquals(statusMsg, "successfully added", "Add book API status Message does not match");

				// here i match status code as well as status message to pass this step
			}
			break;

		case "GetBook":

			for (Response res : getBookResponse) {

				String isbn = res.jsonPath().getString("[0].isbn");
				String aisle = res.jsonPath().getString("[0].aisle");
				String bookID = isbn + aisle;

				System.out.println(bookID);
				Assert.assertEquals(res.getStatusCode(), expectedStatus, "Status codes does not match");
				Assert.assertTrue(bookIDs.contains(bookID), "BookID not found in set:" + bookID);

			}
			break;

		case "DeleteBook":

			for (Response res : deleteBookResponse) {
                 
				
				Assert.assertEquals(res.getStatusCode(), expectedStatus, "Status codes does not match");
				Assert.assertEquals(res.jsonPath().getString("msg"), "book is successfully deleted",
						"Delete book API status Message does not match");
			}

			break;
		}

	}

}
