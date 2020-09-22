package com.restapi;
import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// get the response data
		JsonPath js = new JsonPath(payload.CoursePrice());

		// get the number of courses
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// get the total purchaseAmount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// print title of course title and price
		for (int i = 0; i < count; i++) {
			String title = js.get("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println(title + " " + price);
		}

		// get number of copies sold for RPA
		for (int i = 0; i < count; i++) {
			String title = js.get("courses[" + i + "].title");
			if (title.equalsIgnoreCase("RPA")) {
				System.out.println(js.getInt("courses[" + i + "].copies"));
				break;
			}

		}

	}

}
