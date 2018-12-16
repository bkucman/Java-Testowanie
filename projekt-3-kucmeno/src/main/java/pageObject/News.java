package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class News extends ElementManage {


    public News(MainClassPage page, WebElement element) {
        super(page, element);
    }

    public News(MainClassPage page, By by) {
        super(page, by);
    }

    public News(ElementManage control, MainClassPage page, By by) {
        super(control, page, by);
    }

    ElementManage topic;

    public ElementManage getTopic() {
        if( topic != null)
            return topic;
        else{
            topic = new ElementManage(this,mcPage, By.xpath("//h2"));
            return topic;
        }
    }

    ElementManage buttonEdit;
    public ElementManage getButtonEdit(){
        if( buttonEdit != null)
            return buttonEdit;
        else{
            buttonEdit = new ElementManage(this,mcPage,By.xpath("(.//a)[2]"));
            return buttonEdit;
        }
    }

    ElementManage buttonShow;
    public ElementManage getButtonShow(){
        if( buttonShow != null)
            return buttonShow;
        else{
            buttonShow = new ElementManage(this,mcPage,By.xpath("(.//a)[1]"));
            return buttonShow;
        }
    }

    ElementManage buttonDestroy;
    public ElementManage getButtonDestroy(){
        if( buttonDestroy != null)
            return buttonDestroy;
        else{
            buttonDestroy = new ElementManage(this,mcPage,By.xpath("(.//a)[3]"));
            return buttonDestroy;
        }
    }

    public NewsPage edit()
    {
        getButtonEdit().click();
        return new NewsPage(mcPage.driver);
    }
    public NewsPage show()
    {
        getButtonShow().click();
        return new NewsPage(mcPage.driver);//Details
    }

    public NewsPage destroy()
    {
        getButtonDestroy().getElement().click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = mcPage.driver.switchTo().alert();
        alert.accept();
        getButtonDestroy().waitForStaleness();
        return new NewsPage(mcPage.driver);
    }
}
