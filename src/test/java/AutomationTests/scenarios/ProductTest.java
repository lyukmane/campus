package AutomationTests.scenarios;

import AutomationTests.pages.LoginPage;
import AutomationTests.pages.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductTest {
    private static ProductPage productPage;
    private static LoginPage loginPage;
    @BeforeAll
    @Step("Set up driver before tests")
    static void init() {
        BaseSetUp baseSetUp = new BaseSetUp();
        loginPage = PageFactory.initElements(baseSetUp.driver, LoginPage.class);
        productPage = PageFactory.initElements(baseSetUp.driver, ProductPage.class);
    }

    @RegisterExtension
    ScreenshotOnFailure watcher = new ScreenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");
    @Test
    @Story("Product add, update and delete")
    @Issue("issue-12345")
    @Description("Delete product after update")
    public void delete_product(){
        //GIVEN
        WebDriverWait wait = new WebDriverWait(BaseSetUp.driver, Duration.ofMillis(300L));
        By locator_item_deleted = By.xpath(
                "//td/text()[contains(.,'TestNameUpdate')]");
        //WHEN
        loginPage.openLoginPage();
        loginPage.submit();
        productPage.click_button_delete();
        //THEN
        boolean item_deleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator_item_deleted));
        Assertions.assertTrue(item_deleted);

    }

    @Test
    @Step("Exit user from site")
    public void exit_user(){
        //WHEN
        loginPage.openLoginPage();
        loginPage.submit();
        productPage.click_button_logout();
        String actualCurrentUrl = BaseSetUp.driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals("http://online-sh.herokuapp.com/login", actualCurrentUrl);
    }

    @Test
    @Step("Exit user from site")
    public void check_all_products_is_present(){
        //WHEN
        loginPage.openLoginPage();
        loginPage.submit();
        productPage.checkAllProductsVievisPresent();
        productPage.checkProductsCountIsEquelToExpected(12);
    }
    @Test
    @Step("Add new product")
    public void add_new_product(){
        //GIVEN
        String nameProduct= "TestNameProduct";
        String priceProduct = "123456";
        //WHEN
        loginPage.openLoginPage();
        loginPage.submit();
        productPage.click_add_product_button();
        String actualCurrentUrl_add = BaseSetUp.driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals("http://online-sh.herokuapp.com/products/add", actualCurrentUrl_add);
        //WHEN
        productPage.set_name_product(nameProduct);
        productPage.set_price_product(priceProduct);
        productPage.click_add_product_button();
        String actualCurrentUrl_product = BaseSetUp.driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", actualCurrentUrl_product);
    }

    @Test
    @Step("Update new product after add")
    public void update_new_product() throws InterruptedException {
        //WHEN
        loginPage.openLoginPage();
        loginPage.submit();
        productPage.click_button_update();
        productPage.set_name_product("TestNameUpdate");
        productPage.set_price_product("321");
        productPage.click_submit_update_product_button();
        String actualCurrentUrl = BaseSetUp.driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", actualCurrentUrl);
    }

    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        BaseSetUp.driver.quit();
    }
}
