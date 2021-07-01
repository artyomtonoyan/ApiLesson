package setup;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverHelper {
    public static DriverHelper get() {
        return new DriverHelper();
    }

    public WebDriver driver;
//        private static final String BROWSER = "chrome";
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (driverThread.get() == null || driverThread.get().toString().contains("(null)")) {
//            switch (BROWSER) {
//                case "chrome":
//                    System.setProperty("webdriver.chrome.driver",
//                            System.getProperty("user.dir") + "/src/main/resources/chromedriver");
//                    driver = new ChromeDriver();
//                    driverThread.set(driver);
//                    break;
//                case "firefox":
//                    System.setProperty("webdriver.gecko.driver",
//                            System.getProperty("user.dir") + "/src/main/resources/geckodriver");
//                    driver = new FirefoxDriver();
//                    driverThread.set(driver);
//                    break;
//                case "safari":
//                    System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
//                    driver = new SafariDriver();
//                    driverThread.set(driver);
//                    break;
//                case "remote":c
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(System.getProperty("browser", "chrome"));
            capabilities.setCapability("enableVNC", true);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driverThread.set(driver);
//                    break;
//            }
        }
        return driverThread.get();
    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
        driverThread.remove();
    }

    public static void quitDriver() {
        driverThread.get().quit();
    }
}