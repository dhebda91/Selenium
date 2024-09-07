package stepDefinitions;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.objects.BasePage;
import page.objects.Elements.TextBox;
import stepDefinitions.configurationSteps.TestContext;

public class ElementsStepDefs extends BasePage {
    final TestContext context;
    final TextBox textBox;

    public ElementsStepDefs(TestContext context, TextBox textBox) {
        this.context = context;
        this.textBox = textBox;
    }

    @When("UŻYTKOWNIK przechodzi do zakładki Elementy")
    public void użytkownikPrzechodziDoZakładkiElementy() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/text-box");
    }

    @When("UŻYTKOWNIK uzupełnia dane")
    public void użytkownikUzupełniaDane() {
        textBox.fillFormAndSaveData("John Doe", "john@example.com", "123 Main St", "456 Secondary St");
    }

    @When("UŻYTKOWNIK zatwierdza dane")
    public void użytkownikZatwierdzaDane() {
    }

    @Then("SYSTEM wyświetla dane")
    public void systemWyświetlaDane() {
    }


}
