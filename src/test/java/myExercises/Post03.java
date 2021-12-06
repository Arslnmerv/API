package myExercises;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Post03 extends HerokuAppTestBase {

    @Test
    public void test () {

        Bookingdates bookingdates = new Bookingdates("2013-02-23","2014-10-23");
        Booking booking = new Booking("Sally","Brown",111,true,bookingdates,"Breakfast");

        Response response = given().contentType(ContentType.JSON).spec(spec02).auth().basic("admin" , "password123").body(booking).when().post("/booking");

        response.prettyPrint();

       response.then().assertThat().statusCode(200);

        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getString("booking.firstname"), booking.getFirstname());
        softAssert.assertEquals(json.getString("booking.lastname"), booking.getLastname());
        softAssert.assertEquals(json.getInt("booking.totalprice"), booking.getTotalprice());
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),booking.getDepositpaid());
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), booking.getBookingdates().getCheckin());
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), booking.getBookingdates().getCheckout());
        softAssert.assertEquals(json.getString("booking.additionalneeds"), booking.getAdditionalneeds());

        softAssert.assertAll();

    }
}
