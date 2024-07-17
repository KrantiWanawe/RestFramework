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
import java.util.ResourceBundle;

//UserEndPoints.java
//Created to perform Create Retrieve Update Delete requests to the API

public class UserEndPoints_properties {
	//To get the URLs from properties file
	public static ResourceBundle getURL()
	{
		ResourceBundle route=ResourceBundle.getBundle("Routes");  //load properties file - Routes
		return route;
	}

	public static Response createuser(UserPOJO payload)
	{
		//Store Post URL in the string
		String post_url = getURL().getString("post_url");
	Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		return res;		
	}
	
	public static Response readUser(String username)
	{
		String get_url = getURL().getString("get_url");
	Response res=given()
				.pathParam("username", username)
			
		.when()
			.get(get_url);
		return res;		
	}
	public static Response updateUser(String username,UserPOJO payload)
	{
		String update_url = getURL().getString("update_url");
	Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username", username)
		.when()
			.put(update_url);
		return res;		
	}
	public static Response deleteUser(String username)
	{
		String delete_url = getURL().getString("delete_url");
	Response res=given()
				.pathParam("username", username)
			
		.when()
			.delete(delete_url);
		return res;		
	}
}
