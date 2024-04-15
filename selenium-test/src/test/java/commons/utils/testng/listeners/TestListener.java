package commons.utils.testng.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;


public class TestListener implements ITestListener, ISuiteListener {


    private Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result){
        String testName =  result.getTestClass().getRealClass().getSimpleName();
        logger.info("Testcase {}- Starting test {} ", testName,  result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        String testName =  result.getTestClass().getRealClass().getSimpleName();
        logger.info("Testcase {}- Test {} passed successfully" , testName, result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getTestClass().getRealClass().getSimpleName();
        logger.error("Testcase {}- Test {} failed!", testName, result.getName());
//        ScreenShotMaker.makeFullPageScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult result){
        String testName =  result.getTestClass().getRealClass().getSimpleName();
        logger.error("Testcase {}- Test {} skipped", testName, result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        String testName =  result.getTestClass().getRealClass().getSimpleName();
        logger.error("Testcase {}- Test {} failed!", testName, result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onStart(ISuite suite){
    }
    @Override
    public void onFinish(ISuite suite) {
    }
}
