package base;

import constants.URLs;
import utils.DriverFactory;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected static WebDriver driver;

    @Before
    @Step("Инициируем драйвер")
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.getDriver(browser); // Инициализация драйвера
    }

    @After
    @Step("Закрываем браузер")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }



    @Step("Открыть страницу: {relativePath}")
    public void openPage(String relativePath) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver не инициализирован. Проверьте вызов метода setUp().");
        }
        driver.get(URLs.URL_STELLAR_BURGERS + relativePath); // Конкатенация полного пути
    }



}
