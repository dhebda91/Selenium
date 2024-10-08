package commons.utils.testng.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    // Dodanie poprzez @Test(retryAnaliser = RetryAnalyser.class)
    private Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int count = 0;
    private static final int MAX_NUMBER_OF_RETRIES = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < MAX_NUMBER_OF_RETRIES) {
                iTestResult.getTestContext().getFailedTests().removeResult(iTestResult);
                iTestResult.getTestContext().getSkippedTests().removeResult(iTestResult);
                count++;
                logger.info("Retrying test method {}!", iTestResult.getName());
                return true;
            }
        }
        logger.info("Test method {} will be not retried!", iTestResult.getName());
        return false;
    }
}