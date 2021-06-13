package listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger LOGGER = Logger.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Started: ---> " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Started Test: ---> " + result.getMethod().getQualifiedName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Passed Test: --> " + result.getMethod().getQualifiedName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Failed Test: --> " + result.getMethod().getQualifiedName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Skipped Test: --> " + result.getMethod().getQualifiedName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Finished: ---> " + context.getName());
    }
}