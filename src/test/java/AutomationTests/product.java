package AutomationTests;

import calc.my_Calculator;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class product {
    private static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeEach
    void init() {
        System.out.println("before each called");
        System.setProperty("webdriver.chrome.driver", "/Users/dima/Applications/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        }

    @Test
    public void open_form_user_registration() {
        //GIVEN
        String expectedUrl = "http://online-sh.herokuapp.com/registration";
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement inputEmail = driver.findElement(By.id("exampleInputEmail1"));
        //WHEN
        inputEmail.sendKeys("dmytroessay1@gmail.com");
        inputEmail.sendKeys(Keys.ENTER);
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }

    @Test
    public void new_user_registration() throws InterruptedException {
        //GIVEN
        String expectedUrl = "http://online-sh.herokuapp.com/login";
        driver.get("http://online-sh.herokuapp.com/registration");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        WebElement inputName = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name")));
        WebElement inputLastName = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("last_Name")));
        WebElement inputEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement inputPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement submit = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary")));

        //WHEN
        inputName.sendKeys("Dmytro");
        inputLastName.sendKeys("Test");
        inputEmail.sendKeys("dmytroessay1@gmail.com");
        inputPassword.sendKeys("123456");
        submit.click();
        String actualCurrentUrl = driver.getCurrentUrl();

        //THEN
        Thread.sleep(800l);
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }

    @Test
    public void login_existing_user() throws InterruptedException {
        //GIVEN
        String expectedUrl = "http://online-sh.herokuapp.com/products";
        driver.get("http://online-sh.herokuapp.com/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));

        WebElement inputEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exampleInputEmail1")));
        WebElement inputPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exampleInputPassword1")));
        WebElement submit = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary")));
        //WHEN
        inputEmail.sendKeys("dmytroessay1@gmail.com");
        inputPassword.sendKeys("123456");
        submit.click();

        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Thread.sleep(800l);
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }

    @Test
    public void add_new_product() throws InterruptedException {
        //GIVEN
        driver.get("http://online-sh.herokuapp.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        WebElement submit_login = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector(".btn.btn-primary")));
        submit_login.click();
        WebElement button_add_product = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector(".btn.btn-outline-success")));
        button_add_product.click();
        WebElement inputNameProduct = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.id("exampleInputProduct1")));
        WebElement inputPriceProduct = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.id("exampleInputPrice1")));
        WebElement submit = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector(".btn.btn-outline-success")));

        //WHEN


        inputNameProduct.sendKeys("NewProduct");
        inputPriceProduct.sendKeys("123");
        submit.click();

        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Thread.sleep(800l);
//        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
    }

