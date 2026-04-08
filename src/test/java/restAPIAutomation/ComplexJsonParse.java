package restAPIAutomation;

import java.util.List;

import Payloads.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	/*
	Implement JsonPath parsing to:
		- Read course count and purchase amount
		- Print course titles and prices
		- Retrieve RPA course copies
		- Validate purchase amount equals sum(price × copies)
		
		*/

	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.courseDetails());
		int courseCount = js.getInt("courses.size()");

		System.out.println(courseCount);

		int purchaseAmount = js.getInt("dashboard.purchaseAmount");

		System.out.println(purchaseAmount);

		String firstTitle = js.get("courses[0].title");
		System.out.println(firstTitle);

		// print course titles

		for (int i = 0; i < courseCount; i++) {

			String course = js.get("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println(course);
			System.out.println(price);
		}

		for (int i = 0; i < courseCount; i++) {

			String title = js.getString("courses[" + i + "].title");

			if (title.equals("RPA")) {

				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println("RPA course has " + copies + " copies");
				break;
			}

		}

		int courseAmount = 0;

		for (int i = 0; i < courseCount; i++) {

			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");

			courseAmount = courseAmount + price * copies;

		}
		if (purchaseAmount == courseAmount) {

			System.out.println("Purchase amount match with course amount");
		} else {

			System.out.println("Purchase amount does not match with course amount");
		}
	}

}
