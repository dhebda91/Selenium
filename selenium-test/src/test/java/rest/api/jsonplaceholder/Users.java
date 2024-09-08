package rest.api.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Users {

    private static String apiEndpoint = "https://jsonplaceholder.typicode.com/users";

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
    }

    public static void main(String[] args) {
        get();
    }
}