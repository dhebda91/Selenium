package commons.configuration;

import commons.driver.BrowserType;

public class TestRunProperties {

    public static String getGridUrl() {
        return ConfigurationProperties.getProperties().getProperty("grid.url");
    }

    public static BrowserType getBrowserToRun() {
        return BrowserType.valueOf(ConfigurationProperties.getProperties().getProperty("browser"));
    }

    public static boolean getIsRemoteRun(){
        return Boolean.parseBoolean(ConfigurationProperties.getProperties().getProperty("is.remote.run"));
    }

    public static String getDownloadDirectoryPath(){
        return ConfigurationProperties.getProperties().getProperty("download.directory.path");
    }

    public static String getAllureResultsDirectoryPath(){
        return  ConfigurationProperties.getProperties().getProperty("allure.results");
    }
}