package myExercises;

import com.techproed.testBase.DummyTestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class M4 extends DummyTestBase {

   /* http://dummy.restapiexample.com/api/v1/employee/3 url'ine bir GET request gonderdigimizde
    donen response'un asagidaki gibi oldugunu test edin.
    Response Body

    {
        "status":"success",
            "data":{
        "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
    },
        "message":"Successfully! Record has been fetched."
    }   */

    @Test
    public void test() {

        spec03.pathParams("first", "employee", "second", 3);
        Response response = given().spec(spec03).when().get("/{first}/{second}");

       Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("id", 3);
        expectedData.put("employee_name", "Ashton Cox");
        expectedData.put("employee_salary", 86000);
        expectedData.put("employee_age", 66);
        expectedData.put("profile_image", "");
        expectedData.put("message", "Successfully! Record has been fetched.");

        response.then().assertThat().statusCode((int) expectedData.get("statusCode"))
                .body("data.id", equalTo(expectedData.get("id"))
                        , "data.employee_name", equalTo(expectedData.get("employee_name"))
                        , "data.employee_salary", equalTo(expectedData.get("employee_salary"))
                        , "data.employee_age", equalTo(expectedData.get("employee_age"))
                        , "data.profile_image", equalTo(expectedData.get("profile_image"))
                        , "message", equalTo(expectedData.get("message")));

    }
}



