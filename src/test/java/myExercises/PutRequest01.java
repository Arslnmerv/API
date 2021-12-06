package myExercises;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.*;

public class PutRequest01 extends JsonPlaceHolderTestBase {

    @Test
    public void put02 () {

        Response response = given().spec(spec01).when().get("/todos/198");
        response.prettyPrint();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Suleyman");
        jsonObject.put("userId", 150);
        jsonObject.put("id", 198);
        jsonObject.put("completed", true);

        Response responseAfterPut = given().when().
                spec(spec01).contentType(ContentType.JSON)
                .body(jsonObject.toString()).put("/todos/198");

        responseAfterPut.prettyPrint();

        responseAfterPut.then().assertThat().statusCode(200);

        JsonPath json = responseAfterPut.jsonPath();
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(json.getBoolean("completed") , jsonObject.get("completed"));
        softAssert.assertEquals(json.getString("title") , jsonObject.get("title"));
        softAssert.assertEquals(json.getInt("userId") , jsonObject.get("userId"));
        softAssert.assertEquals(json.getInt("id") , jsonObject.get("id"));

        softAssert.assertAll();
    }




}
