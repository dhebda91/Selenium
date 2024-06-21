package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import variables.CustomerData;
import commons.driver.manager.DriverManager;
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
        customerData.setFirstName("Jan");
        customerData.setLastName("Kowlaski");
        customerData.setEmail("Test.test@gmail.com");
        customerData.setPhone("0001110001");
        customerData.setAddress("Rynek 2, Cracow, Poland");
        customerData.setBirthDate("22 February 1991");
        customerData.setGender("Male");
        customerData.setHobby("Music");
        customerData.setState("NCR");
        customerData.setCity("Delhi");
        customerData.setSubject("English");

    }
//
//    @When("STUDENT wybiera kafel {}")
//    public void użytkownikWybieraKafelForms(String categories) {
//        dashboardPage.selectCategory(categories);
//    }
//
//    @When("SYSTEM wyświetla komunikat o konieczności wyboru elementu do ćwiczeń")
//    public void systemWyświetlaKomunikatOKoniecznościWyboruElementuDoĆwiczeń() {
//        dashboardPage.checkControlElement();
//    }

//    @When("STUDENT wybiera opcję Practice Form")
//    public void użytkownikWybieraOpcjęPracticeForm() {
//        dashboardPage.selectingPracticeForm();
//        Assert.assertEquals(DriverManager.getWebDriver().getCurrentUrl(), "https://demoqa.com/automation-practice-form");
//    }
    // TODO to może się jeszcze przydać

    @Given("STUDENT przechodzi do formularza")
    public void studentPrzechodziDoFormularza() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/automation-practice-form");
    }

    @Given("SYSTEM wyświetla formularz")
    public void systemWyświetlaFormularz() {
        automationPracticeFormPage.checkIfEverythingIsDisplayed();
        setCustomerData();
    }

    @When("STUDENT uzupełnia pole imię")
    public void użytkownikUzupełniaPoleImię() {
        automationPracticeFormPage.enterName(customerData.getFirstName());
    }

    @When("STUDENT uzupełnia pole nazwisko")
    public void użytkownikUzupełniaPoleNazwisko() {
        automationPracticeFormPage.enterLastName(customerData.getLastName());
    }

    @When("STUDENT uzupełnia pole email")
    public void użytkownikUzupełniaPoleEmail() {
        automationPracticeFormPage.enterUserEmail(customerData.getEmail());
    }

    @When("STUDENT uzupełnia wybiera płeć")
    public void użytkownikUzupełniaWybieraPłeć() {
        automationPracticeFormPage.selectingGender(customerData.getGender());
    }

    @When("STUDENT uzupełnia pole numer telefonu")
    public void użytkownikUzupełniaPoleNumerTelefonu() {
        automationPracticeFormPage.enterPhone(customerData.getPhone());
    }

    @When("STUDENT uzupełnia pole data urodzenia")
    public void użytkownikUzupełniaPoleDataUrodzenia() {
        automationPracticeFormPage.setDateOfBirth(customerData.getBirthDate());
    }

    @When("STUDENT uzupełnia pole przedmiot")
    public void użytkownikUzupełniaPolePrzedmiot() {
        automationPracticeFormPage.selectionOfItems(customerData.getSubject());
    }

    @When("STUDENT uzupełnia wybiera hobby")
    public void użytkownikUzupełniaWybieraHobby() {
        automationPracticeFormPage.selectionOfHobbies(customerData.getHobby());
    }

    @When("STUDENT wgrywa zdjęcie")
    public void użytkownikWgrywaZdjęcie() {
        automationPracticeFormPage.addPhoto();
    }

    @When("STUDENT uzupełnia pole obecny adres")
    public void użytkownikUzupełniaPoleObecznyAdres() {
        automationPracticeFormPage.setCurrentAddress(customerData.getAddress());
    }

    @When("STUDENT z listy rozwijanej wybiera stan")
    public void użytkownikZListyRozwijanejWybieraStan() {
        automationPracticeFormPage.selectState(customerData.getState());
    }

    @When("STUDENT z listy rozwijanej wybiera miasto")
    public void użytkownikZListyRozwijanejWybieraMiasto() {
        automationPracticeFormPage.selectCity(customerData.getCity());
    }

    @When("STUDENT klika przycisk Submit")
    public void użytkownikKlikaPrzyciskSubmit() {
        automationPracticeFormPage.submitButtonClick();
    }

    @Then("SYSTEM wyświetla podsumowanie formularza")
    public void systemWyświetlaPodsumowanieFormularza() {
        automationPracticeFormPage.subbmitingTheForm();
    }

    @Then("STUDENT sprawdza dane")
    public void studentSprawdzaDane() {
        automationPracticeFormPage.studentCheckData();
    }
}