package com.restapi;

import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F4QGQJ3UGJni60WcMuV4cbS2aN8SjJdOyK1r2_BtdquZ-r1s5J3TP6thUDIXIEriihnGuUNJHBrkaIk72rMFxv2o&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];

		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());

		// System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		List<Api> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("SoapUI Webservices testing:- " + apiCourses.get(i).getPrice());
			}
		}

		List<WebAutomation> webCources = gc.getCourses().getWebAutomation();
		for (int i = 0; i < webCources.size(); i++) {
			System.out.println(webCources.get(i).getCourseTitle() + " : " + webCources.get(i).getPrice());
		}

	}

}
