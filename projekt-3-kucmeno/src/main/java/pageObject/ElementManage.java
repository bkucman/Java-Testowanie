package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ElementManage {
    protected MainClassPage mcPage;
    protected WebElement element;
    FluentWait<WebDriver> wait;

    public ElementManage (MainClassPage page, WebElement element)
    {

        this.mcPage = page;
        this.element = element;
        prepareWait();
        waitForElementBy();
    }
    public ElementManage(MainClassPage page, By by)
    {

        this.mcPage = page;
        prepareWait();
        waitForElementBy(by);
        element = page.driver.findElement(by);
    }

    public ElementManage(ElementManage control, MainClassPage page, By by)
    {

        this.mcPage = page;
        element = control.element.findElement(by);
        prepareWait();
    }

    public WebElement getElement()
    {
        return element;
    }

    public void click()
    {

        waitForElementBy();
        waitForElementToBeClickable();
        element.click();
        mcPage.waitForLoad();
    }

    public boolean isVisible()
    {
        return element.isDisplayed();
    }

    public String getText()
    {
        return element.getText();
    }

    public void waitForElementBy(By by)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementBy()
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable()
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void prepareWait(){
        wait = new FluentWait<WebDriver>(mcPage.driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public void waitForStaleness()
    {
        Wait<WebDriver> wait = new WebDriverWait(mcPage.driver, 3);
        wait.until(ExpectedConditions.stalenessOf(getElement()));
    }
}
