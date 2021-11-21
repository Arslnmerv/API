package com.techproed.day08;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest13 extends DummyTestBase {

    @Test
    public void test() {

        //  http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        //response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualData = " + actualDataMap);

        //    Status kodun 200 olduğunu,
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        //    5. Çalışan isminin "Airi Satou" olduğunu ,
        assertEquals(expectedDataMap.get("besinciCalisan"), ((Map) ((List) actualDataMap.get("data")).get(4)).get("employee_name"));

        //    çalışan sayısının 24 olduğunu,
        assertEquals(expectedDataMap.get("calisanSayisi"), ((List<?>) actualDataMap.get("data")).size());

        //    Sondan 2. çalışanın maaşının 106450 olduğunu
        int datasize = ((List) actualDataMap.get("data")).size();
        assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(datasize - 2)).get("employee_salary"));

        //   40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> actualYasListesi = new ArrayList<Integer>();
        for (int i = 0; i < datasize; i++) {
            actualYasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        assertTrue(actualYasListesi.containsAll((List) expectedDataMap.get("arananyaslar")));

        /*
        11. Çalışan bilgilerinin
        {
 “id”:”11”
            "employee_name": "Jena Gaines",
                "employee_salary": "90560",
                "employee_age": "30",
                "profile_image": "" }
    } gibi olduğunu test edin.*/

        assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));

    }
}
