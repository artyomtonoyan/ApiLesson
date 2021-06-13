package pageobjects;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage() {
        open(getURL());
    }

    @Override
    public String getURL() {
        return BASE_URL + "/login";
    }
}