package com.techproed.day08;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuappTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest12 extends HerokuAppTestBase {

   /*  https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
    dönen response body nin
    {
                    "firstname": "Eric",
                    "lastname": "Smith",
                    "totalprice": 555,
                    "depositpaid": false,
                    "bookingdates": {
                            "checkin": "2016-09-09",
                             "checkout": "2017-09-21"
 }
    } gibi olduğunu test edin                     */

    @Test
    public void test() {

        spec02.pathParams("first", "booking", "second", 1);
        Response response = given().accept("application/json").spec(spec02).when().get("/{first}/{second}");

        //expected data
        HerokuappTestData expectedObject = new HerokuappTestData();
        Map<String, Object> expectedDataMap = expectedObject.setUpTestData();
        System.out.println("expectedData = " + expectedDataMap);

        //actual data
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualData = " + actualDataMap);

        assertEquals(expectedDataMap.get("firstname") , actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname") , actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice") , actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid") , actualDataMap.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin") , ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout") , ((Map) actualDataMap.get("bookingdates")).get("checkout"));

        //w/jsonpath
        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedDataMap.get("firstname") , jsonPath.getString("firstname"));
        assertEquals(expectedDataMap.get("lastname") , jsonPath.getString("lastname"));
        assertEquals(expectedDataMap.get("totalprice") , jsonPath.getString("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid") , jsonPath.getString("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin") , jsonPath.get("bookingdates.checkin"));
        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout") , jsonPath.get("bookingdates.checkout"));





    }

}
