package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private final By nameField  = By.xpath(".//label[text() = 'Имя']/../input");
    private final By emailField  = By.xpath(".//label[text() = 'Email']/../input");
    private final By passwordField  = By.xpath(".//form/.//label[text() = 'Пароль']/following::input[1]");
    private final By errorNotification = By.xpath(".//p[contains(@class, 'input__error')]");
    public final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");
    private final By personalCabinetButton = By.xpath("//a[contains(@href,'/account')]");


    // Методы для взаимодействия с элементами страницы
    @Step("Прописываем имя")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name); // Ввод имени
    }

    @Step("Прописываем почту")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email); // Ввод email
    }

    @Step("Прописываем пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password); // Ввод пароля
    }

    @Step("Нажимаем кнопку регистрация")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click(); // Клик по кнопке регистрации
    }

    @Step("Получаем сообщение об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorNotification).getText(); // Получение текста ошибки
    }

    @Step("Нажимаем кнопку <Войти>")
    public void clickLoginLink() {
        driver.findElement(loginButton).click(); // Переход по ссылке "Войти"
    }

    @Step("Страница регистрации открылась. Кнопка <Войти> доступна")
    public void isLoginButtonDisplayed() {
        driver.findElement(loginButton).isDisplayed();
    }


    @Step("Нажать кнопку <личный кабинет>")
    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

}
