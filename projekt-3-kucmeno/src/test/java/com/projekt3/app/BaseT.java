package com.projekt3.app;

import com.beust.jcommander.Parameters;
import io.github.bonigarcia.SeleniumExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


@ExtendWith(SeleniumExtension.class)
public abstract class BaseT {



    protected static WebDriver driver;
    public String browser;

    @BeforeAll
    public static void oneTimeSetUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.operadriver().setup();
    }

    @BeforeEach
    public void setUp() throws Exception {

        driver = new FirefoxDriver();
        driver.get("https://writeo.herokuapp.com/");
    }

    @AfterEach
    public void tearDown()
    {
        if(driver != null)
            driver.quit();
    }
    public static Collection<String> browsers(){
        return Arrays.asList(new String[] {"FF","Chrome"});
    }

    @AfterAll
    public static void oneTimeTearUp()
    {
        if(driver != null)
            driver.quit();
    }

}
