package pojo;

import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

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

		// RestAssured.baseURI = "https://rahulshettyacademy.com";
		// Response response = given().log().all().queryParam("key",
		// "qaclick123").body(p).when()
		// .post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
		// String responseString = response.asString();
		// System.out.println(responseString);

		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		RequestSpecification response1 = given().spec(reqSpec).body(p);

		Response response2 = response1.when().post("/maps/api/place/add/json").then().spec(respSpec).extract()
				.response();

		// then().assertThat().statusCode(200).extract().response();

		String responseString = response2.asString();
		System.out.println(responseString);
	}

}
