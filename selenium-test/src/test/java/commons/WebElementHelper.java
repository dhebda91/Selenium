package commons;

import commons.javaScriptExecutor.JavaScriptExecutorClass;
import commons.waits.WaitForElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class WebElementHelper {

    public WebElementHelper() {
    }

    public static boolean isAvailable(WebElement element, boolean scrollToElement) {
        try {
            if (scrollToElement) {
                (new JavaScriptExecutorClass()).scrollToElement(element);
            }

            return element.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException var3) {
            return false;
        }
    }

    public static String getTextAfterCheck(WebElement element) {
        try {
            return element.getText();
        } catch (NoSuchElementException var2) {
            return null;
        }
    }

    public static void checkElementsDisplay(String testedElement, Boolean scrollToElement, SoftAssert s, WebElement elementLabel, WebElement elementSelect) {
        try {
            WaitForElement.waitUntilElementIsVisible(elementLabel);
            s.assertTrue(isAvailable(elementLabel, scrollToElement), "Nie wyświetlono labelki dla " + testedElement);
            s.assertTrue(isAvailable(elementSelect, scrollToElement), "Nie wyświetlono selecta/inputu dla " + testedElement);
        } catch (TimeoutException var6) {
            Assert.fail("Nie wyświetlono elementów");
        }

    }
}