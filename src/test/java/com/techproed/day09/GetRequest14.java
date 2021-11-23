package com.techproed.day09;

import com.google.gson.annotations.JsonAdapter;
import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest14 extends DummyTestBase {

    @Test
    public void test() {
        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Integer> expectedDataMap = expectedObje.setUpTestData02();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        //response.prettyPrint();

        //w/de-serialization
        HashMap <String , Object> actualDataMap = response.as(HashMap.class);

       // System.out.println("actualDataMap = " + actualDataMap);
        //Status kodun 200 olduğunu,
        assertEquals(expectedDataMap.get("statusCode") , (Integer)response.getStatusCode());


        //En yüksek maaşın 725000 olduğunu,
        List <Integer> maasListesi = new ArrayList<Integer>();
        int datasize= ((List) actualDataMap.get("data")).size();


        for (int i = 0; i < datasize; i++) {
           maasListesi.add ((Integer)((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));

        }

        Collections.sort(maasListesi);
        assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(datasize-1));


        //İkinci en yüksek maaşın 675000
        assertEquals(expectedDataMap.get("ikinciYuksekMaas"), maasListesi.get(maasListesi.size()-2));

        //En küçük yaşın 19 olduğunu,
        List <Integer> yasListesi = new ArrayList<Integer>();

        for (int i = 0; i < datasize; i++) {
            yasListesi.add ((Integer)((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));

        }
        Collections.sort(yasListesi);
        assertEquals(expectedDataMap.get("enKucukYas"),yasListesi.get(0));


        //w/jsonpath

        JsonPath json = response.jsonPath();

        //En yüksek maaşın 725000 olduğunu,

        List <Integer> maasListesijson = json.getList("data.employee_salary");
        Collections.sort(maasListesijson);
        assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesijson.get(maasListesijson.size()-1));

        //İkinci en yüksek maaşın 675000
        assertEquals(expectedDataMap.get("ikinciYuksekMaas"), maasListesijson.get(maasListesijson.size()-2));

        //En küçük yaşın 19 olduğunu,
        List <Integer> yasListesiJson = json.getList("data.employee_age");
        Collections.sort(yasListesiJson);
        assertEquals(expectedDataMap.get("enKucukYas") , yasListesiJson.get(0));




    }

}
