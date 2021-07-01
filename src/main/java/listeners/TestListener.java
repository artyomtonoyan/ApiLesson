package listeners;

import org.apache.log4j.Logger;
import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import setup.DriverHelper;

import java.io.File;
import java.io.IOException;

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
        String fileName = System.getProperty("user.dir") + "/Screenshots/" + result.getMethod().getQualifiedName() + "Fail.png";
        try {
            File destiny = new File(fileName);
            FileUtils.copyFile(((TakesScreenshot) DriverHelper.get().getDriver()).getScreenshotAs(OutputType.FILE), destiny);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
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