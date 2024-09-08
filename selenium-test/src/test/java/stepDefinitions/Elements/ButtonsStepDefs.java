package stepDefinitions.Elements;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.BasePage;
import page.objects.Elements.Buttons;
import stepDefinitions.configurationSteps.TestContext;

public class ButtonsStepDefs extends BasePage {
    final TestContext context;
    final Buttons buttons;

    public ButtonsStepDefs(TestContext context, Buttons buttons) {
        this.context = context;
        this.buttons = buttons;
    }

    @When("UŻYTKOWNIK przechodzi do zakładki Buttons")
    public void użytkownikPrzechodziDoZakładkiButtons() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/buttons");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("buttons"));
    }

    @When("UŻYTKOWNIK klika w {}")
    public void użytkownikKlikaWButton(String button) {
        buttons.clickButton(button);
    }

    @Then("SYSTEM po kliknięciu wyświetla {}")
    public void systemPoKliknięciuWyświetlaKomunikat(String komunikat) {
        buttons.checkMessage(komunikat);
    }
}
