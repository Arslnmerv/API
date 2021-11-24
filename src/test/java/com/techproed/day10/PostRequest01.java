package com.techproed.day10;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PostRequest01 extends DummyTestBase {
   /* http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
    {
        "name":"Ahmet Aksoy",
               "salary":"1000",
               "age":"18",
               "profile_image": ""
    }
    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    {
       "status": "success",
               "data": {
            “id”:…
       },
       "message": "Successfully! Record has been added."
    }
    olduğunu test edin */

    @Test
    public void test() {
        spec03.pathParam("first", "create");

        DummyTestData object = new DummyTestData();


        //post request yaparken biz body gondermek zorundayiz , test data clasinda olusturdugumuz request
        //bodyi burada cagiriyoruz.

        HashMap<String, String> requestBodymap = object.setuprequestBody();
        HashMap<String, Object> expectedDataMap = object.setUpExpecteddata();

        Response response = given().
                accept("application/json").
                spec(spec03).auth().basic("admin" , "password123")
                .body(requestBodymap)
                .when().
                post("/{first}");

        response.prettyPrint();

        //w/de-de-serialization

        HashMap <String , Object > actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("statusCode") , response.getStatusCode());
        assertEquals(expectedDataMap.get("status") , actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("message") , actualDataMap.get("message"));

        //w/jsonpath

        JsonPath json = response.jsonPath();
        assertEquals(expectedDataMap.get("status") , json.get("status"));
        assertEquals(expectedDataMap.get("message") , json.get("message"));

    }
}
