package AutomationTests.scenarios;

import AutomationTests.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;
import java.io.File;
import java.io.IOException;

public class LoginTest{
    private static LoginPage loginPage;

    @BeforeAll
    @Step("Set up driver before tests")
    static void init() {
        BaseSetUp baseSetUp = new BaseSetUp();
        loginPage = PageFactory.initElements(baseSetUp.driver, LoginPage.class);
    }

    @RegisterExtension
    ScreenshotOnFailure watcher = new ScreenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");
    private void checkUserIsRedirectedToProducts() {
        String currentUrl = BaseSetUp.driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }
    private void checkUserIsRedirectedToRegistration() {
        String currentUrl = BaseSetUp.driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/registration", currentUrl);
    }
    @Test
    @Story("Login successful")
    @Issue("issue-12345")
    @Description("Check login is successful after entering valid credentials")
    public void login_existing_user() {
        //GIVEN

        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail("dmytroessay1@gmail.com");
        loginPage.setPassword("123456");
        loginPage.submit();

        //THEN
        checkUserIsRedirectedToProducts();
    }

    @Test
    @Step("Login non existing user")
    public void login_non_existing_user() {
        //GIVEN

        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail("dmytroessay1@gmail.com");
        loginPage.setPassword("123");
        loginPage.submit();

        //THEN
        checkUserIsRedirectedToRegistration();
    }

    @Test
    @Step("Check text alert in the input field email ")
    public void check_text_alert() throws IOException {
        //GIVEN
        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail("dmytroessay1");
        loginPage.submit();
        String message = BaseSetUp.driver.findElement(By.cssSelector
                ("#exampleInputEmail1.form-control")).getAttribute("validationMessage");
        boolean messageContent = message.contains(
                "Електронна адреса має містити знак \"@\". В електронній адресі \"dmytroessay1\" знака \"@\" немає.");

        //THEN
        Assertions.assertTrue(messageContent);
        TakesScreenshot screenDriver = (TakesScreenshot) BaseSetUp.driver;
        File screenshotAs = screenDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File("/Users/dima/Documents/Testscreen.png"));
    }

    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        BaseSetUp.driver.quit();
    }
}
