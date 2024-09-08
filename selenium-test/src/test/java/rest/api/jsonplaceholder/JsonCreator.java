package rest.api.jsonplaceholder;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonCreator {
    public String title = "Hello World";
    public String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public int userId = 1;
    public String path;

    public void prepareJsonCarsSection() {
        JSONObject post = new JSONObject()
                .put("title", title)
                .put("body", body)
                .put("userId", userId);
        try {
            // TODO zmienić na ścieżkę absolutną
            path = "/home/dhebda/Pulpit/AutomaticTests/tests/selenium-test/src/test/resources/RQ/post_" + System.identityHashCode(this) + ".json";
            FileWriter file = new FileWriter(path);
            file.write(post.toString());
            file.close();
            System.out.println("JSON file created: " + post);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JsonCreator creator = new JsonCreator();
        creator.prepareJsonCarsSection();
    }
}