package myExercises;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class TodosPojo01 extends JsonPlaceHolderTestBase {

    /*
{"userId": 1,
        "id": 201,
        "title": "delectus aut autem",
        "completed": false
}


     */
    @Test
    public void test () {

        TodosPojoTest todosPojoTest = new TodosPojoTest(1 , 201, "delectus aut autem" , false);
        Response response = given().spec(spec01).auth()
                .basic("admin" , "password123").contentType(ContentType.JSON)
                .body(todosPojoTest).when().post("/todos");

        response.then().assertThat().statusCode(201);
        response.prettyPrint();

        JsonPath json = response.jsonPath();

        assertEquals(json.getInt("userId") , todosPojoTest.getUserId());
        assertEquals(json.getInt("id") , todosPojoTest.getId());
        assertEquals(json.getString("title") , todosPojoTest.getTitle());
        assertEquals(json.getBoolean("completed") , todosPojoTest.getCompleted());

    }
}
