package page.objects.Elements;

import commons.driver.manager.DriverManager;
import commons.waits.WaitBuilder;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page.objects.BasePage;

public class RadioButton extends BasePage {
    private String button;
    @FindBy(css = "[for='yesRadio']")
    private WebElement yesButton;
    @FindBy(css = "[for='impressiveRadio']")
    private WebElement impressiveRadio;
    @FindBy(css = "[for='noRadio']")
    private WebElement noButton;
    @FindBy(css = "[class='mt-3']")
    private WebElement displayedText;
// w klasie jestc ustom-control-input disabled

    @Step("Click on button")
    public void buttonAction(String button) {
        this.button = button.toLowerCase();
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        switch (button) {
            case "yes":
                WaitBuilder.waitDefaultTime().untilElementIsVisible(yesButton);
                checkIfButtonIsClicable(yesButton);
                break;
            case "impressive":
                WaitBuilder.waitDefaultTime().untilElementIsVisible(impressiveRadio);
                impressiveRadio.click();
                checkIfButtonIsClicable(impressiveRadio);
                break;
            case "no":
                WaitBuilder.waitDefaultTime().untilElementIsVisible(noButton);
//                noButton.click());
                checkIfButtonIsClicable(noButton);
                break;
            default:
                throw new IllegalArgumentException("Invalid button: " + button);
        }
    }

    private void checkIfButtonIsClicable(WebElement button) {
        String classAttribute = button.getAttribute("class");
        if (!classAttribute.equals("disabled")) {
            button.click();
        } else {
            Assert.fail("Button is not clickable");
        }
    }

    @Step("Check text")
    public String isDisplayedText() {
        if (button.equals("yes") || button.equals("impressive"))
            return displayedText.getText();
        return "";
    }
}