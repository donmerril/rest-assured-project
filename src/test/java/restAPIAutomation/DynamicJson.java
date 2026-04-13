package restAPIAutomation;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Payloads.Payload;

public class DynamicJson {

	public static Set<String> bookIDs = new HashSet();
	public static List<Response> addBookResponses = new ArrayList<>();
	
	

	public static AddBookResult addBookAPI(ConfigReader config) {

		int count = Integer.parseInt(config.getProperty("books.count"));

		for (int i = 1; i <= count; i++) {

			String bookName = config.getProperty("book" + i + ".name");
			String ISBN = config.getProperty("book" + i + ".isbn");
			String aisleNum = config.getProperty("book" + i + ".aisle");
			String author = config.getProperty("book" + i + ".author");

			Response response = given().header("Content-type", "application/json")
					.body(Payload.addBook(bookName, aisleNum, ISBN, author)).when().post("Library/Addbook.php").then()
					.log().all().extract().response();

			String ID = response.jsonPath().getString("ID");

			System.out.println(ID);

			bookIDs.add(ID);
			addBookResponses.add(response);

		}

		return new AddBookResult(bookIDs, addBookResponses);

	}

	public static List<Response> getBookAPI(Set<String> bookIDs) {

		List<Response> responses = new ArrayList<>();

		for (String bkID : bookIDs) {

			Response response = given().queryParam("ID", bkID).header("Content-type", "application/json").when()
					.get("/Library/GetBook.php").then().extract().response();

			System.out.println(response.asString());

			responses.add(response);

		}

		return responses;

	}

}
