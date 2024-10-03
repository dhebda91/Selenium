package stepDefinitions.Elements;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.Elements.CheckBox.CheckBox;
import stepDefinitions.configurationSteps.TestContext;

public class CheckBoxStepsDefs {
    final TestContext context;
    final CheckBox checkBox;

    public CheckBoxStepsDefs(TestContext context, CheckBox checkBox) {
        this.context = context;
        this.checkBox = checkBox;
    }

    @When("UŻYTKOWNIK przechodzi do zakładki CheckBox")
    public void użytkownikPrzechodziDoZakładkiCheckBox() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/checkbox");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("checkbox"));
    }

    @When("UŻYTKOWNIK oznaczy {}")
    public void użytkownikOznaczyCheckbox(String checkboxName) {
        checkBox.clickOnExpandAllButtonClick();
        checkBox.clickOnCheckBox(checkboxName);
    }

    @Then("SYSTEM wyświetli listę zaznaczonych {}")
    public void systemWyświetliListęZaznaczonychCheckboxów(String checkboxName) {
        checkBox.getResult(checkboxName);
    }

}