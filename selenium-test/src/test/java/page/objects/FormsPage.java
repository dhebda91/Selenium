package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FormsPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement userEmailInput;


    @Step("Enter name")
    public FormsPage enterName(String name) {
        inputHelper(firstNameInput, name);
        return this;
    }

    @Step("Enter last name")
    public FormsPage enterLastName(String lastName) {
        inputHelper(lastNameInput, lastName);
        return this;
    }

    @Step("Enter email")
    public FormsPage enterUserEmail(String userEmail) {
        inputHelper(userEmailInput, userEmail);
        return this;
    }

    private void inputHelper(WebElement element, String value) {
        element.clear();
        element.sendKeys();
        Assert.assertEquals(element.getText(), value);
    }
}
