package com.projekt3.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pageObject.HomePage;
import pageObject.LogInPage;

import java.util.concurrent.TimeUnit;

public class CRUDTest extends BaseT {

    public void LogIn(){
        HomePage homePage = new HomePage(driver);
        LogInPage signUpPage = homePage.goToLogInPage();
        signUpPage.getEmailField().setText("moderator@example.com");
        signUpPage.getPasswordField().setText("moderator123");
        HomePage homePage1 = signUpPage.login();
    }
    @Test
    public void test(){

        LogIn();
        driver.findElement(By.linkText("Tematy")).click();

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("New Topic")));

        driver.findElement(By.partialLinkText("New Topic")).click();

        driver.findElement(By.name("topic[name]")).sendKeys("Nowy");
        driver.findElement(By.name("topic[desc]")).sendKeys("Bardzo fajny");

        driver.findElement(By.name("commit")).click();

        Assertions.assertNotNull(driver.findElement(By.className("alert")).getText());
    }

    @Disabled
    @Test
    public void testDrop(){
        LogIn();

        driver.findElement(By.linkText("Tematy")).click();

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("New Topic")));
        driver.findElement(By.partialLinkText("Sortuj od najnowszych")).click();
        driver.findElement(By.partialLinkText("Wyświetl")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Usuń")));
        driver.findElement(By.partialLinkText("Usuń")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert")));


        Assertions.assertNotNull(driver.findElement(By.className("alert")).getText());
    }
}
