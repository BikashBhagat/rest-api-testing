package BasicTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Course;
import pojo.GetCourse;

import static io.restassured.RestAssured.*;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utility.ReusableMethods;
public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ResponseOAuth = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
				.when()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
				.asString();
				System.out.println(ResponseOAuth);
				
		JsonPath js = ReusableMethods.rawToJson(ResponseOAuth);
		
		String accessToken = js.getString("access_token");
		
		GetCourse gc = given().queryParam("access_token", accessToken)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		
//		System.out.println(gc.getUrl());
//		System.out.println(gc.getCourses().getWebAutomation().get(0).getCourseTitle());
		
		List<Course> webAutomationCourses = gc.getCourses().getWebAutomation();
		
		List<Course> seleniumJava = webAutomationCourses.stream().filter(courses -> courses.getCourseTitle().equals("Selenium Webdriver Java")).collect(Collectors.toList());
		seleniumJava.forEach(c-> System.out.println(c.getCourseTitle()+"    "+c.getPrice()));
		
	}

}
