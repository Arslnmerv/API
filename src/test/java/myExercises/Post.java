package myExercises;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Post  extends HerokuAppTestBase {

    @Test
    public void test () {

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin" , "2020-05-02");
        bookingDatesMap.put("checkout" , "2020-05-05");

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("firstname" , "Suleyman");
        requestBodyMap.put("lastname" , "Alptekin");
        requestBodyMap.put("totalprice" , 123);
        requestBodyMap.put("depositpaid" , true);
        requestBodyMap.put("bookingdates" , bookingDatesMap);
        requestBodyMap.put("additionalneeds" , "wifi");

        Response response = given().
                                  spec(spec02).
                                  contentType(ContentType.JSON).
                                  auth().
                                  basic("admin", "password123").
                                  body(requestBodyMap).
                                  post("/booking");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("booking.firstname"),"Suleyman");
        softAssert.assertEquals(json.getString("booking.lastname"), "Alptekin");
        softAssert.assertEquals(json.getInt("booking.totalprice"), 123);
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),true);
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2020-05-02");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),"2020-05-05");
        softAssert.assertEquals(json.getString("booking.additionalneeds"),"wifi");

        softAssert.assertAll();

    }
}
