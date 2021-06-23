package pageobjects;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage() {
        open(getURL());
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {

    }

    @Override
    public String getURL() {
        return BASE_URL + "/login";
    }
}