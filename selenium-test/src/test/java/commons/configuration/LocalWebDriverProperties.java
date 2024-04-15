//– klasa będzie dostarczała właściwości związanych z ścieżkami do plików *.exe oraz przeglądarki, na której ma być uruchomiony test

package commons.configuration;


import commons.driver.BrowserType;

public class LocalWebDriverProperties {

    public static BrowserType getLocalBrowser() {
        return BrowserType.valueOf(ConfigurationProperties.getProperties().getProperty("local.browser"));
    }

    public static String getChromeWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("chrome.driver.location");
    }

    public static String getFirefoxWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("firefox.driver.location");
    }

    public static String getInternetExplorerWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("ie.driver.location");
    }

    public static String getSafariWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("safari.driver.location");
    }
}