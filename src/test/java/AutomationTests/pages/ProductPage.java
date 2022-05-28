package AutomationTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    By locator_add_product_button = By.cssSelector(".btn.btn-outline-success");
    By locator_name_product = By.id("exampleInputProduct1");
    By locator_price_product = By.id("exampleInputPrice1");
    By locator_update_product_button = By.xpath(
            "//td/text()[contains(.,'TestNameProduct')]//following::button");
    By locator_delete_product_button = By.xpath(
            "//td/text()[contains(.,'TestNameUpdate')]/following::button[@class='btn btn-outline-danger']");
    By locator_submit_update = By.cssSelector(".btn.btn-outline-warning");
    private static WebDriver driver;

    public ProductPage (WebDriver driver){
        this.driver = driver;
    }
    @Step("Click add new product button")
    public ProductPage click_add_product_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement add_product_button = wait.until(ExpectedConditions.presenceOfElementLocated(locator_add_product_button));
        add_product_button.click();
        return this;
    }
    @Step("Click update new product button")
    public ProductPage click_submit_update_product_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement add_product_button = wait.until(ExpectedConditions.presenceOfElementLocated(locator_submit_update));
        add_product_button.click();
        return this;
    }
    @Step("Set name product {text_name} in the field name product")
    public ProductPage set_name_product(String text_name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement inputNameProduct = wait.until(ExpectedConditions.
                presenceOfElementLocated(locator_name_product));
        inputNameProduct.clear();
        inputNameProduct.sendKeys(text_name);
        return this;
    }
    @Step("Set price product {text_price} in the field price product")
    public ProductPage set_price_product(String text_price) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement inputPriceProduct = wait.until(ExpectedConditions.
                presenceOfElementLocated(locator_price_product));
        inputPriceProduct.clear();
        inputPriceProduct.sendKeys(text_price);
        return this;
    }
    @Step("Click update button in the products page")
    public ProductPage click_button_update () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement button_update_product = wait.until(ExpectedConditions.
                presenceOfElementLocated(locator_update_product_button));
        button_update_product.click();
        return this;
    }

    @Step("Click delete button in the products page")
    public ProductPage click_button_delete () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        WebElement button_delete_product = wait.until(ExpectedConditions.
                presenceOfElementLocated(locator_delete_product_button));
        button_delete_product.click();
        return this;
    }
    @Step("Open products page")
    public ProductPage open_product_page(){
        driver.get("http://online-sh.herokuapp.com/products");
        return this;
    }
}
