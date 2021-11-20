package myExercises;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class M0 extends TestBaseDummy {
     /*   http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
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
} gibi olduğunu test edin. */

    @Test
    public void test() {
        spec04.pathParam("first", "employees");
        Response response = given().accept("application/json").spec(spec04).when().get("/{first}");
       // response.prettyPrint();
        TestDataDummy tsd = new TestDataDummy();
        HashMap<String,Object> expectedDataMap=tsd.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);
       // response.prettyPrint();

        HashMap <String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
       assertEquals(expectedDataMap.get("statusCode") , response.statusCode());
       assertEquals(expectedDataMap.get("besinciCalisan") , ((Map)actualDataMap.get("data")).get("employee_name"));

    }


}

class TestBaseDummy {
    protected RequestSpecification spec04;

    @Before
    public void Setup() {
        spec04 = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1/").build();
    }
}

class TestDataDummy {

    public HashMap <String , Object> setUpTestData () {

        List<Integer> ageList = new ArrayList<Integer>();
        ageList.add(40);
        ageList.add(21);
        ageList.add(19);

        HashMap<String , Object> onBirinci= new HashMap<>();
        onBirinci.put("id" , 1);
        onBirinci.put("employee_name" , "Jena Gaines");
        onBirinci.put("employee_salary" , 90560);
        onBirinci.put("employee_age" , 30);
        onBirinci.put("profile_image" , "");

        HashMap <String , Object> expectedData = new HashMap<>();
        expectedData.put("statusCode" , 200);
        expectedData.put("besinciCalisan" , "Airi Satou");
        expectedData.put("calisanSayisi" , 24);
        expectedData.put("sondanIkincicalisanMaasi" , 106450);
        expectedData.put("arananYaslar" , ageList);
        expectedData.put("onBirinciCalisan" , onBirinci);

        return expectedData;

    }


}