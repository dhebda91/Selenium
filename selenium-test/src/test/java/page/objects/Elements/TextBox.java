package page.objects.Elements;

import commons.javaScriptExecutor.JavaScriptExecutorClass;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.objects.BasePage;

import java.util.ArrayList;
import java.util.List;

public class TextBox extends BasePage {

    @FindBy(id = "userName")
    public WebElement fullNameInput;
    @FindBy(id = "userEmail")
    public WebElement emailInput;
    @FindBy(id = "currentAddress")
    public WebElement currentAddressInput;
    @FindBy(id = "permanentAddress")
    public WebElement permanentAddressInput;
    @FindBy(id = "submit")
    public WebElement submitButton;
    @FindBy(xpath = "/html//p")
    public List<WebElement> summarySection;

    @Getter
    private List<String> savedData = new ArrayList<>();

    @Step("Form completion")
    public void fillFormAndSaveData(String fullName, String email, String currentAddress, String permanentAddress) {
        fullNameInput.sendKeys(fullName);
        emailInput.sendKeys(email);
        currentAddressInput.sendKeys(currentAddress);
        permanentAddressInput.sendKeys(permanentAddress);

        savedData.add("Name:" + fullName);
        savedData.add("Email:" + email);
        savedData.add("Current Address :" + currentAddress);
        savedData.add("Permananet Address :" + permanentAddress);
    }

    @Step("Clicking the confirmation button")
    public void clickConfirmationButton() {
        new JavaScriptExecutorClass().scrollToEndOfPage();
        submitButton.click();
    }


    @Step("Data validation")
    public boolean compareSummaryWithData() {
        List<String> summaryData = new ArrayList<>();
        for (WebElement element : summarySection) {
            System.out.println("To summaryData.add(element.getText() : "+ summaryData.add(element.getText()));
            System.out.println("To zcompareSummaryWithData : "+ element.getText());
        }

        System.out.println("Summary data: "+ summaryData);
        System.out.println("Saved data: "+ savedData);

        return savedData.equals(summaryData);
    }
}