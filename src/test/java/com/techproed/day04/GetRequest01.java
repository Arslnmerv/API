package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest01 {
/*
https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
HTTP status kodunun 200
Content Type'in Json
Ve Status Line'in HTTP/1.1 200 OK
Oldugunu test edin
 */

    @Test
            public void test01 () {

        //1- api testi yaparken ilk olarak (endpoint) url belirlenmeli

        String url = "https://restful-booker.herokuapp.com/booking/3 ";

        //2- beklenen sonuc (expected result) olusturulur.

        //  bu casede benden body dogrulamasi istenmedigi icin simdilik beklenen sonuc  olusturmuyoruz.

        //3-  request gonder

         Response response = given().accept("application/json").when().get(url);
         //accept ("application/json") kullanilabilir
        response.prettyPrint();

        //4- actual result olustur.

        // response body ile ilgili islem yapmayacagimiz icin simdi olusturmaycagz

        //5- dogrulama yap (assertion)

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        //responsedan donen status kodu verir

        System.out.println("response.getContentType() = " + response.getContentType());
        //responsedan gelen content type'i verir

        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        //responsedan donen status line'i verir

        System.out.println("response.getHeaders() = " + response.getHeaders());

//        Assert.assertEquals(200,response.getStatusCode());
//        //expected kismi bize text olarak verilen degerdir, actual kismi ise responsedan donen degerdir.
//
//        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
//        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
    }
}
