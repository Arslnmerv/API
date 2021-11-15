package com.techproed.day05;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GetRequest06 extends JsonPlaceHolderTestBase {
    //https://jsonplaceholder.typicode.com/todos/123 url'ine
    //   accept type'i "application/json" olan GET request'i yolladigimda
    //   gelen response’un
    //  status kodunun 200
    //    ve content type'inin "application/json"
    //  ve Headers'daki "Server" in "cloudflare"
    //  ve response body'deki "userId"'nin 7
    //  ve "title" in "esse et quis iste est earum aut impedit"
    //  ve "completed" bolumunun false oldugunu test edin

    @Test
    public void test() {

        spec01.pathParams("parametre1", "todos", "parametre2", 123);

        Response response = given().accept("application/json").spec(spec01).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        response.then()
                .assertThat().statusCode(200)
                .contentType("application/json")
                .header("Server", equalTo("cloudflare"))
                .body("userId", equalTo(7),
                        "title", equalTo("esse et quis iste est earum aut impedit")
                        , "completed", equalTo(false));

        // w/jsonpath

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals("7", jsonPath.getString("userId"));
        assertEquals("esse et quis iste est earum aut impedit", jsonPath.getString("title"));
        assertEquals("false", jsonPath.getString("completed"));

    }
}
