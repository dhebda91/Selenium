package stepDefinitions.Elements;

import commons.driver.manager.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.Elements.Links;
import stepDefinitions.configurationSteps.TestContext;

public class LinksStepDefs {
    TestContext testContext;
    Links links;

    public LinksStepDefs(TestContext testContext, Links links) {
        this.testContext = testContext;
        this.links = links;
    }

    @Given("UŻYTKOWNIK przechodzi do zakładki Links")
    public void użytkownikPrzechodziDoZakładkiLinks() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/links");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("links"));
    }

    @When("UŻYTKOWNIK klika w link {}")
    public void użytkownikKlikaWLinkLink(String link) {
        links.linkClicker(link);
    }

    @Then("SYSTEM otwiera nowe okno")
    public void systemOtwieraNoweOkno() {
        links.isNewWindowOpenedAfterClick();
    }

    @Then("SYSTEM zwraca odpowiedni {}")
    public void systemZwracaOdpowiedniStatus(String status) {
        links.checkTheDisplayedStatus(status);
    }
}