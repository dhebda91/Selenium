package page.objects;

import commons.driver.manager.DriverManager;
import commons.waits.WaitForElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static page.objects.Categories.*;

public class DashboardPage extends BasePage {

    @FindBy(css = ".col-12.col-md-6.mt-4")
    private WebElement selectAnItemFromLeftToStartPracticeElement;

    @FindBy(xpath = "//span[text()='Practice Form']")
    private WebElement practiceFormElement;

    public DashboardPage selectCategory(String category) {
        String xpath = null;
        switch (category) {
            case "Elements":
                xpath = "//h5[text()='" + ELEMENTS.getName() + "']";
                break;
            case "Forms":
                xpath = "//h5[text()='" + FORMS.getName() + "']";
                break;
            case "Alerts":
                xpath = "//h5[text()='" + ALERTS.getName() + "']";
                break;
            case "Widgets":
                xpath = "//h5[text()='" + WIDGETS.getName() + "']";
                break;
            case "Interactions":
                xpath = "//h5[text()='" + INTERACTIONS.getName() + "']";
                break;
            case "Books":
                xpath = "//h5[text()='" + BOOKS.getName() + "']";
                break;
        }

        if (xpath != null) {
            DriverManager.getWebDriver().findElement(By.xpath(xpath)).click();
        } else {
            System.out.println("Niepoprawna kategoria: " + category);
        }

        return this;
    }

    public DashboardPage checkControlElement() {
        String text = "Please select an item from left to start practice.";
//        WaitForElement.waitUntilElementIsInvisible(selectAnItemFromLeftToStartPracticeElement);
        Assert.assertEquals(selectAnItemFromLeftToStartPracticeElement.getText(), text);
        return this;
    }
    @Step("Selecting Practice form")
    public void selectingPracticeForm(){
        practiceFormElement.click();
    }
}