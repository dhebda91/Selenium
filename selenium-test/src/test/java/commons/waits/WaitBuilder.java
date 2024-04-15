package commons.waits;

import commons.driver.manager.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitBuilder {
    public static WaitConditions waitLong() {
        return new WaitConditions(new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(35)));
    }

    public static WaitConditions waitShort() {
        return new WaitConditions(new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(1)));
    }

    public static WaitConditions waitDefaultTime() {
        return new WaitConditions(new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10)));
    }

    public static WaitConditions waitForXTime(int timeToWaitInSec) {
        return new WaitConditions(new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(timeToWaitInSec)));
    }
}