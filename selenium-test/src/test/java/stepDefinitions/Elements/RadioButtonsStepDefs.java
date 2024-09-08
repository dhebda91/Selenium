package stepDefinitions.Elements;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.BasePage;
import page.objects.Elements.RadioButton;
import stepDefinitions.configurationSteps.TestContext;

public class RadioButtonsStepDefs extends BasePage {
    final TestContext context;
    final RadioButton radioButton;

    public RadioButtonsStepDefs(TestContext context, RadioButton radioButton) {
        this.context = context;
        this.radioButton = radioButton;
    }

    @When("UŻYTKOWNIK przechodzi do zakładki RadioButton")
    public void użytkownikPrzechodziDoZakładkiRadioButton() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/radio-button");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("radio-button"));
    }

    @When("UŻYTKOWNIK zaznaczy {}")
    public void użytkownikZaznaczyButton(String button) {
        radioButton.buttonAction(button);
    }

    @Then("SYSTEM wyświetla {}")
    public void systemWyświetlaKomunikat(String komunikat) {
        if(!komunikat.equals("brak")){
            String actualText = radioButton.isDisplayedText();
            Assert.assertEquals(actualText, komunikat);
        } else {
            System.out.println("Button No");
        }
    }
}
