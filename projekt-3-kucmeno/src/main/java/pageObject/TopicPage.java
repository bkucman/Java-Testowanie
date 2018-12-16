package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopicPage extends MainClassPage  {
    public TopicPage(WebDriver _driver) {
        super(_driver);
    }

    ElementManage newTopicButton;
    public ElementManage getNewTopicButton() {
        if (newTopicButton != null)
            return newTopicButton;
        else {
            newTopicButton = new ElementManage(this, By.linkText("New Topic"));
            return newTopicButton;
        }
    }





    News topic;
    public News getfirstTopic()
    {
        if(topic != null)
            return topic;
        else
        {
            topic = new News(this,By.xpath("(.//div[@class='row'])[1]"));
            return topic;
        }
    }
}
