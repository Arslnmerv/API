package myExercises;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class M1 {
    /*
     https://restful-booker.herokuapp.com/booking/10 url'ine bir GET request gonderdigimizde donen Response'un,
            status code'unun 200,
            ve content type'inin application/json; charset=utf-8,
            ve Server isimli Header'in degerinin Cowboy,
            ve status Line'in HTTP/1.1 200 OK
            ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz.
     */
    @Test
    public void test() {

        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = given().accept("application/json").when().get(url);

        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8").headers("Server","Cowboy").statusLine("HTTP/1.1 200 OK");
        Assert.assertTrue(response.time()<5000);

    }
}
