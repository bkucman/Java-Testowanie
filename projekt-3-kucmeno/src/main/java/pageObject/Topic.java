package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Topic extends ElementManage {
    public Topic(MainClassPage page, WebElement element) {
        super(page, element);
    }

    public Topic(MainClassPage page, By by) {
        super(page, by);
    }

    public Topic(ElementManage control, MainClassPage page, By by) {
        super(control, page, by);
    }


}
