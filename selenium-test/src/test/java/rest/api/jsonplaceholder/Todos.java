package rest.api.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class Todos {

    private static String apiEndpoint = "https://jsonplaceholder.typicode.com/todos/1";

    public static void get() {
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/Todos;charset=utf-8")
                .when()
                .get(apiEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
        String rs = response.getBody().asString();
        System.out.println("Response: " + rs);
        int id = response.jsonPath().getInt("id");
        Assert.assertEquals(id, 1, "Id should be 1");
    }

    public static void main(String[] args) {
        get();
    }
}