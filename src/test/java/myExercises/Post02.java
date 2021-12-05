package myExercises;


import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Post02 extends HerokuAppTestBase {
    @Test
    public void test() {

        String jsonRequrstBody2 = "{\n" +
                " \"firstname\": \"Merve\",\n" +
                " \"lastname\": \"Yildirim\",\n" +
                " \"totalprice\": 121,\n" +
                " \"depositpaid\": true,\n" +
                " \"bookingdates\": {\n" +
                " \"checkin\": \"2021-09-09\",\n" +
                " \"checkout\": \"2021-09-21\"\n" +
                "},\n" +
                "\"additionalneeds\":\"Wifi\"\n" +
                " }";

        Response response = given().
                spec(spec02).
                contentType(ContentType.JSON).
                auth().
                basic("admin", "password123").
                body(jsonRequrstBody2).
                post("/booking");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();
        assertEquals(json.getString("booking.firstname"), "Merve");
        assertEquals(json.getString("booking.lastname"), "Yildirim");
        assertEquals(json.getInt("booking.totalprice"), 121);
        assertEquals(json.getBoolean("booking.depositpaid"), true);
        assertEquals(json.getString("booking.bookingdates.checkin"), "2021-09-09");
        assertEquals(json.getString("booking.bookingdates.checkout"), "2021-09-21");
        assertEquals(json.getString("booking.additionalneeds"), "Wifi");

    }
}
