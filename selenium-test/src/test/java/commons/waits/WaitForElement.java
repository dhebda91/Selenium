package commons.waits;

import commons.driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class WaitForElement {
    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10));
    }

    private static WebDriverWait getWebDriverWaitLong() {
        return new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(35));
    }

    private static WebDriverWait getWebDriverWaitOneSecond() {
        return new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(1));
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsVisibleLongVersion(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWaitLong();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsVisibleOneSecond(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWaitOneSecond();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementIsClickableLongVersion(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWaitLong();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void untilTheTextToBe(WebElement element, String text1) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(textToBePresentInElement(element, text1));
    }

    public static void untilTheTextDoesNotContain(WebElement element, String text1) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.not(textToBePresentInElement(element, text1)));
    }

    public static void waitUntilURLToBe(String URL) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.urlToBe(URL));
    }
    public static void waitUntilURLToBeLong(String URL) {
        WebDriverWait webDriverWait = getWebDriverWaitLong();
        webDriverWait.until(ExpectedConditions.urlToBe(URL));
    }

    public static void waitUntilURLContains(String URL) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.urlContains(URL));
    }
    public static void waitUntilURLContainsLongVersion(String URL) {
        WebDriverWait webDriverWait = getWebDriverWaitLong();
        webDriverWait.until(ExpectedConditions.urlContains(URL));
    }

    public static void waitUntilTitleContains(String title) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilTitleIs(String title) {
        WebDriverWait webDriverWait = getWebDriverWaitLong();
        webDriverWait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitUntilElementIsInvisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitUntilElementIsInvisibleLong(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWaitLong();
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitUntilAttributeContains(WebElement element, String attribute, String value){
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }
    public static void waitUntilPresenceInDOM(By element){
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void waitUntilNotPresentInDOM(WebElement element){
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.stalenessOf(element));
    }
    public static void waitUntilAttributeToBeNotEmpty(WebElement webElement, String attribute) {
        WebDriverWait webDriverWait =  getWebDriverWait();
        webDriverWait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attribute));
    }
    public static void waitUntilAttributeToBe(WebElement element, String attribute, String value){
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }
    public static void waitForVisibilityOFGivenElementAndClick(WebElement element){
        waitUntilElementIsVisible(element);
        element.click();
    }
    public static void waitForTextToBePResentInElement(WebElement element, String text){
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }
}
