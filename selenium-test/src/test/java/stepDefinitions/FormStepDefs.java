package stepDefinitions;

import Variables.CustomerData;
import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.AutomationPracticeFormPage;
import page.objects.BasePage;
import page.objects.DashboardPage;
import stepDefinitions.configurationSteps.TestContext;

public class FormStepDefs extends BasePage {
    final TestContext context;
    final DashboardPage dashboardPage;
    final AutomationPracticeFormPage automationPracticeFormPage;
    CustomerData customerData;
    String page;

    public FormStepDefs(TestContext testContext, DashboardPage dashboardPage, AutomationPracticeFormPage automationPracticeFormPage) {
        this.context = testContext;
        this.dashboardPage = dashboardPage;
        this.automationPracticeFormPage = automationPracticeFormPage;
        this.customerData = new CustomerData();
    }

    public void setCustomerData() {
        customerData.setFirstName("Dorota");
        customerData.setLastName("Hebda");
        customerData.setEmail("dorota.hebda91@gmail.com");
        customerData.setPhone("000111000");
    }

    @When("UŻYTKOWNIK wybiera kafel {}")
    public void użytkownikWybieraKafelForms(String categories) {
        dashboardPage.selectCategory(categories);
    }

    @When("SYSTEM wyświetla komunikat o konieczności wyboru elementu do ćwiczeń")
    public void systemWyświetlaKomunikatOKoniecznościWyboruElementuDoĆwiczeń() {
        dashboardPage.checkControlElement();
    }

    @When("UŻYTKOWNIK wybiera opcję Practice Form")
    public void użytkownikWybieraOpcjęPracticeForm() {
        dashboardPage.selectingPracticeForm();
        Assert.assertEquals(DriverManager.getWebDriver().getCurrentUrl(), "https://demoqa.com/automation-practice-form");
    }

    @When("SYSTEM wyświetla formularz")
    public void systemWyświetlaFormularz() {
//        DriverManager.getWebDriver().navigate().to("https://demoqa.com/automation-practice-form");
        automationPracticeFormPage.checkIfEverythingIsDisplayed();
        setCustomerData();
    }

    @When("UŻYTKOWNIK uzupełnia pole imię")
    public void użytkownikUzupełniaPoleImię() {
        automationPracticeFormPage.enterName(customerData.getFirstName());
    }

    @When("UŻYTKOWNIK uzupełnia pole nazwisko")
    public void użytkownikUzupełniaPoleNazwisko() {
        automationPracticeFormPage.enterLastName(customerData.getLastName());
    }

    @When("UŻYTKOWNIK uzupełnia pole email")
    public void użytkownikUzupełniaPoleEmail() {
        automationPracticeFormPage.enterUserEmail(customerData.getEmail());
    }

    @When("UŻYTKOWNIK uzupełnia wybiera płeć")
    public void użytkownikUzupełniaWybieraPłeć() {
        automationPracticeFormPage.selectingGender("Male");
    }

    @When("UŻYTKOWNIK uzupełnia pole numer telefonu")
    public void użytkownikUzupełniaPoleNumerTelefonu() {
        automationPracticeFormPage.enterPhone(customerData.getPhone());
    }

    @When("UŻYTKOWNIK uzupełnia pole data urodzenia")
    public void użytkownikUzupełniaPoleDataUrodzenia() {
        automationPracticeFormPage.setDateOfBirth("22 February 1991");
    }

    @When("UŻYTKOWNIK uzupełnia pole przedmiot")
    public void użytkownikUzupełniaPolePrzedmiot() {
        automationPracticeFormPage.selectionOfItems();
    }

    @When("UŻYTKOWNIK uzupełnia wybiera hobby")
    public void użytkownikUzupełniaWybieraHobby() {
        automationPracticeFormPage.selectionOfHobbies("Music");
    }

    @When("UŻYTKOWNIK wgrywa zdjęcie")
    public void użytkownikWgrywaZdjęcie() {
    }

    @When("UŻYTKOWNIK uzupełnia pole obeczny adres")
    public void użytkownikUzupełniaPoleObecznyAdres() {
    }

    @When("UŻYTKOWNIK z listy rozwijanej wybiera stan")
    public void użytkownikZListyRozwijanejWybieraStan() {
    }

    @When("UŻYTKOWNIK z listy rozwijanej wybiera miasto")
    public void użytkownikZListyRozwijanejWybieraMiasto() {
    }

    @When("UŻYTKOWNIK klika przycisk Submit")
    public void użytkownikKlikaPrzyciskSubmit() {
    }
}
