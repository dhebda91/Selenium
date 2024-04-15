package commons.utils;

import commons.driver.manager.DriverManager;
import io.qameta.allure.Allure;
import commons.javaScriptExecutor.JavaScriptExecutorClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import commons.waits.TryWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ScreenShotMaker {
    private static final Logger logger = LogManager.getLogger(TryWait.class.getName());
    protected static Logger log() {
        return logger;
    }

    public static void makeScreenShot(){
        byte [] screenshotFile = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        InputStream screen = new ByteArrayInputStream(screenshotFile);
        Allure.addAttachment("Page screenshot", screen);
    }

    public static void makeNamedScreenShot(String attachmentName){
        byte [] screenshotFile = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        InputStream screen = new ByteArrayInputStream(screenshotFile);
        Allure.addAttachment(attachmentName, screen);
    }

    public static void makeFullPageScreenShot(){
        Object[] position = new JavaScriptExecutorClass().getCurrentDocumentPosition();
        log().info("POSITION: " + position[0] + " " + position[1]);
        byte [] screenshotFile = takeFullPageScreenShotAsByte();
        InputStream screen = new ByteArrayInputStream(screenshotFile);
        Allure.addAttachment("Page screenshot test", screen);
        log().info("POSITION AFTER SCROLL: " + new JavaScriptExecutorClass().getCurrentDocumentPosition()[0] + " " + new JavaScriptExecutorClass().getCurrentDocumentPosition()[1]);
        new JavaScriptExecutorClass().scrollToPosition(position);
        Object[] afterposition = new JavaScriptExecutorClass().getCurrentDocumentPosition();
        log().info("AFTER POSITION: " + afterposition[0] + " " +afterposition[1]);
        try{
            Thread.sleep(6000);
        } catch (InterruptedException e){

        }
    }

    private static byte[] takeFullPageScreenShotAsByte(){
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(DriverManager.getWebDriver());
        BufferedImage originalImage = fpScreenshot.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(originalImage, "jpeg", baos);
            baos.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return baos.toByteArray();
    }

}
