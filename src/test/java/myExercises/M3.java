package myExercises;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.*;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class M3 extends JsonPlaceHolderTestBase {

     /*
		1) Create a class and name it as you wish :)
		2) When
		     I send a GET Request to https://jsonplaceholder.typicode.com/todos
		   Then
			 HTTP Status code should be "200"
			 And Content type should be in "JSON" format
			 And there should be 200 "title"
			 And "dignissimos quo nobis earum saepe" should be one of the "title"s
			 And 111, 121, and 131 should be among the "id"s
			 And 4th title is "et porro tempora"
			 And last title is "ipsam aperiam voluptates qui"
    */

    @Test
    public void test() {
        spec01.pathParam("parametre1", "todos");
        Response response = given().spec(spec01).when().get("/{parametre1}");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        assertEquals(200, jsonPath.getList("title").size());
        assertTrue(jsonPath.getString("title").contains("dignissimos quo nobis earum saepe"));
        assertTrue( jsonPath.getList("id").stream().anyMatch((t -> t.equals(111) || t.equals(121) || t.equals(131))));
        assertEquals("et porro tempora", jsonPath.getString("title[3]"));
        assertEquals("ipsam aperiam voluptates qui", jsonPath.getString("title[-1]"));


    }

}
