package rest.api;

import static io.restassured.RestAssured.given;

public class AllureDockerServiceApi {

    public static void cleanResults() {
        given()
                .when()
                .get("http://localhost:5050/allure-docker-service/clean-results")
                .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();
    }

    public static void cleanServerResults() {
        given()
                .when()
                .get("http://10.10.100.104:5050/allure-docker-service/clean-results?project_id=Express")
                .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();
    }

    public static void generateReport() {
        given()
                .when()
                .get("http://localhost:5050/allure-docker-service/generate-report")
                .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();
    }
}