package BasicTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.Payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
public class LocationUpdateInMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		given - all inputs details
//		when - call the api, http method with resources
//		then - validation need to be done
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();		
		JsonPath js = new JsonPath(response);
		String place_id = js.get("place_id");
		System.out.println("Location Placed Place ID:"+place_id);
		
		
//		update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.updatePlace(place_id)).when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		System.out.println("Address Updated Successfully");
		
//		get place
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(getResponse);
		String updatedAddress = js1.getString("address");
		System.out.println("Address Retrieved Successfully :"+js1.getString("address"));
		
		Assert.assertEquals(updatedAddress,"xyz street, 12345661234");
		
//		deletePlace
		
		
		given()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(Payload.deletePlace(place_id))
		.when()
		.delete("maps/api/place/delete/json")
		.then().log().all()
		.assertThat().statusCode(200)
		.body("status", equalTo("OK"));
		
		System.out.println("Location Deleted Successfully");
		
		
	}

}
