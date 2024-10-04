package stepDefinitions;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.BookStoreApplication.LoginAndRegistration;
import stepDefinitions.configurationSteps.TestContext;

public class BookStoreApplicationStepDefs {
    TestContext testContext;
    LoginAndRegistration loginAndRegistration;

    public BookStoreApplicationStepDefs(TestContext testContext, LoginAndRegistration loginAndRegistration) {
        this.testContext = testContext;
        this.loginAndRegistration = loginAndRegistration;
    }

    @Given("UŻYTKOWNIK przechodzi do zakładki Login")
    public void użytkownikPrzechodziDoZakładkiLogin() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/login");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("login"));
    }

    @When("UŻYTKOWNIK przechodzi do rejestracji")
    public void użytkownikPrzechodziDoRejestracji() {
        loginAndRegistration.goToRegistrationPage();
    }

    @When("UŻYTKOWNIK uzupełnia pole imię {}")
    public void użytkownikUzupełniaPoleImięName(String name) {
        loginAndRegistration.completeTheNameField(name);
    }

    @When("UŻYTKOWNIK uzupełnia pole nazwisko {}")
    public void użytkownikUzupełniaPoleNazwiskoLastName(String lastName) {
        loginAndRegistration.completeTheLastNameField(lastName);
    }

    @When("UŻYTKOWNIK uzupełnia pole nazwę użytkownika {}")
    public void użytkownikUzupełniaPoleNazwęUżytkownikaUserName(String userName) {
        loginAndRegistration.completeTheUserNameField(userName);
    }

    @When("UŻYTKOWNIK uzupełnia pole hasło {}")
    public void użytkownikUzupełniaPoleHasłoPassword(String password) {
        loginAndRegistration.completeThePasswordField(password);
    }

    @When("UŻYTKOWNIK zatwierdza capche")
    public void użytkownikZatwierdzaCapche() {
        loginAndRegistration.capcha(); // TODO
    }

    @When("UŻYTKOWNIK klika w przycisk Register")
    public void użytkownikKlikaWPrzyciskRegister() {
        loginAndRegistration.register();
    }

    @Then("SYSTEM tworzy nowego użytkownika")
    public void systemTworzyNowegoUżytkownika() {
        System.out.println("TODO");
    }

    @Then("UŻYTKOWNIK zamyka komunikat sukcesu")
    public void użytkownikZamykaKomunikatSukcesu() {
        System.out.println("TODO");
    }

    @Then("UŻYTKOWNIK loguje sie do systemu")
    public void użytkownikLogujeSieDoSystemu() {
        System.out.println("TODO");
    }
}
