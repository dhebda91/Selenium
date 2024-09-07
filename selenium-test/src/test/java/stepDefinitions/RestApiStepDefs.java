package stepDefinitions;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import rest.api.jsonplaceholder.Routes.CreatePost;
import stepDefinitions.configurationSteps.TestContext;

import java.io.IOException;

public class RestApiStepDefs {
    final CreatePost createPost;
    final TestContext context;
    Response response;

    public RestApiStepDefs(CreatePost createPost, TestContext context) {
        this.createPost = createPost;
        this.context = context;
    }

    @When("UŻYTKOWNIK dodaje post")
    public void użytkownikDodajePost() throws IOException {
        DriverManager.getWebDriver().quit();
        response = createPost.post();
    }

    @When("SYSTEM dodaje post")
    public void systemDodajePost() {
        createPost.checkResponseStatus(response);
    }

    @When("UŻYTKOWNIK sprawdza dodany post")
    public void użytkownikSprawdzaDodanyPost() {
        createPost.checkData(response);
    }
}