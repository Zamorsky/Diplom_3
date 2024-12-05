package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver,constants.Path.REGISTER_PATH);
    }

    private final By title = By.xpath(".//h2[text() = 'Регистрация']");
    private final By nameField  = By.xpath(".//label[text() = 'Имя']/../input");
    private final By emailField  = By.xpath(".//label[text() = 'Email']/../input");
    private final By passwordField  = By.xpath(".//form/.//label[text() = 'Пароль']/following::input[1]");
    private final By errorNotification = By.xpath(".//p[contains(@class, 'input__error')]");
    private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");
    private final By incorrectPass = By.xpath(".//p[text() = 'Некорректный пароль']");

    // Методы для взаимодействия с элементами страницы
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name); // Ввод имени
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email); // Ввод email
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password); // Ввод пароля
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click(); // Клик по кнопке регистрации
    }

    public String getErrorMessage() {
        return driver.findElement(errorNotification).getText(); // Получение текста ошибки
    }

    public void clickLoginLink() {
        driver.findElement(loginButton).click(); // Переход по ссылке "Войти"
    }

}
