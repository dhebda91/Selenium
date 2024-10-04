package page.objects.Elements;

import commons.driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import page.objects.BasePage;

import java.util.Set;

public class Links extends BasePage {

    private String originalWindow;
    private Set<String> windowsBeforeClick;

    @FindBy(id = "simpleLink")
    private WebElement homeLink;

    @FindBy(id = "dynamicLink")
    private WebElement homePD0pmLink;

    @FindBy(css = "p#linkResponse > b:nth-of-type(1)")
    private WebElement status;

    public void linkClicker(String linkName) {
        countTheNumberOfWindows();

        switch (linkName) {
            case "Home":
                homeLink.click();
                break;
            case "HomePD0pM":
                homePD0pmLink.click();
                break;
            default:
                WebElement webElement = DriverManager.getWebDriver()
                        .findElement(By.xpath("//a[text()='" + linkName + "']"));
                webElement.click();
                break;
        }
    }

    private void countTheNumberOfWindows() {
        originalWindow = DriverManager.getWebDriver().getWindowHandle();
        System.out.println("OriginalWindow: " + originalWindow);
        windowsBeforeClick = DriverManager.getWebDriver().getWindowHandles();
        System.out.println("windowsBeforeClickCount: " + windowsBeforeClick.size());
    }

    public void isNewWindowOpenedAfterClick() {
        Set<String> windowsAfterClick = DriverManager.getWebDriver().getWindowHandles();
        if (windowsAfterClick.size() > windowsBeforeClick.size()) {
            switchToNewWindow(windowsAfterClick);
        } else {
            Assert.fail("Nowe okno nie zostało otwarte");
        }
    }

    private void switchToNewWindow(Set<String> windowsAfterClick) {
        for (String windowHandle : windowsAfterClick) {
            if (!windowsBeforeClick.contains(windowHandle)) {
                DriverManager.getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void checkTheDisplayedStatus(String expectedStatus) {
        String displayedStatus = status.getText();
        Assert.assertEquals(displayedStatus, expectedStatus);
    }
}
