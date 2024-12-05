package base;

import constants.URLs;
import utils.DriverFactory;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;
    protected String relativePath = ""; // Относительный путь страницы

    @Before
    public void setUp() {
        // Читаем параметр браузера из системных свойств (по умолчанию Chrome)
        String browser = System.getProperty("browser", "chrome");

        // Инициализируем драйвер через фабрику
        driver = DriverFactory.getDriver(browser);

        // Открытие базовой страницы
        openPage(relativePath);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        //надо удалить пользователя через API, если он был создан
    }

    @Step("Открыть страницу: {relativePath}")
    public void openPage(String relativePath) {
        driver.get(URLs.URL_STELLAR_BURGERS + relativePath);
    }


}
