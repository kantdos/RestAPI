package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("A303, Grevillea");
		p.setLanguage("English-IN");
		p.setPhone_number("(+91)-123 456 7890");
		p.setWebsite("http://rahulshettyacademy.com");
		p.setName("Ravi");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Response response = given().log().all().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
		String responseString = response.asString();
		System.out.println(responseString);
	}

}
