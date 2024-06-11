package page.objects;

import commons.driver.manager.DriverManager;
import commons.waits.WaitBuilder;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.zip.DataFormatException;

public class AutomationPracticeFormPage extends BasePage {

    // https://demoqa.com/automation-practice-form

    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "userEmail")
    private WebElement userEmailInput;
    @FindBy(css = ".custom-control custom-radio custom-control-inline")
    private List<WebElement> genderRadioCheckboxes;
    @FindBy(id = "userNumber")
    private WebElement mobileInput;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;
//    @FindBy(css = "subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3")
    @FindBy(id = "subjectsContainer")
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
    @FindBy(css = ".react-datepicker__current-month--hasMonthDropdown")
    private WebElement monthAndYearOnCalendar;

    String dayOfBirth;
    String monthOfBirth;
    String yearOfBirth;


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
    public AutomationPracticeFormPage setDateOfBirth(String date) {
        dateOfBirthInput.click();
        WaitBuilder.waitDefaultTime().untilElementIsVisible(monthAndYearOnCalendar);
//        String date = "22 February 1991";

        // Podzielenie ciągu znaków na części przy użyciu metody split()
        String[] dateParts = date.split(" ");

        // Sprawdzenie, czy ciąg został poprawnie podzielony na trzy części
        if (dateParts.length == 3) {
            dayOfBirth = dateParts[0];
            monthOfBirth = dateParts[1];
            yearOfBirth = dateParts[2];
        } else {
            try {
                throw new DataFormatException("Invalid date format.");
            } catch (DataFormatException e) {
                throw new RuntimeException(e);
            }
        }

        // Wyświetlenie wyników
        System.out.println("Day: " + dayOfBirth);
        System.out.println("Month: " + monthOfBirth);
        System.out.println("Year: " + yearOfBirth);

        // Month selection
        Select monthSelect = new Select(monthSelectElement);
        monthSelect.selectByVisibleText(monthOfBirth);
        WaitBuilder.waitDefaultTime().untilTextOnElementContains(monthAndYearOnCalendar, monthOfBirth);
        // Year selection
        Select yearSelect = new Select(yearSelectElement);
        yearSelect.selectByVisibleText(yearOfBirth);
        WaitBuilder.waitDefaultTime().untilTextOnElementContains(monthAndYearOnCalendar, yearOfBirth);
        // Day selection
        List<WebElement> days = DriverManager.getWebDriver().findElements(By.cssSelector("div[role='listbox'] > div > div"));
        if (!days.isEmpty()) {
            for (WebElement day : days) {
                String ariaLabel = day.getAttribute("aria-label");
                if (ariaLabel.contains(monthOfBirth) && ariaLabel.contains(yearOfBirth) && day.getText().equals(dayOfBirth)) { // Zmodyfikuj datę i miesiąc według potrzeb
                    day.click();
                    break;
                }
            }
        } else {
            throw new AssertionError("There are no items on the calendar");
        }
        return this;
    }

    @Step("Selection of items")
    public AutomationPracticeFormPage selectionOfItems() {
////        subjectsInput.click();
//        Actions actions = new Actions(DriverManager.getWebDriver());
//        actions.moveToElement(DriverManager.getWebDriver()).click().sendKeys("your value").build().perform();
        return this;
    }

    @Step("Selection of hobbies")
    public AutomationPracticeFormPage selectionOfHobbies(String hobby) {

        for (WebElement hobbies : hobbiesCheckboxes)
            if (hobbies.getText().contains(hobby)) {
                hobbies.click();
                break;
            }

        return this;
    }
}