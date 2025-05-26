package BasicTest;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class TestBookLibraryAPI {
	
	
	String responseAddBook;
	String responseDeleteBook;
	@Test(dataProvider = "data")
	public void Test_addBookInLibrary(String isbn, String aisle){
		RestAssured.baseURI = "http://216.10.245.166";
		
		 responseAddBook = given().header("Content-Type","application/json")
		.body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}")
		.when()
		.post("Library/Addbook.php")
		.then().assertThat()
		.statusCode(200).extract().response().asString();
		 
		String MethodName = new Throwable().getStackTrace()[0].getMethodName();
		System.out.println("The response of test: "+MethodName+"-  "+responseAddBook);
		
		
	}
	@Test(dependsOnMethods = "Test_addBookInLibrary",dataProvider = "data")
	public void Test_DeleteBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		 responseDeleteBook = given().header("Content-Type","application/json")
		.body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn+aisle+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "")
		.when()
		.delete("Library/DeleteBook.php")
		.then().assertThat()
		.statusCode(200).body("msg" , equalTo("book is successfully deleted")).extract().response().asString();
		System.out.println(responseDeleteBook);
		
	}
	
	
	@DataProvider(name = "data")
	public Object[][] getData() {
		return new Object[][] {{"xyzabc","1999"},{"abcdefgh","1947"},{"ijklmno","1905"}};
	}

}
