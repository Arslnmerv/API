package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {
    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in “Susan"
ve totalprice’in 995
ve checkin date'in "2017-04-30""oldugunu test edin
     */
    @Test
    public void test() {
        String url = "  https://restful-booker.herokuapp.com/booking/5";

        Response response = given().when().accept("application/json").get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .body("firstname", equalTo("Susan"),
                        "totalprice", equalTo(848),
                        "bookingdates.checkin", equalTo("2017-04-30"));


    }
}
