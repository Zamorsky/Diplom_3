package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;

    }
    public By stellarLogo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");
    public By constructorLogo = By.xpath("//p[contains(text(), 'Конструктор')]");


    @Step("Нажать на логотип Stellar Burger")
    public void clickStellarLogo() {
        driver.findElement(stellarLogo).click();
    }

    @Step("Нажать на логотип кнопку конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorLogo).click();
    }

}