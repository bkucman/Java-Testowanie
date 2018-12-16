package pageObject;

import org.openqa.selenium.By;
import pageObject.ElementManage;
import pageObject.MainClassPage;

public class TextFieldManage extends ElementManage {
    public TextFieldManage(MainClassPage page, By by) {
        super(page, by);
    }
    public TextFieldManage(ElementManage control, MainClassPage page, By by) {
        super(control, page, by);
    }

    public void setText(String text)
    {
        element.click();
        //element.clear();
        element.sendKeys(text);
    }
    @Override
    public String getText()
    {
        return element.getAttribute("value");
    }
}
