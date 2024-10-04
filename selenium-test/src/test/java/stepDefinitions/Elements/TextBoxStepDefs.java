package stepDefinitions.Elements;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.BasePage;
import page.objects.Elements.TextBox;
import stepDefinitions.configurationSteps.TestContext;

public class TextBoxStepDefs extends BasePage {
    final TestContext context;
    final TextBox textBox;

    public TextBoxStepDefs(TestContext context, TextBox textBox) {
        this.context = context;
        this.textBox = textBox;
    }

    @When("UŻYTKOWNIK przechodzi do zakładki Elementy")
    public void użytkownikPrzechodziDoZakładkiElementy() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/elements");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("elements"));
    }

    @When("UŻYTKOWNIK przechodzi do zakładki TextBox")
    public void użytkownikPrzechodziDoZakładkiTextBox() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/text-box");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("text-box"));
    }

    @When("UŻYTKOWNIK uzupełnia dane")
    public void użytkownikUzupełniaDane() {
        textBox.fillFormAndSaveData("John Doe", "john@example.com", "123 Main St", "456 Secondary St");
    }

    @When("UŻYTKOWNIK zatwierdza dane")
    public void użytkownikZatwierdzaDane() {
        textBox.clickConfirmationButton();
    }

    @Then("SYSTEM prezentuje odpowiednie dane")
    public void systemWyświetlaOdpowiednieDane() {
        boolean result = textBox.compareSummaryWithData();
        Assert.assertTrue(result, "Data not equals");
    }
}