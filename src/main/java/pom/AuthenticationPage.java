package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthenticationPage extends BasePage{
    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public By email = By.xpath(".//label[text() = 'Email']/../input");
    private By password = By.xpath(".//label[text() = 'Пароль']/../input");
    public By loginButton = By.xpath(".//button[text() = 'Войти']");




    @Step("Аутентификация")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();

    }

    @Step("Ввод Email")
    public void setEmail(String email) {
        driver.findElement(this.email).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }

    @Step("Нажать на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Страница аутентификации открылась, кнопка <Войти> доступна")
    public boolean isPageOpened() {
        return driver.findElement(loginButton).isDisplayed();
    }
}
