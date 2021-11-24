package com.techproed.day07;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest11TestData extends JsonPlaceHolderTestBase {
    @Test
    public void test() {

        spec01.pathParams("parametre1", "todos", "parametre2", 2);

        JsonPlaceHolderTestData json = new JsonPlaceHolderTestData();
        HashMap<String, Object> expectedData = (HashMap<String, Object>) json.setUpTestData();
        System.out.println(expectedData);

        Response response = given().accept("application/json").spec(spec01).when().get("/{parametre1}/{parametre2}");

        //response.prettyPrint();

        //w/mathers class
        response.then().assertThat().statusCode((int) expectedData.get("statusCode")).
                headers("via", equalTo(expectedData.get("Via"))
                        , "Server", equalTo(expectedData.get("Server")))
                .body("userId", equalTo(expectedData.get("userId"))
                        , "title", equalTo(expectedData.get("title"))
                        , "completed", equalTo(expectedData.get("completed")));

        //w/jsonpath
        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedData.get("statusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.header("Via"));
        assertEquals(expectedData.get("Server"), response.header("Server"));
        assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        assertEquals(expectedData.get("title"), jsonPath.get("title"));
        assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));

        //w/de-serialization
        HashMap <String , Object > actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("userId") , actualData.get("userId"));
        assertEquals(expectedData.get("title") , actualData.get("title"));
        assertEquals(expectedData.get("completed") , actualData.get("completed"));
















    }
}
