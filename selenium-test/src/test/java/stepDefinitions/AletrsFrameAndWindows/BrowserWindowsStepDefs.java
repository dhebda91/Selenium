package stepDefinitions.AletrsFrameAndWindows;

import commons.driver.manager.DriverManager;
import commons.helpers.WindowTabHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.objects.AletrsFrameAndWindows.BrowserWindows;
import stepDefinitions.configurationSteps.TestContext;

import java.util.Set;

public class BrowserWindowsStepDefs {

    TestContext testContext;
    WindowTabHandler handler = new WindowTabHandler();
    BrowserWindows browserWindows;

    Set<String> windowsBeforeCount;

    public BrowserWindowsStepDefs(TestContext testContext, BrowserWindows browserWindows) {
        this.testContext = testContext;
        this.browserWindows = browserWindows;
    }


    @Given("UŻYTKOWNIK przechodzi do zakładki Browser Windows")
    public void użytkownikPrzechodziDoZakładkiBrowserWindows() {
        DriverManager.getWebDriver().navigate().to("https://demoqa.com/browser-windows");
        Assert.assertTrue(DriverManager.getWebDriver().getCurrentUrl().contains("browser-windows"));
    }

    @When("UŻYTKOWNIK klika w przycisk {}")
    public void użytkownikKlikaWPrzyciskNewTab(String button) {
        windowsBeforeCount = handler.isNewWindowOrTabOpened();
        browserWindows.buttonClicker(button);
    }
    @Then("SYSTEM otwiera nową kartę")
    public void systemOtwieraNowąKartę() {
        handler.isNewWindowOpenedAfterClick(windowsBeforeCount);
        System.out.println(handler.determineIfWindowOrTab());
    }


    @Then("SYSTEM otwiera nowe małe okno")
    public void systemOtwieraNoweMałeOkno() {
        handler.checkMessageWindow();
        System.out.println(handler.determineIfWindowOrTab());
    }

    // TODO - narazie nie działa, dodatkowo należy zrobić w klasie Links - powtórzenia
    @Then("SYSTEM otwiera okno")
    public void systemOtwieraOkno() {
        handler.isNewWindowOpenedAfterClick(windowsBeforeCount);
        System.out.println(handler.determineIfWindowOrTab());
    }
}
