package page.objects.AletrsFrameAndWindows;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.objects.BasePage;

public class BrowserWindows extends BasePage {

    @FindBy(id = "tabButton")
    private WebElement newTabButton;

    @FindBy(id = "windowButton")
    private WebElement newWindowButton;

    @FindBy(id = "msgWindowButtonWrapper")
    private WebElement newWindoMessageButton;

    public void buttonClicker(String button) {
        switch (button) {
            case "New Tab":
                newTabButton.click();
                break;
            case "New Window":
                newWindowButton.click();
                break;
            case "New Window Message":
                newWindoMessageButton.click();
                break;
            default:
        }
    }
}