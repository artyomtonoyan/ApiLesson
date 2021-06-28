package base;

import api.ApiHelper;
import com.google.gson.JsonObject;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import pageobjects.LoginPage;
import setup.DriverHelper;

import java.util.ArrayList;


public class BaseTest {



    @BeforeMethod
    public void start() {
        LoginPage loginPage = new LoginPage();
        DriverHelper.get().getDriver().manage().addCookie(new Cookie("cookieConsent", "1"));
        JsonObject loginResponse = ApiHelper.login("artyomtest", "artyomtest");
        loginPage.setItemInLocalStorage("token", String.valueOf(loginResponse.get("auth_token")));
        loginPage.setItemInLocalStorage("userInfo", String.valueOf(loginResponse));
        DriverHelper.get().getDriver().navigate().refresh();
    }

    @AfterMethod
    public void close() {
        DriverHelper.quitDriver();
    }

    @AfterSuite
    public void deleteAllProjects() {
        ArrayList<String> projectIds = ApiHelper.getCurrentProjectsIds();
        for (String projectId : projectIds) {
            ApiHelper.deleteProject(projectId);
        }
    }
}