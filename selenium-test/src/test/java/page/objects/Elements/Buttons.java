package page.objects.Elements;

import commons.driver.manager.DriverManager;
import commons.javaScriptExecutor.JavaScriptExecutorClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import page.objects.BasePage;

public class Buttons extends BasePage {
    String buttonType;
    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClick;
    @FindBy(id = "rightClickBtn")
    private WebElement rightClick;
    @FindBy(xpath = "/html//button[text()='Click Me']")
    private WebElement clickMe;

    @FindBy(id = "doubleClickMessage")
    private WebElement doubleClickMsg;
    @FindBy(id = "rightClickMessage")
    private WebElement rightClickMsg;
    @FindBy(id = "dynamicClickMessage")
    private WebElement clickMeMgs;

    public void clickButton(String button) {
        this.buttonType = button;
        switch (button) {
            case "Double Click Me":
                new JavaScriptExecutorClass().doubleClick(doubleClick);
                break;
            case "Right Click Me":
                Actions actions = new Actions(DriverManager.getWebDriver());
                actions.contextClick(rightClick).perform();
                break;
            case "Click Me":
                clickMe.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid button: " + button);
        }
    }

    public boolean checkMessage(String message) {
        return getMessageElement().getText().equals(message);
    }

    private WebElement getMessageElement() {
        switch (buttonType) {
            case "Double Click Me":
                return doubleClickMsg;
            case "Right Click Me":
                return rightClickMsg;
            case "Click Me":
                return clickMeMgs;
            default:
                throw new IllegalArgumentException("Invalid button: " + buttonType);
        }
    }
}