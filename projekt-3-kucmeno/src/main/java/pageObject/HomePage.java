package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.ElementManage;

public class HomePage extends MainClassPage {
    public HomePage(WebDriver _driver) {
        super(_driver);
    }

    ElementManage dropDown;
    ElementManage logIn;
    public LogInPage goToLogInPage() {

        dropDown = new ElementManage(this, By.linkText("Profile"));
        dropDown.click();
        logIn = new ElementManage(this,By.linkText("Login"));
        logIn.click();
        return new LogInPage(this.driver);

    }

    ElementManage newsPage;
    public NewsPage goToNewsPage() {

        newsPage = new ElementManage(this, By.cssSelector("a[href*='/news']"));
        //newsPage.click();
        return new NewsPage(this.driver);

    }

    public HomePage goToLogOutPage() {

        dropDown = new ElementManage(this, By.linkText("Profile"));
        dropDown.click();
        logIn = new ElementManage(this,By.linkText("Logout"));
        logIn.click();
        return new HomePage(this.driver);

    }
    ElementManage informationControl;
    public ElementManage getMessage() {
        if (informationControl != null)
            return informationControl;
        else {
            informationControl = new ElementManage(this, By.className("alert"));
            return informationControl;
        }

    }
}
