package runner;

import commons.ImageManager;
import commons.helpers.FileManagingHelper;
import commons.driver.BrowserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import rest.api.AllureDockerServiceApi;

import java.io.File;
import java.util.Objects;

public class SuiteListener implements ISuiteListener {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    protected Logger log() {
        return logger;
    }

    String chromeBrowser;
    String firefoxBrowser;
    String edgeBrowser;
    String operaBrowser;
    String safariBrowser;
    String env;

    @Override
    public void onStart(ISuite suite) {
        AllureEnvironmentSection.createEnvFile();
//        FileManagingHelper.deleteDirectory(new File("src/test/resources/features/features-runtime"));
//        FileManagingHelper.deleteWildcardFiles("src/test/resources/RQ", "*Todos");

        env = System.getProperty("env");
        AllureEnvironmentSection.addEnvVariable("env", env);
        if (env != null) {
            AllureDockerServiceApi.cleanServerResults();
            log().debug("Clean server results done");
        } else {
//            AllureDockerServiceApi.cleanResults();
        }
        FileManagingHelper.fileCopier("src/test/resources","allure-results","categories.json");

        chromeBrowser = System.getProperty("chrome");
        System.out.println("Tests in the Chrome browser: " + chromeBrowser);

        firefoxBrowser = System.getProperty("firefox");
        System.out.println("Tests in the Firefox browser: " + firefoxBrowser);

        edgeBrowser = System.getProperty("edge");
        System.out.println("Tests in the Edge browser: " + edgeBrowser);

        operaBrowser = System.getProperty("opera");
        System.out.println("Tests in the Opera browser: " + operaBrowser);

        safariBrowser = System.getProperty("safari");
        System.out.println("Tests in the Safari browser: " + safariBrowser);

        FeaturesCopier featuresCopier = new FeaturesCopier();
        if (chromeBrowser != null && chromeBrowser.contains("true")) {
            featuresCopier.addBrowserType(BrowserType.CHROME);
        }
        if (firefoxBrowser != null && firefoxBrowser.contains("true")) {
            featuresCopier.addBrowserType(BrowserType.FIREFOX);
        }
        if (edgeBrowser != null && edgeBrowser.contains("true")) {
            featuresCopier.addBrowserType(BrowserType.EDGE);
        }
        if (operaBrowser != null && operaBrowser.contains("true")) {
            featuresCopier.addBrowserType(BrowserType.OPERA);
        }
        if (safariBrowser != null && safariBrowser.contains("true")) {
            featuresCopier.addBrowserType(BrowserType.SAFARI);
        }
        if (chromeBrowser == null && firefoxBrowser == null && edgeBrowser == null && operaBrowser == null && safariBrowser == null) {
            featuresCopier.addBrowserType(BrowserType.CHROME);
        }
        featuresCopier.copyTemplatesFeatures();
        try {
//            DeletePublishedOffers.deleteOffers();
        } catch (Exception e) {
            log().error("Usuwanie ofert zakończone niepowodzeniem" + System.lineSeparator() + e.getMessage());
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        String env = System.getProperty("env");
        String suiteName = suite.getName();
        FileManagingHelper.deleteDirectory(new File("src/test/resources/features/features-runtime"));
//        FileManagingHelper.deleteWildcardFiles("src/test/resources/RQ", "*Todos");
//        FileManagingHelper.deleteWildcardFiles("videos", "*mp4");
        if (Objects.equals(suiteName, "Testy Express")) {
            ImageManager imageManager = new ImageManager();
            imageManager.resizeAttachments();
            if (env == null) {
                AllureDockerServiceApi.generateReport();
                AllureDockerServiceApi.cleanResults();
            } else {
                AllureDockerServiceApi.cleanServerResults();
            }
        }
    }

}