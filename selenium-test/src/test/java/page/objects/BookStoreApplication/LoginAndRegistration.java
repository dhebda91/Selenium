package page.objects.BookStoreApplication;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.objects.BasePage;

public class LoginAndRegistration extends BasePage {

    @FindBy(id = "newUser")
    private WebElement newUserButton;
    @FindBy(id = "firstname")
    private WebElement firstNameField;
    @FindBy(id = "lastname")
    private WebElement lastnameField;
    @FindBy(id = "userName")
    private WebElement userNameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css = ".recaptcha-checkbox-checkmark")
    private WebElement capchaCheckbox;
    @FindBy(css = "button#register")
    private WebElement registerButton;

    public void goToRegistrationPage() {
        newUserButton.click();
    }

    public void completeTheNameField(String name) {
        firstNameField.sendKeys(name);
    }

    public void completeTheLastNameField(String name) {
        lastnameField.sendKeys(name);
    }

    public void completeTheUserNameField(String name) {
        userNameField.sendKeys(name);
    }

    public void completeThePasswordField(String name) {
        passwordField.sendKeys(name);
    }

    public void capcha() {
        capchaCheckbox.click();
    }

    public void register() {
        registerButton.click();
    }
}
