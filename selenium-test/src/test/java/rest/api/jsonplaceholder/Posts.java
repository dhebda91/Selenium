package rest.api.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Posts {
    private static String apiEndpoint = "https://jsonplaceholder.typicode.com/posts/";

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