package BasicTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import payload.Payload;
import pojo.AddPlace;
import pojo.Location;


public class Test_AddPlace {


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

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(p).when().post("maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();

        System.out.println(response);
	}


}
