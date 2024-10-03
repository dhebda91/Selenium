package page.objects.Elements.CheckBox;

import commons.driver.manager.DriverManager;
import commons.waits.WaitBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import page.objects.BasePage;

import java.util.*;

public class CheckBox extends BasePage {

    private static final List<String> HOME_LIST = Arrays.asList("Home", "Desktop", "Notes", "Commands", "Documents", "WorkSpace", "React", "Angular", "Veu", "Office", "Public", "Private", "Classified", "General", "Downloads", "Word File.doc", "Excel File.doc");
    private static final List<String> DOCUMENTS_LIST = Arrays.asList("Documents", "WorkSpace", "React", "Angular", "Veu", "Office", "Public", "Private", "Classified", "General");
    private static final List<String> DOWNLOADING_LIST = Arrays.asList("Downloads", "Word File.doc", "Excel File.doc");

    private static final Map<String, List<String>> LIST_MAP = new HashMap<>();

    static {
        LIST_MAP.put("home", HOME_LIST);
        LIST_MAP.put("desktop", Arrays.asList("Desktop", "Notes", "Commands"));
        LIST_MAP.put("notes", List.of("Notes"));
        LIST_MAP.put("commands", List.of("Commands"));
        LIST_MAP.put("documents", DOCUMENTS_LIST);
        LIST_MAP.put("workspace", Arrays.asList("WorkSpace", "React", "Angular", "Veu"));
        LIST_MAP.put("react", List.of("React"));
        LIST_MAP.put("angular", List.of("Angular"));
        LIST_MAP.put("veu", List.of("Veu"));
        LIST_MAP.put("office", Arrays.asList("Office", "Public", "Private", "Classified", "General"));
        LIST_MAP.put("public", List.of("Public"));
        LIST_MAP.put("private", List.of("Private"));
        LIST_MAP.put("classified", List.of("Classified"));
        LIST_MAP.put("general", List.of("General"));
        LIST_MAP.put("downloads", DOWNLOADING_LIST);
        LIST_MAP.put("wordFile", List.of("Word File.doc"));
        LIST_MAP.put("excelFile", List.of("Excel File.doc"));
    }

    @FindBy(css = ".text-success")
    private List<WebElement> textSuccess;
    @FindBy(xpath = "//button[@title='Expand all']")
    private WebElement expandAllButton;

    public void clickOnExpandAllButtonClick() {
        expandAllButton.click();
    }

    public void clickOnCheckBox(String checkbox) {
        checkbox = OptimizedListAndCheckBoxNames.processCheckboxName(checkbox);

        WebElement checkboxElement = findCheckbox(checkbox);
        WaitBuilder.waitDefaultTime().untilElementIsVisible(checkboxElement);
        checkboxElement.click();
        checkIfCheckboxIsSelectedByClassAttribute(checkbox);
    }

    private WebElement findCheckbox(String checkbox) {
        return DriverManager.getWebDriver().findElement(By.cssSelector(locatorBuilder(checkbox)));
    }

    private void checkIfCheckboxIsSelectedByClassAttribute(String checkbox) {
        boolean isSelected = isCheckboxSelected(checkbox);
        System.out.println("======================================================");
        System.out.println(isSelected);
        System.out.println("======================================================");
    }

    private void unClickCheckboxIfSelected(WebElement checkboxElement) {
        if (isCheckboxSelectedByElement(checkboxElement)) {
            checkboxElement.click();
        } else {
            Assert.fail("Element nie był zaznaczony jak oczekiwano.");
        }
    }

    private boolean isCheckboxSelected(String checkbox) {
        WebElement iconElement = DriverManager.getWebDriver().findElement(By.cssSelector(locatorBuilder(checkbox) + " > svg"));
        return iconElement.getAttribute("class").contains("rct-icon-check");
    }

    private boolean isCheckboxSelectedByElement(WebElement checkboxElement) {
        WebElement iconElement = checkboxElement.findElement(By.cssSelector("svg"));
        return iconElement.getAttribute("class").contains("rct-icon-check");
    }

    private String locatorBuilder(String checkboxName) {
        return "[for='tree-node-" + checkboxName + "'] .rct-checkbox";
    }

    public void getResult(String checkboxName) {
        System.out.println("******************************************************************");
        System.out.println(checkboxName);
        System.out.println("******************************************************************");

        List<String> listToCheck = returnList(checkboxName);
        System.out.println("List to check: " + listToCheck);
        System.out.println("Elements " + listToCheck.size());

        List<String> successElements = new ArrayList<>();
        for (WebElement element : textSuccess) {
            successElements.add(element.getText());
        }
        System.out.println("Success List: " + successElements);
        System.out.println("Elements " + successElements.size());

        boolean areEqual = listToCheck.equals(successElements);
        System.out.println("Lista oczekiwana jest taka sama jak na stronie: " + areEqual);
        Assert.assertTrue(areEqual);
    }

    private List<String> returnList(String checkboxName) {
        List<String> list = Optional.ofNullable(LIST_MAP.get(OptimizedListAndCheckBoxNames.processCheckboxName(checkboxName))).orElse(List.of());
        return OptimizedListAndCheckBoxNames.getProcessedList(list);
    }
}