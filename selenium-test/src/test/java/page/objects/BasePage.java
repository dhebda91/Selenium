package page.objects;

import commons.driver.manager.DriverManager;
import commons.javaScriptExecutor.JavaScriptExecutorClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    protected Logger log() {
        return logger;
    }

    public BasePage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @FindBy(css = "header.rac-header")
    public WebElement header;
    @FindBy(xpath = "//*[contains(@class, 'loader')]")
    public WebElement loader;

    public void scrollByHeaderBar(){
        int height = header.getSize().getHeight();
        new JavaScriptExecutorClass().scrollBy(-height, 0);
    }
}