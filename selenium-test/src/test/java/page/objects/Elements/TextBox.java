package page.objects.Elements;

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

    // Lista, która przechowuje dane
    private List<String> savedData = new ArrayList<>();

    // Metoda wypełniająca pola formularza i zapisująca dane do listy
    public void fillFormAndSaveData(String fullName, String email, String currentAddress, String permanentAddress) {
        // Wypełnienie pól formularza
        fullNameInput.sendKeys(fullName);
        emailInput.sendKeys(email);
        currentAddressInput.sendKeys(currentAddress);
        permanentAddressInput.sendKeys(permanentAddress);

        // Zapisanie danych do listy
        savedData.add("Full Name: " + fullName);
        savedData.add("Email: " + email);
        savedData.add("Current Address: " + currentAddress);
        savedData.add("Permanent Address: " + permanentAddress);
    }

    // Metoda zwracająca zapisane dane
    public List<String> getSavedData() {
        return savedData;
    }

    // Metoda pobierająca dane z sekcji podsumowania i porównująca z zapisanymi danymi
    public boolean compareSummaryWithData() {
        // Tworzymy listę do przechowywania danych z sekcji podsumowania
        List<String> summaryData = new ArrayList<>();

        // Przechodzimy przez elementy summarySection i dodajemy ich tekst do listy
        for (WebElement element : summarySection) {
            summaryData.add(element.getText());
        }

        // Porównanie dwóch list - zapisanej i z podsumowania
        return savedData.equals(summaryData);
    }
}