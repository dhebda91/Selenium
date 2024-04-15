package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ErrorMessage {
    private static final Logger logger = LogManager.getLogger(ErrorMessage.class.getName());

    protected static Logger log() {
        return logger;
    }

    public static String getErrorMessage(WebElement element) {
        String errorMessage = element.getText();
        log().info("Error message: " + errorMessage);
        return errorMessage;
    }
}