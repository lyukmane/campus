package AutomationTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    By emailInput = By.id("exampleInputEmail1");
    By passwordInput = By.id("exampleInputPassword1");
    By submitButton = By.cssSelector(".btn.btn-primary");
    String baseUrl = "http://online-sh.herokuapp.com";
    private WebDriver driver;

    public LoginPage (WebDriver driver){
        this.driver = driver;
    }


    @Step("Set email as {emailText}")
    public LoginPage setEmail (String emailText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.presenceOfElementLocated(emailInput))
                .sendKeys(emailText);
        return this;
    }
    @Step("Set email as {passwordText}")
    public LoginPage setPassword (String passwordText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput))
                .sendKeys(passwordText);
        return this;
    }
    @Step("Click submit button on the login form")
    public LoginPage submit (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton))
                .click();
        return this;
    }

    @Step("Open login page")
    public LoginPage openLoginPage (){
        driver.get(baseUrl + "/login");
        return this;
    }
}