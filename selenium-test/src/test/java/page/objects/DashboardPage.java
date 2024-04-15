package page.objects;

import commons.driver.manager.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static page.objects.Categories.*;

public class DashboardPage extends BasePage {

    public void selectCategory(String category) {
        String xpath = null;
        switch (category) {
            case "ELEMENTS":
                xpath = "//h5[text()='" + ELEMENTS.name() + "']";
                break;
            case "FORMS":
                xpath = "//h5[text()='" + FORMS.name() + "']";
                break;
            case "ALERTS":
                xpath = "//h5[text()='" + ALERTS.name() + "']";
                break;
            case "WIDGETS":
                xpath = "//h5[text()='" + WIDGETS.name() + "']";
                break;
            case "INTERACTIONS":
                xpath = "//h5[text()='" + INTERACTIONS.name() + "']";
                break;
            case "BOOKS":
                xpath = "//h5[text()='" + BOOKS.name() + "']";
                break;
        }

        if (xpath != null) {
            DriverManager.getWebDriver().findElement(By.xpath(xpath)).click();
        } else {
            System.out.println("Niepoprawna kategoria: " + category);
        }
    }
}