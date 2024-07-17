package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import Endpoints.UserEndPoints;
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

public class UserTest {

	Faker faker;
	UserPOJO userPayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker= new Faker();
		userPayload= new UserPOJO();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*******Create user**********");
		Response response=UserEndPoints.createuser(userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******User is created**********");
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("*******Reading user Info**********");
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******User info retrieved**********");
	}
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("*******Updating user**********");
		//update user data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******User is updated**********");
		//Checking data after update
		Response res=UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("*******Deleting user**********");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******User Deleted**********");
	}
}
