package com.techproed.day05;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;

public class GetRequest05 {

    /*http://dummy.restapiexample.com/api/v1/employees url'ine
                  accept type'i "application/json" olan GET request'i yolladigimda
                  gelen response'un
                  status kodunun 200
                  ve content type'inin "application/json"
                 ve employees sayisinin 24
                 ve employee'lerden birinin "Ashton Cox"
                 ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin*/

    @Test
    public void test() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        Response response = given().when().accept("application/json").get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("application/json").
                body("data.profile_image", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age", hasItems(21, 61, 23));

        //w/jsonpath

        JsonPath jsonPath = response.jsonPath();

        assertEquals(24, jsonPath.getList("data.profile_image").size());
        assertTrue(jsonPath.getString("data.employee_name").contains("Ashton Cox"));
        assertTrue(jsonPath.getList("data.employee_age").stream().allMatch((t -> t.equals(21) && t.equals(61) && t.equals(23))));


    }
}
