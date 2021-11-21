package com.techproed.day09;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest13JsonPath extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
       Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
      Sondan 2. çalışanın maaşının 106450 olduğunu
      40,21 ve 19 yaslarında çalışanlar olup olmadığını
      11. Çalışan bilgilerinin
    {
 “id”:”11”
        "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
} gibi olduğunu test edin.
*/

    @Test
    public void test() {
        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        //w/sonpath

        JsonPath json=response.jsonPath();

        assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("besincicalisan"),json.getString("data[4].employee_name"));
        assertEquals(expectedDataMap.get("calisansayisi"),json.getList("data.id").size());
        assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"),json.getInt("data[-2].employee_salary"));
        assertTrue(json.getList("data.employee_age").containsAll((List) expectedDataMap.get("arananyaslar")));
        assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("id"),
                json.getInt("data[10].id"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                json.getString("data[10].employee_name"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                json.getInt("data[10].employee_salary"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                json.getInt("data[10].employee_age"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                json.getString("data[10].profile_image"));

    }

}