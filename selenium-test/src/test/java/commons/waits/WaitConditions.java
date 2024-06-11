package commons.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class WaitConditions {
    private WebDriverWait webDriverWait;

    private WaitConditions() {
    }

    public WaitConditions(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }

    public void untilElementIsVisible(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void untilElementIsInvisible(WebElement element) {
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void untilElementIsClickable(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void untilTextToBe(WebElement element, String text1) {
        webDriverWait.until(textToBePresentInElement(element, text1));
    }

    public void untilTextDoesNotContain(WebElement element, String text1) {
        webDriverWait.until(ExpectedConditions.not(textToBePresentInElement(element, text1)));
    }

    public void untilURLToBe(String URL) {
        webDriverWait.until(ExpectedConditions.urlToBe(URL));
    }

    public void untilURLContains(String URL) {
        webDriverWait.until(ExpectedConditions.urlContains(URL));
    }

    public void untilTitleIs(String title) {
        webDriverWait.until(ExpectedConditions.titleIs(title));
    }

    public void untilTitleContains(String title) {
        webDriverWait.until(ExpectedConditions.titleContains(title));
    }

    public void untilAttributeToBe(WebElement element, String attribute, String value) {
        webDriverWait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public void untilAttributeToBeNotEmpty(WebElement webElement, String attribute) {
        webDriverWait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attribute));
    }

    public void untilAttributeContains(WebElement element, String attribute, String value) {
        webDriverWait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void untilPresenceInDOM(By element) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void untilNotPresentInDOM(WebElement element) {
        webDriverWait.until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForVisibilityOfGivenElementAndClick(WebElement element) {
        untilElementIsVisible(element);
        element.click();
    }

    public void untilTextOnElementContains(WebElement element, String text) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}