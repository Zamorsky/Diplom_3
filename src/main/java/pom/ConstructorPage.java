package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ConstructorPage extends BasePage {

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }


    public By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    public By orderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By personalCabinetButton = By.xpath("//a[contains(@href,'/account')]");

    // Локаторы переключателей вкладок
    public final By bunsSwitch = By.xpath("//span[text()='Булки']");
    public final By saucesSwitch = By.xpath("//span[text()='Соусы']");
    public final By fillingsSwitch = By.xpath("//span[text()='Начинки']");

    public final By bunsScroll = By.xpath("//h2[text()='Булки']");
    public final By saucesScroll = By.xpath("//h2[text()='Соусы']");
    public final By fillingsScroll = By.xpath("//h2[text()='Начинки']");

    public final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span");


    @Step("Клик по вкладке Булки")
    public void clickBunsTab() {
        driver.findElement(bunsSwitch).click();
    }

    @Step("Клик по вкладке Соусы")
    public void clickSaucesTab() {
        driver.findElement(saucesSwitch).click();
    }

    @Step("Клик по вкладке Начинки")
    public void clickFillingsTab() {
        driver.findElement(fillingsSwitch).click();
    }




    @Step("Нажать кнопку войти в аккаунт")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать кнопку <личный кабинет>")
    public void clickPersonalCabinetButton() {
            driver.findElement(personalCabinetButton).click();
    }

    @Step("Страница конструктора открылась. Кнопка <оформить заказ> доступна")
    public boolean isOrderButtonDisplayed() {
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Страница конструктора открылась. Кнопка <войти в аккаунт> доступна")
    public void isLoginButtonDisplayed() {
        driver.findElement(loginButton).isDisplayed();
    }

    @Step("Прокрутка до элемента")
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


//    @Step("Ожидание что элемент стал активным")
//    public void waitForFillingsTabToBeActive() {
//        ConstructorPage constructorPage = new ConstructorPage(driver);
//
//        // Ожидание до тех пор, пока вкладка "Начинки" не станет активной
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(constructorPage.fillingsIsSelected));
//    }


}