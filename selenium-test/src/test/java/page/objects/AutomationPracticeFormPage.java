package page.objects;

import commons.driver.manager.DriverManager;
import commons.waits.WaitForElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static page.objects.Categories.*;
import static page.objects.Categories.BOOKS;

public class AutomationPracticeFormPage extends BasePage {

    // https://demoqa.com/automation-practice-form

    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "userEmail")
    private WebElement userEmailInput;
    @FindBy(css = ".custom-control custom-radio custom-control-inline")
    private List<WebElement> genderRadioCheckboxes; // fixme do zmiany nazwa
    @FindBy(id = "userNumber")
    private WebElement mobileInput;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput; // TODO do zrobienia testy z dotapickera
    @FindBy(css = "subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3")
    private WebElement subjectsInput;
    @FindBy(css = ".custom-control custom-checkbox custom-control-inline")
    private List<WebElement> hobbiesCheckboxes;
    @FindBy(id = "uploadPicture")
    private WebElement selectPictureButton;
    @FindBy(css = "textarea#currentAddress")
    private WebElement currentAddressField;
    @FindBy(css = ". css-tlfecz-indicatorContainer")
    private WebElement selectStateOpenListButton;
    @FindBy(css = "")
    private List<WebElement> selectStateOpenList;
    @FindBy(css = "")
    private WebElement selectCityOpenListButton;
    @FindBy(css = "")
    private List<WebElement> selectCityOpenList;
    @FindBy(css = ".react-datepicker__month-select")
    private WebElement monthSelectElement;
    @FindBy(css = ".react-datepicker__year-select")
    private WebElement yearSelectElement;
    String day;
    String month;
    String year ;


    public AutomationPracticeFormPage enterName(String name) {
        inputHelper(firstNameInput, name);
        return this;
    }

    @Step("Enter last name")
    public AutomationPracticeFormPage enterLastName(String lastName) {
        inputHelper(lastNameInput, lastName);
        return this;
    }

    @Step("Enter email")
    public AutomationPracticeFormPage enterUserEmail(String userEmail) {
        inputHelper(userEmailInput, userEmail);
        return this;
    }

    @Step("Selecting gender")
    public AutomationPracticeFormPage selectingGender(String gender) {
        try {
            DriverManager.getWebDriver().findElement(By.xpath("//label[text()='" + gender + "']")).click();
        } catch (NoSuchSessionException e) {
            System.out.println("Incorrect gender " + gender);
        }
        return this;
    }

    @Step("Enter Phone number")
    public AutomationPracticeFormPage enterPhone(String phone) {
        inputHelper(mobileInput, phone);
        return this;
    }

    private void inputHelper(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        Assert.assertEquals(element.getText(), value); // FIXME pobiera pustkę
    }

    @Step("Setting Date of birth")
    public AutomationPracticeFormPage setDateOfBirth() {

        dateOfBirthInput.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String date = "22 February 1991";

        // Podzielenie ciągu znaków na części przy użyciu metody split()
        String[] dateParts = date.split(" ");

        // Sprawdzenie, czy ciąg został poprawnie podzielony na trzy części
        if (dateParts.length == 3) {
            day = dateParts[0];
            month = dateParts[1];
            year = dateParts[2];

            // Wyświetlenie wyników
            System.out.println("Day: " + day);
            System.out.println("Month: " + month);
            System.out.println("Year: " + year);
        } else {
            System.out.println("Invalid date format.");
        }

        Select monthSelect = new Select(monthSelectElement);
        monthSelect.selectByVisibleText(month);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Select yearSelect = new Select(yearSelectElement);
        yearSelect.selectByVisibleText(year);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String dateString = "22 February 1991";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate date1 = LocalDate.parse(dateString, formatter);

        // Pobranie dnia tygodnia
        DayOfWeek dayOfWeek = date1.getDayOfWeek();
        System.out.println("Day of the week: " + dayOfWeek);

        DriverManager.getWebDriver().findElement(By.xpath("//*[contains(@label, 'February 22nd, 1991')]")).click();

// TODO: wybór dnia, jeżeli chcesz szukąć po wartości atrybutu label to należy dodać do tego jeszcze dzień miesiąca. Do zrobienia

        return this;
    }
}
