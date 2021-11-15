package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Mary"
ve lastname'in "Jones"
ve checkin date'in 2018-10-07"
ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void test(){
        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response= given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

       /* response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Sally")).
                body("lastname",Matchers.equalTo("Wilson")).
                body("totalprice",Matchers.equalTo(432)).
                body("depositpaid",Matchers.equalTo(true)).
                body("bookingdates.checkin",Matchers.equalTo("2016-05-14")).
                body("bookingdates.checkout",Matchers.equalTo("2016-07-12")); */


        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Sally"),
                        "lastname", equalTo("Wilson"),
                        "totalprice", equalTo(432),
                        "bookingdates.checkin",equalTo("2016-05-14"),
                        "bookingdates.checkout",equalTo("2016-07-12"));

    }
}
