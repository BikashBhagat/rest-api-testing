package BasicTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestBookLibraryAPITestingWithJsonData {

	
	String responseAddBook;
	String responseDeleteBook;
	@Test
	public void Test_addBookInLibrary(){
		RestAssured.baseURI = "http://216.10.245.166";
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		File requestData = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\payloads\\"+methodName+".json");
		 responseAddBook = given().header("Content-Type","application/json")
		.body(requestData)
		.when()
		.post("Library/Addbook.php")
		.then().assertThat()
		.statusCode(200).extract().response().asString();
		 
		
		System.out.println("The response of test: "+methodName+"-  "+responseAddBook);
		
		
	}
	@Test(dependsOnMethods = "Test_addBookInLibrary")
	public void Test_DeleteBook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		File requestData = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\payloads\\"+methodName+".json");
		 responseDeleteBook = given().header("Content-Type","application/json")
		.body(requestData)
		.when()
		.delete("Library/DeleteBook.php")
		.then().assertThat()
		.statusCode(200).body("msg" , equalTo("book is successfully deleted")).extract().response().asString();
		System.out.println(responseDeleteBook);
		System.out.println("The response of test: "+methodName+"-  "+responseDeleteBook);
		
	}

}
