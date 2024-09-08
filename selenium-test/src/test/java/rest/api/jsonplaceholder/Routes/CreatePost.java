package rest.api.jsonplaceholder.Routes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import rest.api.jsonplaceholder.JsonCreator;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class CreatePost {
    JsonCreator jsonCreator;

    public Response post() throws IOException {
        jsonCreator = new JsonCreator();
        jsonCreator.prepareJsonCarsSection();
        FileInputStream fileInputStream = new FileInputStream(jsonCreator.path);
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8))
                        .when()
                        .post("/posts")
                        .then()
                        .extract()
                        .response();
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
        return response;
    }

    public void checkResponseStatus(Response response) {
        Assert.assertEquals(response.statusCode(), 201);
    }

    public void checkData(Response response) {
        // Asercje
        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title, jsonCreator.title, "Title does not match the expected one.");
        String body = response.jsonPath().getString("body");
        Assert.assertEquals(body, jsonCreator.body, "Body does not match the expected one.");
        int userId = response.jsonPath().getInt("userId");
        Assert.assertEquals(userId, jsonCreator.userId, "UserId does not match the expected one.");
    }

    public static void main(String[] args) throws IOException {
        CreatePost createPost = new CreatePost();
        createPost.post();
    }
}