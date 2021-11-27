package com.techproed.testData;
import org.json.JSONObject;

import java.util.*;


public class DummyTestData {

    public HashMap <String , Object> setUpTestData () {

        List  <Integer> ageList = new ArrayList<Integer>();
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

 /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */

    public HashMap<String, Integer> setUpTestData02(){

        HashMap<String,Integer> expectedData=new HashMap<String, Integer>();
        expectedData.put("statusCode",200);
        expectedData.put("enYuksekMaas",725000);
        expectedData.put("enKucukYas",19);
        expectedData.put("ikinciYuksekMaas",675000);
        return expectedData;


    }

    public  HashMap <String , String> setuprequestBody () {

        HashMap <String , String> requestBody = new HashMap<>();
        requestBody.put("name" , "batch30");
        requestBody.put("salary" , "123000");
        requestBody.put("age" , "20");

        return requestBody;

    }

    public HashMap <String , Object> setUpExpecteddata () {

//        HashMap <String , Object> data = new HashMap<>();
//         data.put("name" , "batch30");
//         data.put("salary" , "123000");
//         data.put("age" , "20");

        HashMap <String , Object> expecteddata = new HashMap<>();
        expecteddata.put("statusCode" ,200);
        expecteddata.put("status" ,"success");
       // expecteddata.put("data" ,data);
        expecteddata.put("message" , "Successfully! Record has been added.");

        return expecteddata;
    }
    public JSONObject setUpDeleteExpectedData(){
        /*
        {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
         */

        JSONObject expectedData=new JSONObject();

        expectedData.put("status", "success");
        expectedData.put("data", "2");
        expectedData.put("message", "Successfully! Record has been deleted");
        return expectedData;


    }
}




