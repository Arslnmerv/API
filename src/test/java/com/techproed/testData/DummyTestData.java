package com.techproed.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
