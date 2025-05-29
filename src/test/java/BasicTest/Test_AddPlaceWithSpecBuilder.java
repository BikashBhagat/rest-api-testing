package BasicTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payload.Payload;
import pojo.AddPlace;
import pojo.Location;


public class Test_AddPlaceWithSpecBuilder {


	public static void main(String[] args) {
        AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		p.setTypes(new String[] {"shoe park", "shop"});
		Location l = new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123")
			.addHeader("Content-Type", "application/json")
			.build();

		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	
		// Using RequestSpecification to send the request
		RequestSpecification reqToSent = given().spec(reqSpec).body(p);
		// Sending the request and validating the response
		String response = reqToSent.when().post("maps/api/place/add/json")
			.then().spec(resSpec).extract().response().asString();

        System.out.println(response);
	}


}
