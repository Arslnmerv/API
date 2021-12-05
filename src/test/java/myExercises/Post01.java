package myExercises;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Post01 extends HerokuAppTestBase {

    @Test
    public void Test() {

        String jsonRequrstBody = "{\n" +
                " \"firstname\": \"Murat\",\n" +
                " \"lastname\": \"Yildirim\",\n" +
                " \"totalprice\": 123,\n" +
                " \"depositpaid\": false,\n" +
                " \"bookingdates\": {\n" +
                " \"checkin\": \"2020-09-09\",\n" +
                " \"checkout\": \"2021-09-21\"\n" +
                "},\n"+
                "\"additionalneeds\":\"Wifi\"\n"+
                " }";

        Response response = given().contentType(ContentType.JSON).
                spec(spec02).
                auth().
                basic("admin","password123").
                body(jsonRequrstBody).
                when().
                post("/booking");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("booking.firstname"),"Murat");
        softAssert.assertEquals(json.getString("booking.lastname"), "Yildirim");
        softAssert.assertEquals(json.getInt("booking.totalprice"), 123);
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),false);
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2020-09-09");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),"2021-09-21");
        softAssert.assertEquals(json.getString("booking.additionalneeds"),"Wifi");

        softAssert.assertAll();

    }

}
