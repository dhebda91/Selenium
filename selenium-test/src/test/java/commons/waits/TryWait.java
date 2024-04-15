package commons.waits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class TryWait {
    private static final Logger logger = LogManager.getLogger(TryWait.class.getName());

    protected static Logger log() {
        return logger;
    }

    public static void tryWaitingForGivenElement(WebElement element) {
        try {
            new TryWait().giveElementToWait(element).giveElementToWaitDisappear(element);
        } catch (TimeoutException e) {
            log().info("Brak elementu");
        } catch (ElementClickInterceptedException e) {
            log().info("Kliknięcie przechwycone przez inny element");
        } catch (StaleElementReferenceException e) {
            log().info("Stale element");
        } catch (IndexOutOfBoundsException e) {
            log().info("Brak listy");
        } finally {
            log().info("Próba kontynuowania testu");
        }
    }

    public static void tryWaitingForGivenElementLongDisappear(WebElement element) {
        try {
            new TryWait().giveElementToWait(element).giveElementToWaitDisappearLongVersion(element);
        } catch (TimeoutException e) {
            log().info("Brak elementu");
        } catch (ElementClickInterceptedException e) {
            log().info("Kliknięcie przechwycone przez inny element");
        } catch (StaleElementReferenceException e) {
            log().info("Stale element");
        } finally {
            log().info("Próba kontynuowania testu");
        }
    }

    public static void tryWaitingForGivenElementLongAppear(WebElement element) {
        try {
            new TryWait().giveElementToWaitLongVersion(element).giveElementToWaitDisappear(element);
        } catch (TimeoutException e) {
            log().info("Brak elementu");
        } catch (ElementClickInterceptedException e) {
            log().info("Kliknięcie przechwycone przez inny element");
        } catch (StaleElementReferenceException e) {
            log().info("Stale element");
        } catch (IndexOutOfBoundsException e) {
            log().info("Brak listy");
        } finally {
            log().info("Próba kontynuowania testu");
        }
    }

    public static void tryWaitingForElementDisappearance(WebElement element) {
        try {
            new TryWait().giveElementToWaitDisappear(element);
        } catch (TimeoutException e) {
            log().info("Brak elementu");
        } catch (ElementClickInterceptedException e) {
            log().info("Kliknięcie przechwycone przez inny element");
        } catch (StaleElementReferenceException e) {
            log().info("Stale element");
        } catch (IndexOutOfBoundsException e) {
            log().info("Brak listy");
        }
    }

    public static void tryWaitingForElementAppearance(WebElement element) {
        try {
            new TryWait().giveElementToWait(element);
        } catch (TimeoutException e) {
            log().info("Brak elementu");
        } catch (ElementClickInterceptedException e) {
            log().info("Kliknięcie przechwycone przez inny element");
        } catch (StaleElementReferenceException e) {
            log().info("Stale element");
        } catch (IndexOutOfBoundsException e) {
            log().info("Brak listy");
        }
    }

    private TryWait giveElementToWait(WebElement element) {
        WaitForElement.waitUntilElementIsVisibleOneSecond(element);
        return this;
    }

    private TryWait giveElementToWaitLongVersion(WebElement element) {
        WaitForElement.waitUntilElementIsVisible(element);
        return this;
    }

    private void giveElementToWaitDisappear(WebElement element) {
        WaitForElement.waitUntilElementIsInvisible(element);
    }

    private void giveElementToWaitDisappearLongVersion(WebElement element) {
        WaitForElement.waitUntilElementIsInvisibleLong(element);
    }

    public static void providingBuilder(Runnable function) {
        try {
            function.run();
        } catch (TimeoutException e) {
            log().info("Brak elementu");
        } catch (ElementClickInterceptedException e) {
            log().info("Kliknięcie przechwycone przez inny element");
        } catch (StaleElementReferenceException e) {
            log().info("Stale element");
        } finally {
            log().info("Próba kontynuowania testu");
        }
    }
}
