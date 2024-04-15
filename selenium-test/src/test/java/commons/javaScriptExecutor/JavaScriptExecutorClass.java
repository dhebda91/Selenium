package commons.javaScriptExecutor;


import commons.driver.manager.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import commons.utils.ScreenShotMaker;
import commons.waits.TryWait;
import commons.waits.WaitBuilder;

import java.time.Duration;

public class JavaScriptExecutorClass {

    // metody executeScript():

    private static JavascriptExecutor getJSE() {
        return (JavascriptExecutor) DriverManager.getWebDriver();
    }

    // Przejście do elementu, który jest poza aktualnym widokiem okna
    public void scrollToElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Kliknięcie element, na ktorym z jakiejś przyczyny nie działa click();
    public void clickOnElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    // Przykład zwrócenia aktualnego URL strony (fragment JS ze słowem return):
    public void checkUrl() {
        JavascriptExecutor javascriptExecutor = getJSE();
        String url = javascriptExecutor.executeScript("return document.URL;").toString();
        System.out.println("URL strony to: " + url);
    }

    // Oczekiwanie aż strona wczyta się całkowicie
    public void waitUntilPageReady(){
        new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10)).until(
              webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    // Przewinięcie strony na samą górę

    public void scrollToTop() {
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }
    // Przewinięcie strony na sam dół
    public void scrollToEndOfPage() {
        try {
            long lastHeight = (long) ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = (long) ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToEndOfPageLoadingContent(WebElement loader) {
        long lastHeight = (long) ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("return document.body.scrollHeight");
        int count = 2;
        while (count > 0) {
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            TryWait.tryWaitingForGivenElementLongDisappear(loader);
            TryWait.providingBuilder(() -> WaitBuilder.waitForXTime(3).untilElementIsVisible(loader));
            TryWait.providingBuilder(() -> WaitBuilder.waitForXTime(30).untilElementIsInvisible(loader));

            long newHeight = (long) ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                count--;
            }
            lastHeight = newHeight;
        }
    }

    public Object[] getCurrentDocumentPosition(){
        Object Height =  ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("return document.body.offsetHeight");
        Object Width = ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("return document.body.offsetWidth");
        return new Object[]{Height, Width};
    }

    public void scrollToPosition(Object[] position){
        scrollToTop();
        long height = (long) position[0];
        long width = (long) position[1];
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("document.body.scroll(arguments[0], arguments[1]);", width, height);
    }


    public void clearField(WebElement element) {
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("arguments[0].value=''", element);
    }

    // Podmień dane innerText
    public void setInnerText(String text, WebElement element){
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("arguments[0].innerText = '" + text +"'", element);
    }

    // Wpisanie tekstu w input
    public void sendKeysJSE(String text, WebElement element){

    }

    public void openNewTab(){
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("window.open()");
    }

    public void scrollToTestedElement(WebElement element){
        try{
            scrollToElement(element);
        } catch (NoSuchElementException e){
            ScreenShotMaker.makeFullPageScreenShot();
            throw new AssertionError(e.getMessage());
        }
    }

    public void changeZoomValue(double zoom){
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("document.body.style.transform = 'scale(' + arguments[0]+')'", zoom);
    }

    public void scrollBy(int height, int width){
        int heightToScroll = height;
        int widthToScroll = width;
        JavascriptExecutor javascriptExecutor = getJSE();
        javascriptExecutor.executeScript("window.scrollBy(arguments[0],arguments[1])", widthToScroll, heightToScroll);
    }
}