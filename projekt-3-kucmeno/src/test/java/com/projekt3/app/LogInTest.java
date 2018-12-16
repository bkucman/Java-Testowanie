package com.projekt3.app;

import org.junit.jupiter.api.Test;
import pageObject.HomePage;
import pageObject.LogInPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest extends BaseT{


    @Test
    public void LogInPassedTest()
    {
        HomePage homePage = new HomePage(driver);
        LogInPage signUpPage = homePage.goToLogInPage();
        signUpPage.getEmailField().setText("user@example.com");
        signUpPage.getPasswordField().setText("user123");
        homePage = signUpPage.login();

        assertThat(homePage.getMessage().getText(), containsString("Signed in successfully."));

    }


    @Test
    public void LogInFailTest()
    {
        HomePage homePage = new HomePage(driver);
        LogInPage signUpPage = homePage.goToLogInPage();
        signUpPage.getEmailField().setText("user@example.com");
        signUpPage.getPasswordField().setText("udsddsdsd");
        homePage = signUpPage.login();

        assertThat(homePage.getMessage().getText(), containsString("Invalid Email or password."));

    }

    @Test
    public void LogOutTest()
    {
        HomePage homePage = new HomePage(driver);
        LogInPage signUpPage = homePage.goToLogInPage();
        signUpPage.getEmailField().setText("user@example.com");
        signUpPage.getPasswordField().setText("user123");
        homePage = signUpPage.login();
        homePage = homePage.goToLogOutPage();

        assertThat(homePage.getMessage().getText(), containsString("Signed out successfully."));

    }
}
