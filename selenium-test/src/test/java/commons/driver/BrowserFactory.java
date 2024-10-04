package commons.driver;


import commons.configuration.LocalWebDriverProperties;
import commons.configuration.TestRunProperties;
import commons.helpers.FileManagingHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;

import static commons.configuration.TestRunProperties.getDownloadDirectoryPath;

public class BrowserFactory {

    private static final String MESSAGE_UNKNOWN_BROWSER = "Unknown browser type! Please check your configuration";

    //Pole określające typ przeglądarki
    private BrowserType browserType;

    //Pole określające czy uruchomienie jest zdalne czy lokalne
    private boolean isRemoteRun;
    private String sessionId;
    private String scenarioName;

    //Konstruktor dla klasy, który ustawia wartości pól browserType or isRemoteRun
    public BrowserFactory(BrowserType browserType, boolean isRemoteRun) {
        this.browserType = browserType;
        this.isRemoteRun = isRemoteRun;
    }

    //Metoda dostarcza obiekt WebDrivera
    public WebDriver getBrowser() {

        //Sprawdzenie czy uruchomienie jest zdalne, jeśli tak to kod wejdzie do warunku
        if (isRemoteRun) {

            //Utworzenie obiektu desiredCapabilities, który jest wymagany do wyboru przeglądarki
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            if(scenarioName!=null){
                desiredCapabilities.setCapability("name", scenarioName);
                desiredCapabilities.setCapability("sessionTimeout", "1m");
            }
            //Wybór przeglądarki w zależności od wartości pola browserType
            switch (browserType) {

                case CHROME:
                    //Do wyboru przeglądarki Chrome należy użyć klasy ChromeOptions
                    ChromeOptions chromeOptions = new ChromeOptions();
                    LoggingPreferences loggingPreferences = new LoggingPreferences();
                    loggingPreferences.enable(LogType.BROWSER, Level.ALL);
                    loggingPreferences.enable(LogType.PERFORMANCE, Level.ALL);
                    chromeOptions.setCapability( "goog:loggingPrefs", loggingPreferences );
                    chromeOptions.setCapability("enableVNC", true);
                    chromeOptions.setCapability("enableVideo", true);
                    chromeOptions.setCapability("screenResolution", "1366x900");
                    chromeOptions.setCapability("videoFrameRate", 8);
                    desiredCapabilities.merge(chromeOptions);
                    return getRemoteWebDriver(desiredCapabilities);

                case FIREFOX:
                    //Do wyboru przeglądarki FireFox należy użyć klasy FireFoxOptions
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("enableVNC", true);
                    firefoxOptions.setCapability("enableVideo", true);
                    firefoxOptions.addArguments("--marionette-port");
                    firefoxOptions.setCapability("screenResolution", "1366x900");
                    firefoxOptions.setCapability("videoFrameRate", 8);
                    desiredCapabilities.merge(firefoxOptions);
                    return getRemoteWebDriver(desiredCapabilities);

                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("enableVNC", true);
                    edgeOptions.setCapability("enableVideo", true);
                    edgeOptions.setCapability("screenResolution", "1366x900");
                    edgeOptions.setCapability("videoFrameRate", 8);
                    desiredCapabilities.merge(edgeOptions);
                    return getRemoteWebDriver(desiredCapabilities);

                case OPERA:
                    OperaOptions operaOptions = new OperaOptions();
                    operaOptions.setCapability("enableVNC", true);
                    operaOptions.setCapability("enableVideo", true);
                    operaOptions.setCapability("screenResolution", "1366x900");
                    operaOptions.setCapability("videoFrameRate", 8);
                    desiredCapabilities.merge(operaOptions);
                    return getRemoteWebDriver(desiredCapabilities);

                case SAFARI:
                    SafariOptions safariOptions = new SafariOptions();
                    safariOptions.setCapability("enableVNC", true);
                    safariOptions.setCapability("enableVideo", true);
                    safariOptions.setCapability("screenResolution", "1366x900");
                    safariOptions.setCapability("videoFrameRate", 8);
                    desiredCapabilities.merge(safariOptions);
                    return getRemoteWebDriver(desiredCapabilities);
                default:
                    throw new IllegalStateException(MESSAGE_UNKNOWN_BROWSER);
            }
            //Jeśli uruchomienie nie jest zdalne, kod wchodzi do else. Jest to uruchomienie lokalne
        } else {
            //Wybór przeglądarki w zależności od wartości pola browserType. Analogicznie jak wyżej
            String downloadDirAbsolutePath = FileManagingHelper.convertStringPathToAbsoluteFilePath(getDownloadDirectoryPath()).toString();
            System.out.println("ŚCIEŻKA DO FOLDER: " + downloadDirAbsolutePath);
            BrowserType browser = TestRunProperties.getBrowserToRun();
            switch (browser) {

                case CHROME:
                    System.setProperty("webdriver.chrome.driver", LocalWebDriverProperties.getChromeWebDriverLocation());
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
//                    options.addArguments("--headless");
                    options.addArguments("--remote-allow-origins=*");
                    HashMap<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory", downloadDirAbsolutePath);
                    options.setExperimentalOption("prefs", prefs);
                    return new ChromeDriver(options);

                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", LocalWebDriverProperties.getFirefoxWebDriverLocation());
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("browser.download.folderList",2);
                    profile.setPreference("browser.download.dir", downloadDirAbsolutePath);
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                    profile.setPreference("browser.download,useDownloadDir", true);
                    profile.setPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
                    firefoxOptions.setProfile(profile);
                    return new FirefoxDriver(firefoxOptions);

                case EDGE:
                    return new EdgeDriver();

                case SAFARI:
                    System.setProperty("webdriver.safari.driver", LocalWebDriverProperties.getSafariWebDriverLocation());
                    SafariOptions options1 = new SafariOptions();
                    options1.setUseTechnologyPreview(true);
                    return new SafariDriver(options1);
                default:
                    throw new IllegalStateException(MESSAGE_UNKNOWN_BROWSER);
            }
        }
    }
            //Metoda zwraca nam obiekt RemoteWebDrivera na podstawie obiektu desiredCapabilities
    private WebDriver getRemoteWebDriver(DesiredCapabilities desiredCapabilities) {
        RemoteWebDriver remoteWebDriver = null;
        //RemoteWebDriver znajduje się w bloku try-catch. Wynika to z faktu, że obiekt URL rzuca wyjątkiem MalformedURLException
        try {
            remoteWebDriver = new RemoteWebDriver(new URL(TestRunProperties.getGridUrl()), desiredCapabilities);
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            sessionId = remoteWebDriver.getSessionId().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create RemoteWebDriver due to: " + e.getMessage());
        }
        return remoteWebDriver;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setScenarioName(String name){
        this.scenarioName = name;
    }
}