package commons.driver;

public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    OPERA("opera"),
    SAFARI("safari");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }
}

