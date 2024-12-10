import base.BaseSteps;
import base.BaseTest;
import constants.Path;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pom.ConstructorPage;

public class ConstructorTabsTests extends BaseTest {


    @Test
    @DisplayName("Проверка активности вкладки 'Булки'")
    public void testDefaultBunsTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание доступности вкладки булки

        BaseSteps.waitForVisibility(driver, constructorPage.bunsSwitch);


        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Булки' не активна!", "Булки", activeTabText);
    }

    @Test
    @DisplayName("Проверка активности вкладки 'Соусы'")
    public void testSaucesTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание доступности вкладки начинки

        BaseSteps.waitForVisibility(driver, constructorPage.saucesSwitch);

        constructorPage.clickSaucesTab(); //нажимаем на вкладку начинки

        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Соусы' не активна!", "Соусы", activeTabText);
    }

    @Test
    @DisplayName("Проверка активности вкладки 'Начинки'")
    public void testFillingsTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание доступности вкладки начинки

        BaseSteps.waitForVisibility(driver, constructorPage.fillingsSwitch);

        constructorPage.clickFillingsTab(); //нажимаем на вкладку начинки

        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Начинки' не активна!", "Начинки", activeTabText);
    }

    @Test
    @DisplayName("Переход со вкладки 'Начинки' на вкладку 'Булки'")
    public void testBunsAfterFillingsTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание доступности вкладки начинки

        BaseSteps.waitForVisibility(driver, constructorPage.fillingsSwitch);
        constructorPage.clickFillingsTab(); //нажимаем на вкладку начинки
        //ожидание доступности вкладки булки

        BaseSteps.waitForVisibility(driver, constructorPage.bunsSwitch);

        constructorPage.clickBunsTab(); //нажимаем на вкладку булки

        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Булки' не активна!", "Булки", activeTabText);
    }

    @Test
    @DisplayName("Проверка активности вкладки 'Начинки' после скролла до неё")
    public void testScrollToFillingsTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание доступности вкладки начинки

        BaseSteps.waitForVisibility(driver, constructorPage.fillingsScroll);

        constructorPage.scrollToElement(constructorPage.fillingsScroll);

        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Начинки' не активна!", "Начинки", activeTabText);
    }

    @Test
    @DisplayName("Проверка активности вкладки 'Соусы' после скролла до неё")
    public void testScrollToSaucesTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание доступности вкладки начинки

        BaseSteps.waitForVisibility(driver, constructorPage.saucesScroll);


        constructorPage.scrollToElement(constructorPage.saucesScroll);

        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Соусы' не активна!", "Соусы", activeTabText);
    }

    @Test
    @DisplayName("Проверка активности вкладки 'Булки' после скролла до 'Начинки' и обратно")
    public void testScrollToBunsTabActive() {
        // Открыть страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидание видимости вкладки начинки
        BaseSteps.waitForVisibility(driver, constructorPage.fillingsScroll);


        //скролл до начинок
        constructorPage.scrollToElement(constructorPage.fillingsScroll);
        //ожидание что вкладка начинки станет активной
        BaseSteps.waitForText(driver, constructorPage.activeTab, "Начинки");
        //скролл обратно до булок
        constructorPage.scrollToElement(constructorPage.bunsScroll);


        WebElement activeTabElement = driver.findElement(constructorPage.activeTab);
        String activeTabText = activeTabElement.getText();
        Assert.assertEquals("Вкладка 'Булки' не активна!", "Булки", activeTabText);
    }
}
