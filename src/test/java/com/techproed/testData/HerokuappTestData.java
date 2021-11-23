package com.techproed.testData;

import org.json.JSONObject;

import java.util.HashMap;

public class HerokuappTestData  {

    public HashMap<String, Object> setUpTestData() {

        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2016-09-09");
        bookingdates.put("checkout", "2017-09-21");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Eric");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 555);
        expectedData.put("depositpaid", false);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;

    }

    public JSONObject setUpTestAndRequestData () {

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin" , "2021-01-05");
        bookingdates.put("checkout" , "2021-01-10");

        JSONObject  expectedRequest = new JSONObject();
        expectedRequest.put("firstname", "batch30");
        expectedRequest.put("lastname", "bitti");
        expectedRequest.put("totalprice", 123);
        expectedRequest.put("depositpaid", false);
        expectedRequest.put("bookingdates", bookingdates);

        return expectedRequest;

    }


}
