package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class TestExamples {
	
	@Test
	public void Test_1() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void Test_2() {
		baseURI = "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[0].id", equalTo(7))
			.log().all();
		
	}
	
	@Test
	public void Test_Get() {
		given().get("https://reqres.in/api/users?page=2").
		then().
		statusCode(200).
		body("data.id[1]",equalTo(8)).
		log().all();
	}
	
	@Test
	public void Test_put() {
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		System.out.println(request);
		given().
		body(request.toJSONString()).
		when().
		put("https://reqres.in/api/users?page=2").
		then().statusCode(200).
		log().all();
	}
	
	@Test
	public void Test_post() {
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		System.out.println(request);
		given().
		body(request.toJSONString()).
		when().
		post("https://reqres.in/api/users?page=2").
		then().statusCode(201).
		log().all();
	}
	
	@Test
	public void Test_delete() {
		JSONObject request = new JSONObject();
		given().
		body(request.toJSONString()).
		when().
		delete("https://reqres.in/api/users?page=2").
		then().statusCode(204).
		log().all();
	}
	
}
