package AutomationTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public FirstTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/dima/Applications/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000L));
    }


    @Test
    public void testSearchResultPresent_JsInput() {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        //WHEN
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // set the text
        jsExecutor.executeScript("arguments[0].value='QA'", input);
        input.sendKeys(Keys.ENTER);

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));
        driver.close();
        driver.quit();

    }

    @Test
    public void shouldNavigate() throws MalformedURLException {
        //GIVEN
        String testUrl = "https://www.google.com/";
        String expectedPageTitle = "google";
        //WHEN
        driver.navigate().to(new URL(testUrl));
        String actualPageTitle = driver.getTitle();
        //THEN
        Assertions.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Test
    public void shouldSearch_byId() {
        //GIVEN
        String expectedHeaderText = "Available relative locators";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.id("not existing"));
        //THEN
        Assertions.assertTrue(headerElements.isEmpty());
    }

    @Test
    public void shouldSearch_byName() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInputElement = driver.findElement(By.name("email"));
        //THEN
        Assertions.assertTrue(emailInputElement.isEnabled());
    }

    @Test
    public void shouldSearch_byClassName() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement submitButtonElement = driver.findElement(By.className("btn-primary"));
        //THEN
        Assertions.assertTrue(submitButtonElement.isEnabled());
    }

    @Test
    public void shouldSearch_byClassName_multipleElements() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> inputElements = driver.findElements(By.className("form-control"));

        //THEN
        Assertions.assertEquals(2, inputElements.size());

    }

    @Test
    public void shouldSearch_byTagName() {
        //GIVEN
        int expectedUlsCount = 35;
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.tagName("ul"));
        //THEN
        Assertions.assertEquals(expectedUlsCount, headerElements.size());
    }

    @Test
    public void shouldSearch_byLink() {
        //GIVEN
        String expectedUrl = "https://www.selenium.dev/documentation/about/contributing/";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        WebElement linkElement = driver.findElement(By.partialLinkText("contribution"));
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }

    @Test
    public void shouldSearch_byCssSelector() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement inputElement =
                driver.findElement(By.cssSelector("input[type='Password']"));
        inputElement.sendKeys("verySecurePassword");
        //THEN
        Thread.sleep(1000L);

    }

    @Test
    public void shouldSearch_byCssSelector_parentChild() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector("ul > li"));
        //THEN
        Assertions.assertEquals(177, elements.size());

    }

    @Test
    public void shouldSearch_byCssSelector_complexStructure() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector("#tabs-10 > li:nth-child(1) > a"));
        //THEN
        Assertions.assertEquals(1, elements.size());

        WebElement firstElement =  elements.get(0);
        String classAttributeValue = firstElement.getAttribute("class");
        boolean isActive = classAttributeValue.contains("active");

        boolean isElementActive = elements.get(0).getAttribute("class").contains("active");

        Assertions.assertTrue(isActive);

    }

    @Test
    public void byXpath() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/");
        WebElement headerElement = driver.findElement(By.xpath("(//div[@class='entry'])[3]//a"));
        headerElement.click();
        String currentUrl = driver.getCurrentUrl();
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("browser/");
    }

    @Test
    public void byRelativeLocator() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");
        Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
        Duration timeout = driver.manage().timeouts().getImplicitWaitTimeout();
        String currentTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(3000L);
        driver.switchTo().window(currentTab);
        //THEN
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));

    }

    @Test
    public void testClick() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");

        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");

        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");
        String classAttributeValue = passwordInput.getAttribute("class");
        String text = passwordInput.getText();

        //THEN
        boolean isEnabled = passwordInput.isEnabled();
        Assertions.assertTrue(isEnabled);
        boolean isDisplayed = passwordInput.isDisplayed();
        Assertions.assertTrue(isDisplayed);

        //WHEN
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]"));
        submitButton.click();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @Test
    public void testSubmitForm() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");

        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");


        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");

        passwordInput.submit();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @Test
    public void clickCheckbox() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        WebElement checkboxElementInput = driver.findElement(By.xpath
                ("//label[contains(.,' Default checkbox')]//preceding-sibling::input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", checkboxElementInput);

        //THEN
        Assertions.assertFalse(checkboxElementInput.isSelected());

        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkboxElementInput);
        Thread.sleep(3000L);
        //THEN
        Assertions.assertTrue(checkboxElementInput.isSelected());

    }

    @Test
    public void clickRadioButton() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/");
        driver.manage().window().maximize();
        driver.switchTo().frame(1);

        WebElement radio_button = driver.findElement(By.xpath("//*[@id='M']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", radio_button);
        radio_button.click();


        //THEN
        Assertions.assertTrue(radio_button.isSelected());


    }

    @Test
    public void shouldSearch_byLink_Alerts() {
        //GIVEN
        String expectedUrl = "https://t.me/air_alert_ua";
        //WHEN
        driver.get("https://alerts.in.ua/");
        String mainWindow = driver.getWindowHandle();
        WebElement linkElement = driver.findElement(By.xpath("(//a[text()[contains(.,'Повітряна тривога')]])[1]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", linkElement);

        //THEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandler : driver.getWindowHandles()) {
            if(!mainWindow.contentEquals(windowHandler)) {
                driver.switchTo().window(windowHandler);
                break;
            }
        }

        String actualCurrentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualCurrentUrl);
        //WHEN
        driver.switchTo().window(mainWindow);

    }

    @Test
    public void testSelect() {
        //GIVEN
        //WHEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/select/");
        WebElement selectElement = driver.findElement(By.xpath("(//select[@class='form-select'])[1]"));
        Select select = new Select(selectElement);
        //select.selectByVisibleText("Two");
        select.selectByValue("1");
        try {
            Thread.sleep(800l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //THEN
        WebElement firstSelectedOption = select.getFirstSelectedOption();
        Assertions.assertEquals("One", firstSelectedOption.getText());
        Assertions.assertTrue(firstSelectedOption.isSelected());
        Assertions.assertFalse(select.isMultiple());

        //WHEN
        List<WebElement> options = select.getOptions();

        //THEN
        Assertions.assertEquals(4, options.size());

        //WHEN
        //select.deselectAll();

    }

    @Test
    public void testSearchResultPresent_seleniumInput() throws InterruptedException {
        //GIVEN
        driver.get("https://www.google.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
        Actions actions = new Actions(driver);
        //WHEN
        //actions.moveToElement(gmailLink).click().perform();
        //Thread.sleep(100L);

        //WHEN
        //input.sendKeys("QA automation");
        //input.sendKeys(Keys.ENTER);
        //actions.sendKeys(input, "QA Automation").sendKeys(Keys.ENTER).perform();
        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ulc")));



    }

    @Test
    public void test_cookie() throws InterruptedException {
        //GIVEN

        driver.get("https://demo.guru99.com/test/cookie/selenium_aut.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));

        WebElement inputEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        //WHEN
        inputEmail.sendKeys("abc123");
        //Given;
        WebElement inputPass = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        //WHEN
        inputPass.sendKeys("123xyz");
        //Given
        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(
                "submit")));
        //WHEN
        submitButton.click();
        Set<Cookie> authCookie = driver.manage().getCookies();
        System.out.println(authCookie);

        ChromeDriver driver2 = new ChromeDriver();
// Skip login
        driver2.get("https://demo.guru99.com/test/cookie/selenium_aut.php");
// Adding authorization cookie
        driver2.manage().addCookie((Cookie) authCookie);
// Then get authorized
        driver2.get("https://demo.guru99.com/test/cookie/selenium_cookie.php");

    }


    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}

