package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public final By loginButton = By.xpath(".//a[text() = 'Войти']");
    public final By personalCabinetButton = By.xpath("//a[contains(@href,'/account')]");



    @Step("Нажатие ссылки Войти")
    public void clickLoginLink() {
        driver.findElement(loginButton).click();
    }

    @Step("Страница восстановления пароля открылась. Кнопка <Войти> доступна")
    public void isLoginButtonDisplayed() {
        driver.findElement(loginButton).isDisplayed();
    }



    @Step("Нажать кнопку <личный кабинет>")
    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

}


