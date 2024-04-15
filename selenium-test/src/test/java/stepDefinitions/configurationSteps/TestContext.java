package stepDefinitions.configurationSteps;

import page.objects.PageObjectManager;

public class TestContext {
    private final PageObjectManager PageObjectManager;
    private String name;
    private String sessionId;
    private String id;
    private String token;
    public TestContext(){
        this.PageObjectManager = new PageObjectManager();
    }

    public PageObjectManager getTestObjectManager(){
        return PageObjectManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String carName) {
        this.name = carName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


}
