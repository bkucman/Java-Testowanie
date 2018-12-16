package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends  MainClassPage {

    public RegistrationPage(WebDriver _driver){
        super(_driver);
    }
    ElementManage title;
    public ElementManage getTitle() {
        if (title != null)
            return title;
        else {
            title = new ElementManage(this, By.tagName("h2"));
            return title;
        }
    }

    TextFieldManage email;
    public TextFieldManage getEmailField() {
        if (email != null)
            return email;
        else {
            email = new TextFieldManage(this, By.name("user[email]"));
            return email;
        }
    }

    TextFieldManage username;
    public TextFieldManage getUsernameField() {
        if (email != null)
            return email;
        else {
            email = new TextFieldManage(this, By.name("user[email]"));
            return email;
        }
    }

    TextFieldManage password;
    public TextFieldManage getPasswordField() {
        if (password != null)
            return password;
        else {
            password = new TextFieldManage(this, By.id("user_password"));
            return password;
        }
    }

    TextFieldManage passwordConfiramtion;
    public TextFieldManage getPasswordConfiramtionField() {
        if (passwordConfiramtion != null)
            return passwordConfiramtion;
        else {
            passwordConfiramtion = new TextFieldManage(this, By.id("user_password_confirmation"));
            return passwordConfiramtion;
        }
    }

    TextFieldManage signUpControl;
    public TextFieldManage getSignInControl() {
        if (signUpControl != null)
            return signUpControl;
        else {
            signUpControl = new TextFieldManage(this, By.name("commit"));
            return signUpControl;
        }
    }

    public HomePage login()
    {
        getSignInControl().click();
        return new HomePage(driver);
    }
}
