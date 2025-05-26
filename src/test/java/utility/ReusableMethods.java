package utility;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public JsonPath rawToJson(String body) {
		JsonPath js = new JsonPath(body);
		return js;
	}

}
