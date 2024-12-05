package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-notifications", "--disable-default-apps", "--disable-extensions"); // Пример: "--no-sandbox", "--headless", "--disable-dev-shm-usage"
                driver = new ChromeDriver(chromeOptions);
                break;

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/yandexdriver.exe");
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("disable-notifications", "--disable-default-apps", "--disable-extensions"); // Пример: "--no-sandbox", "--headless", "--disable-dev-shm-usage"
                driver = new ChromeDriver(yandexOptions);
                break;

            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
