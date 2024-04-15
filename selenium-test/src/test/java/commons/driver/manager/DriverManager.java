package commons.driver.manager;

import commons.driver.BrowserFactory;
import commons.driver.BrowserType;
import commons.driver.listeners.WebDriverEventListenerRegistrar;
import org.openqa.selenium.WebDriver;

import static commons.configuration.TestRunProperties.getBrowserToRun;
import static commons.configuration.TestRunProperties.getIsRemoteRun;

public class DriverManager {

    //Dwie zmiennej instancji klasy ThreadLocal przechowujące kolejno instancję obiektu WebDriver oraz BrowserType dla danego wątku
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserType> browserTypeThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> sessionIdThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> scenarioName = new ThreadLocal<>();

    private DriverManager() {
    }

    //Metoda służy od ustawiania typu przeglądarki dla danego wątku
    public static void setWebDriver(BrowserType browserType){

        //Obiekt typu WebDriver, który w kolejnych liniach zostanie zainicjalizowany wywołaniem metody getBrowser() z klasy BrowserFactory
        WebDriver browser = null;

        //Jeśli obiekt browserType będzie nullem, wtedy dla danego wątku zostanie wybrana przeglądarka zdefiniowana w pliku configuration.properties
        if(browserType == null){

            //Utworzenie instancji WebDrivera dla opcji gdy browserType jest nullem Zostanie wtedy wybrana przeglądarka zdefiniowana w pliku configuration.properties
            browserType = getBrowserToRun();

            browserType = getBrowserToRun();
            browser = new BrowserFactory(browserType, getIsRemoteRun()).getBrowser();
        } else {
            //Utworzenie instancji WebDrivera dla opcji gdy browserType nie jest nullem
            //To znaczy, że został on zdefiniowany w pliku TestNG XML i możemy go używać
            BrowserFactory browserFactory = new BrowserFactory(browserType, getIsRemoteRun());
            if(scenarioName.get()!=null){
                browserFactory.setScenarioName(scenarioName.get());
            }
            browser = browserFactory.getBrowser();
            sessionIdThreadLocal.set(browserFactory.getSessionId());
        }

        //Rejestracja obiektu WebDrivera
        browser = WebDriverEventListenerRegistrar.registerWebDriverEventListener(browser);

        //Dodanie do puli instancji ThreadLocal za pomocą metody set() instancji klasy BrowserType
        browserTypeThreadLocal.set(browserType);

        //Dodanie do puli instancji ThreadLocal za pomocą metody set() instancji klasy WebDriver
        webDriverThreadLocal.set(browser);
    }
    public static WebDriver getWebDriver() {

        if (webDriverThreadLocal.get() == null) {
            //Rzucenie wyjątku IllegalStateException w sytuacji gdy dla danego wątku instancja przeglądarki nie została
            // poprawnie zainicializowana metodą setWebDriver
            throw new IllegalStateException("WebDriver Instance was null! Please create instance of WebDriver using setWebDriver!");
        }
        return webDriverThreadLocal.get();
    }

    public static void disposeDriver() {
//        webDriverThreadLocal.get().close();

        //Sprawdzenie czy dla danego wątku przeglądarka to Firefox
//        if (!browserTypeThreadLocal.get().equals(FIREFOX)) {
            webDriverThreadLocal.get().quit();
//        }
        //Usunięcie zmiennych typu BrowserType oraz WebDriver dla danego wątku
        webDriverThreadLocal.remove();
        browserTypeThreadLocal.remove();
    }
    public static void deleteCookies(){
        getWebDriver().manage().deleteAllCookies();
    }

    public static String getSessionId() {
        return sessionIdThreadLocal.get();
    }

    public static void setSessionIdThreadLocal(ThreadLocal<String> sessionIdThreadLocal) {
        DriverManager.sessionIdThreadLocal = sessionIdThreadLocal;
    }

    public static ThreadLocal<String> getScenarioName() {
        return scenarioName;
    }

    public static void setScenarioName(String scenarioName) {
        DriverManager.scenarioName.set(scenarioName);
    }
}