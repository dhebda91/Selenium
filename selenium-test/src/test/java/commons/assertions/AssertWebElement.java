package commons.assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

//Klasa zaimplementowana zgodnie z instrukcją z http://joel-costigliola.github.io/assertj/assertj-assertions-generator.html
public class AssertWebElement extends AbstractAssert<AssertWebElement, WebElement> {

    //Instancja Logger - do logowania wiadomości
    private Logger logger = LogManager.getLogger(AssertWebElement.class);

    public AssertWebElement(WebElement webElement) {
        super(webElement, AssertWebElement.class);
    }

    public static AssertWebElement assertThat(WebElement webElement) {
        return new AssertWebElement(webElement);
    }

    //Metoda do sprawdzania czy element jest wyświetlony
    public AssertWebElement isDisplayed() {
        logger.info("Checking if element is displayed");
        isNotNull();

        if (!actual.isDisplayed()) {
            failWithMessage("Element was not displayed!");
        } else {
            logger.info("WebElement was displayed!");
        }
        return this;
    }

    //Metoda do sprawdzenia czy element posiada zadany tekst
    public AssertWebElement hasText(String expectedTextValue) {
        logger.info("Checking if WebElement has text: " + expectedTextValue);
        isNotNull();

        String actualElementText = actual.getText();
        if (!actualElementText.equals(expectedTextValue)) {
            failWithMessage("Element text was <%s> expecting to be <%s>!", actualElementText, expectedTextValue);
        }
        logger.info("WebElement had expected text!");
        return this;
    }

    //
    public AssertWebElement isEnabled(){
        logger.info("Checking if WebElement is enabled");
        isNotNull();

        if(!actual.isEnabled()){
            failWithMessage("Expected element to be enabled. But was not!!");
        }
        logger.info("WebElement was enabled!");
        return this;
    }

    // Metoda do sprawdzenia czy element jest buttonem
    public AssertWebElement isButton(){
        logger.info("Checking if WebElement is button");
        isNotNull();

        if(!(actual.getTagName().equalsIgnoreCase("button") || actual.getAttribute("type").equalsIgnoreCase("button"))){
            failWithMessage("Expected element to be a button. But was not!!");
        }
        logger.info("Expected element to be a button!");
        return this;
    }

    // Metoda do sprawdzenia czy element jest linkiem
    public AssertWebElement isLink(){
        logger.info("Checking if a WebElement is a link");
        isNotNull();

        if(!actual.getTagName().equalsIgnoreCase("a")){
            failWithMessage("Expected element to be a link. But was not!!");
        }
        logger.info("Expected element to be a link!");
        return this;
    }

    // Metoda do sprawdzenia czy element posiada zadany atrybut
    public AssertWebElement hasAttributeValue(String attr, String value){
        logger.info("Checking if element has a given attr");
        isNotNull();

        if(!actual.getAttribute(attr).equals(value)){
            failWithMessage("Expected element to have attr <%s> value as <%s>. But was not!!", attr, value);
        }
        logger.info("The element has a given attr!");
        return this;
    }

}