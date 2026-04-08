package restAPIAutomation;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;

import Payloads.Payload;

public class DynamicJson {

	public static void addBookAPI(ConfigReader config) {

		
		int count = Integer.parseInt(config.getProperty("books.count"));
		
		for(int i=1; i<=count; i++) {
			
			String bookName = config.getProperty("book"+i+".name");
			String ISBN = config.getProperty("book"+i+".isbn");
			String aisleNum = config.getProperty("book"+i+".aisle");
			String author = config.getProperty("book"+i+".author");
			
			
			String response = given().header("Content-type", "application/json").
			body(Payload.addBook(bookName,aisleNum,ISBN,author)).when()
			.post("Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();

			JsonPath js = new JsonPath(response);

			String ID = js.get("ID");
			System.out.println(ID);
			
		}
		

	}

	public static void main(String[] args) {
		ConfigReader config = new ConfigReader();
		RestAssured.baseURI = config.getProperty("base.url");

		DynamicJson.addBookAPI(config);

	}

}
