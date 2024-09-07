package page.objects;

import commons.WebElementHelper;
import commons.driver.manager.DriverManager;
import commons.helpers.FileManagingHelper;
import commons.javaScriptExecutor.JavaScriptExecutorClass;
import commons.waits.WaitBuilder;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AutomationPracticeFormPage extends BasePage {

    // https://demoqa.com/automation-practice-form

    @FindBy(id = "firstName")
    private WebElement nameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "userEmail")
    private WebElement userEmailInput;
    @FindBy(css = ".custom-control custom-radio custom-control-inline")
    private List<WebElement> genderRadioCheckboxes;
    @FindBy(css = "div#genterWrapper > .col-md-9.col-sm-12")
    private WebElement genderRadioCheckWrapper;
    @FindBy(id = "userNumber")
    private WebElement mobileInput;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;
    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;
    @FindBy(css = "div#hobbiesWrapper > .col-md-9.col-sm-12 > div")
    private List<WebElement> hobbiesCheckboxes;
    @FindBy(css = "div#hobbiesWrapper > .col-md-9.col-sm-12")
    private WebElement hobbiesRadioCheckWrapper;
    @FindBy(xpath = "/html//input[@id='uploadPicture']")
    private WebElement selectPictureButton;
    @FindBy(css = "textarea#currentAddress")
    private WebElement currentAddressField;

    @FindBy(xpath = "//div[@id='state']/div/div[2]/div")
    private WebElement selectStateOpenList;
    @FindBy(css = ".css-11unzgr > div")
    private List<WebElement> selectStateList;
    @FindBy(css = ".css-1uccc91-singleValue")
    private WebElement stateFieldAfterSelection;

    @FindBy(css = "#city [class='css-19bqh2r']")
    private WebElement selectCityOpenList;
    @FindBy(css = ".css-11unzgr > div")
    private List<WebElement> selectCityList;
    @FindBy(css = "div#city .css-1uccc91-singleValue")
    private WebElement cityFieldAfterSelection;

    @FindBy(css = ".react-datepicker__month-select")
    private WebElement monthSelectElement;
    @FindBy(css = ".react-datepicker__year-select")
    private WebElement yearSelectElement;
    @FindBy(css = ".react-datepicker__current-month--hasMonthDropdown")
    private WebElement monthAndYearOnCalendar;
    @FindBy(css = "button#submit")
    private WebElement submitButton;

    // podziekowanie
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement subbmitingTitle;
    @FindBy(id = "closeLargeModal")
    private WebElement closeLargeModalButton;


    String dayOfBirth;
    String monthOfBirth;
    String yearOfBirth;


    public void checkIfEverythingIsDisplayed() {
        WaitBuilder.waitDefaultTime().untilElementIsVisible(nameInput);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(WebElementHelper.isAvailable(nameInput, false), "Name input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(lastNameInput, false), "Lastname input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(userEmailInput, false), "Email input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(genderRadioCheckWrapper, false), "Gender checkboxes is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(mobileInput, false), "Mobile input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(dateOfBirthInput, false), "Date of Birth input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(subjectsInput, false), "Subjects input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(hobbiesRadioCheckWrapper, false), "Hobbies checkboxes input is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(selectPictureButton, false), "Select picture element is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(currentAddressField, false), "Current Address field is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(selectStateOpenList, true), "State field is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(selectCityOpenList, true), "City field is not displayed");
        softAssert.assertTrue(WebElementHelper.isAvailable(submitButton, false), "Submit button is not displayed");
        softAssert.assertAll();
    }

    public AutomationPracticeFormPage enterName(String name) {
        inputHelper(nameInput, name);
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
    public AutomationPracticeFormPage selectionOfItems(String subject) {
        WaitBuilder.waitDefaultTime().untilElementIsVisible(subjectsInput);
        subjectsInput.sendKeys(subject);
        subjectsInput.sendKeys(Keys.ARROW_DOWN);
        subjectsInput.sendKeys(Keys.ENTER);
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

    @Step(value = "Dodanie zdjęcia")
    public AutomationPracticeFormPage addPhoto() {
        new JavaScriptExecutorClass().scrollToElement(selectPictureButton);
        String photo = "src/test/resources/hello-world.png";
        File file = FileManagingHelper.convertStringPathToAbsoluteFilePath(photo);
        if (file.exists()) {
            selectPictureButton.sendKeys(file.getAbsolutePath());
        }
//        Assert.assertEquals(selectPictureButton.getText(), "hello-world.png"); // TODO dodać jakąś asercję, ale póki co nie da sie pobrac textu
        return this;
    }

    @Step("Set current address")
    public AutomationPracticeFormPage setCurrentAddress(String address) {
        currentAddressField.sendKeys(address);
        return this;
    }

    @Step("Select State")
    public AutomationPracticeFormPage selectState(String state) {
        selectItemOnList(selectStateOpenList, selectStateList, state, stateFieldAfterSelection);
        return this;
    }

    @Step("Select city")
    public AutomationPracticeFormPage selectCity(String city) {
        selectItemOnList(selectCityOpenList, selectCityList, city, cityFieldAfterSelection);
        return this;
    }

    private void selectItemOnList(WebElement openList, List<WebElement> list, String text, WebElement afterSelect) {
        new JavaScriptExecutorClass().scrollToEndOfPage();
        openList.click();
        for (WebElement option : list) {
            if (option.getText().equals(text))
                option.click();
            break;
        }
        System.out.println("Selected element: " + afterSelect.getText());
        assertEquals(afterSelect.getText(), text);
    }

    @Step("Submit button clinc")
    public AutomationPracticeFormPage submitButtonClick() {
        submitButton.click();
        return this;
    }

    @Step("Thanks for submitting the form")
    public AutomationPracticeFormPage subbmitingTheForm() {
        WaitBuilder.waitDefaultTime().untilElementIsVisible(subbmitingTitle);
        assertTrue(subbmitingTitle.isDisplayed());
        assertEquals("Thanks for submitting the form", subbmitingTitle.getText());
        return this;
    }

    @Step("Student check data")
    public AutomationPracticeFormPage studentCheckData() {

        return this;
    }

    @Step("Student clicks close")
    public void studentClicksClose() {
        WaitBuilder.waitDefaultTime().untilElementIsVisible(closeLargeModalButton);
        closeLargeModalButton.click();
    }

    private WebElement labelLocatorBuilding(String labelName) {
        String locatorPart = "//td[.='";
        String locatorPart1 = "/']";
        String builder = locatorPart + labelName + locatorPart1;
        return DriverManager.getWebDriver().findElement(By.xpath(builder));
    }

    private WebElement valueLocatorBuilding(String labelName) {
        String locatorPart = "//td[.='";
        String locatorPart1 = "']/following-sibling::td[1]";
        String builder = locatorPart + labelName + locatorPart1;
        return DriverManager.getWebDriver().findElement(By.xpath(builder));
    }
}