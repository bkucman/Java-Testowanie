package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NewsPage extends MainClassPage {

    public NewsPage(WebDriver _driver) {
        super(_driver);
    }

    ElementManage newTopicButton;
    public ElementManage getNewTopicButton() {
        if (newTopicButton != null)
            return newTopicButton;
        else {
            newTopicButton = new ElementManage(this, By.linkText("Dodaj nowy news"));
            return newTopicButton;
        }
    }

    News news;
    public News getfirstNews()
    {
        if(news != null)
            return news;
        else
        {
            news = new News(this,By.xpath("(.//div[@class='row'])[1]"));
            return news;
        }
    }

}
