package AutomationTests.scenarios;

import AutomationTests.pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginTest{
    private static WebDriver driver;
    private static LoginPage loginPage;

    public LoginTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/dima/Applications/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);

    }

    @Test
    public void login_existing_user() {
        //GIVEN
        String expectedUrl = "http://online-sh.herokuapp.com/products";

        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail("dmytroessay1@gmail.com");
        loginPage.setPassword("123456");
        loginPage.submit();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl);
    }

    @Test
    public void login_non_existing_user() throws InterruptedException {
        //GIVEN
        String expectedUrl = "http://online-sh.herokuapp.com/registration";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));

        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail("dmytroessay1@gmail.com");
        loginPage.setPassword("123");
        loginPage.submit();
        String actualCurrentUrl = driver.getCurrentUrl();

        //THEN
        Thread.sleep(800l);
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);
    }

    @Test
    public void check_text_alert() throws IOException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));

        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail("dmytroessay1");
        loginPage.submit();
        String message = driver.findElement(By.cssSelector
                ("#exampleInputEmail1.form-control")).getAttribute("validationMessage");
        System.out.println(message);
        boolean messageContent = message.contains(
                "Електронна адреса має містити знак \"@\". В електронній адресі \"dmytroessay1\" знака \"@\" немає.");

        //THEN
        Assertions.assertTrue(messageContent);
        TakesScreenshot screenDriver = (TakesScreenshot) driver;
        File screenshotAs = screenDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File("/Users/dima/Documents/Testscreen.png"));
    }


    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
