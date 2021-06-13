package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverSetup {
    private static WebDriver driver;

    public static WebDriver getWebDriver() {
        if (driver == null || driver.toString().contains("(null)")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            Map<String, Object> p = new HashMap<>();
            p.put("download.default_directory", System.getProperty("user.dir") + "/src/main/resources");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", p);
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        }
        return driver;
    }
}