package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class PersonalCabinetPage extends BasePage {
    public PersonalCabinetPage(WebDriver driver) {
        super(driver);
    }
    public final By profileText = By.xpath(".//a[text()='Профиль']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");




    @Step("Личный кабинет открылся")
    public boolean isPersonalCabinetPageOpened() {
        return driver.findElement(profileText).isDisplayed();
    }

    @Step("Нажатие кнопки Выход")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }




}


