package stepDefinitions.configurationSteps;

import commons.configuration.ConfigurationProperties;
import commons.configuration.PropertiesLoader;
import commons.driver.BrowserType;
import commons.driver.manager.DriverManager;
import commons.driver.manager.DriverUtils;
import commons.utils.VideoMaker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.NoSuchSessionException;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static commons.configuration.navigation.ApplicationURLs.APPLICATION_URL;

public class Hook {
    final TestContext context;
    private static int testCaseCount = 1;
    String env;
    String chromeBrowser;
    String firefoxBrowser;
    String edgeBrowser;
    String operaBrowser;
    String safariBrowser;
    public Hook(TestContext testContext){
        context = testContext;
    }
    @Before
    public void beforeClass(Scenario scenario) {
        System.out.println(scenario.getUri().toString());

        env = System.getProperty("env");
        System.out.println("Environment type: " + env);

        chromeBrowser = System.getProperty("chrome");
        firefoxBrowser = System.getProperty("firefox");
        edgeBrowser = System.getProperty("edge");
        operaBrowser = System.getProperty("opera");
        safariBrowser = System.getProperty("safari");

        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getPropertiesFromFile("configuration.properties");
        ConfigurationProperties.setProperties(propertiesFromFile);

        System.out.println("Starting test case number " + testCaseCount);
        testCaseCount++;
        String scenarioNameUri = scenario.getUri().toString();
        String scenarioName = scenario.getName();
        Collection<String> tags = scenario.getSourceTagNames();
        List<String> tagList = List.copyOf(tags);
        String processTag = tagList.get(0);
        String fileName = Paths.get(scenario.getUri()).getFileName().toString();
        String scenarioFullName = processTag + ": " + fileName + ": " + scenarioName;
        DriverManager.setScenarioName(scenarioFullName);
        if (chromeBrowser != null && scenarioNameUri.contains("CHROME")) {
            DriverManager.setWebDriver(BrowserType.CHROME);
        } else if (firefoxBrowser != null && scenarioNameUri.contains("FIREFOX")) {
            DriverManager.setWebDriver(BrowserType.FIREFOX);
        } else if (edgeBrowser != null && scenarioNameUri.contains("EDGE")) {
            DriverManager.setWebDriver(BrowserType.EDGE);
        } else if (operaBrowser != null && scenarioNameUri.contains("OPERA")) {
            DriverManager.setWebDriver(BrowserType.OPERA);
        } else if (safariBrowser != null && scenarioNameUri.contains("SAFARI")) {
            DriverManager.setWebDriver(BrowserType.SAFARI);
        } else if (chromeBrowser == null && firefoxBrowser == null && edgeBrowser == null && operaBrowser == null && safariBrowser == null) {
            DriverManager.setWebDriver(BrowserType.FIREFOX);
        }
        context.setSessionId(DriverManager.getSessionId());
        DriverManager.getWebDriver();
        DriverManager.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        DriverUtils.setInitialConfiguration();
        if (env == null) {
            DriverUtils.navigateToPage(APPLICATION_URL);
        } else {
            DriverUtils.navigateToPage(env);
        }
    }

    @After(order = 1)
    public void after(Scenario scenario) {
        try{
            DriverManager.disposeDriver();
        } catch (NoSuchSessionException e){
            e.printStackTrace();
        }
        if (scenario.isFailed()) {
            VideoMaker.selenoidVideoAttach(context.getSessionId());
        }
    }
}