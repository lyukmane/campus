package AutomationTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    public LoginPage setEmail (String emailText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.presenceOfElementLocated(emailInput))
                .sendKeys(emailText);
        return this;
    }

    public LoginPage setPassword (String passwordText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput))
                .sendKeys(passwordText);
        return this;
    }

    public LoginPage submit (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton))
                .click();
        return this;
    }

    public LoginPage openLoginPage (){
        driver.get(baseUrl + "/login");
        return this;
    }
}
