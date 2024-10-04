package commons.helpers;

import commons.driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class WindowTabHandler {

    Set<String> windowsBeforeClick;
    String originalWindow;

    public Set<String> isNewWindowOrTabOpened() {
        originalWindow = DriverManager.getWebDriver().getWindowHandle();
        windowsBeforeClick = DriverManager.getWebDriver().getWindowHandles();
        return windowsBeforeClick;
    }

    public void isNewWindowOpenedAfterClick(Set<String> windowsBeforeClick) {
        Set<String> windowsAfterClick = DriverManager.getWebDriver().getWindowHandles();
        if (windowsAfterClick.size() > windowsBeforeClick.size()) {
            switchToNewWindow(windowsAfterClick);
        } else {
            Assert.fail("Nowe okno nie zostało otwarte");
        }
    }

    private void switchToNewWindow(Set<String> windowsAfterClick) {
        for (String windowHandle : windowsAfterClick) {
            if (!windowsBeforeClick.contains(windowHandle)) {
                DriverManager.getWebDriver().switchTo().window(windowHandle);
                WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))); // Czekaj na tag body
                break;
            }
        }
    }

    public String determineIfWindowOrTab() {
        // Zapisz rozmiar bieżącego okna
        Dimension currentSize = DriverManager.getWebDriver().manage().window().getSize();

        // Zmierz okno, aby określić, czy to nowe okno czy karta
        if (currentSize.getWidth() < 500 && currentSize.getHeight() < 500) {
            return "Nowe okno zostało otwarte";
        } else {
            return "Nowa karta została otwarta";
        }
    }

    public void checkMessageWindow() {
        // Zapisz uchwyt oryginalnego okna

        String mainWindow = DriverManager.getWebDriver().getWindowHandle();

        // Pobierz uchwyty okien przed kliknięciem
        Set<String> windowsBeforeClick = DriverManager.getWebDriver().getWindowHandles();

        // Kliknij przycisk, aby otworzyć małe okno wiadomości
        DriverManager.getWebDriver().findElement(By.id("messageWindowButton")).click();

        // Sprawdź, czy otworzyło się nowe okno
        Set<String> windowsAfterClick = DriverManager.getWebDriver().getWindowHandles();
        System.out.println("*****************************************************************");
        System.out.println(windowsAfterClick);
        System.out.println(windowsBeforeClick);
        if (windowsAfterClick.size() > windowsBeforeClick.size()) {
            switchToNewWindow(windowsAfterClick);

            // Sprawdzenie zawartości nowego okna
            String windowText = DriverManager.getWebDriver().findElement(By.tagName("body")).getText();
            System.out.println("Zawartość nowego okna: " + windowText);

            // Weryfikacja, czy zawiera odpowiednią wiadomość
            if (windowText.contains("Knowledge increases by sharing but not by saving. Please share this to your friends and in your organization.")) {
                System.out.println("Otwarte zostało małe okno wiadomości.");
            }

            // Zamknij nowe okno
            DriverManager.getWebDriver().close();

            // Powrót do głównego okna
            DriverManager.getWebDriver().switchTo().window(mainWindow);
        } else {
            Assert.fail("Nowe okno wiadomości nie zostało otwarte");
        }
    }
}
