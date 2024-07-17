package Endpoints;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import payload.UserPOJO;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//UserEndPoints.java
//Created to perform Create Retrieve Update Delete requests to the API

public class UserEndPoints {

	public static Response createuser(UserPOJO payload)
	{
	Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return res;		
	}
	
	public static Response readUser(String username)
	{
	Response res=given()
				.pathParam("username", username)
			
		.when()
			.get(Routes.get_url);
		return res;		
	}
	public static Response updateUser(String username,UserPOJO payload)
	{
	Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", username)
		.when()
			.put(Routes.update_url);
		return res;		
	}
	public static Response deleteUser(String username)
	{
	Response res=given()
				.pathParam("username", username)
			
		.when()
			.delete(Routes.delete_url);
		return res;		
	}
}
