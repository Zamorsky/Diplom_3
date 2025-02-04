import base.BaseSteps;
import base.BaseTest;
import constants.Path;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.AuthenticationPage;
import pom.ConstructorPage;
import pom.PersonalCabinetPage;
import pom.RegistrationPage;
import api.User;
import api.UserClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTests extends BaseTest {


    private User user;
    private RegistrationPage registrationPage;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        // Генерация случайного пользователя
        user = utils.UserGen.generateRandomUser();

        // Инициализация страницы регистрации
        registrationPage = new RegistrationPage(driver);

        // Открываем страницу регистрации
        openPage(Path.REGISTER_PATH);

        // Ожидание загрузки элемента кнопки регистрации
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPage.registerButton));
    }

    @Test
    @DisplayName("Успешная регистрация")

    public void testSuccessfulRegistration() {
        // Заполняем форму регистрации
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        BaseSteps.waitForVisibility(driver, authenticationPage.loginButton);

        assertTrue("Не открылась страница аутентификации",authenticationPage.isPageOpened());
        //Пытаемся авторизоваться под данными, под которыми регистрировались
        authenticationPage.login(user.getEmail(), user.getPassword());
        //происходит перенаправление на страницу конструктора
        ConstructorPage constructorPage = new ConstructorPage(driver);

        BaseSteps.waitForVisibility(driver, authenticationPage.loginButton);

        assertTrue("Не открылась страница конструктора",constructorPage.isOrderButtonDisplayed());
        constructorPage.clickPersonalCabinetButton(); // Нажать на "Личный кабинет"
        //открылась страница личного кабинета

        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        assertTrue("Личный кабинет не открылся", personalCabinetPage.isPersonalCabinetPageOpened());
        BaseSteps.deleteUser(user);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля при регистрации")

    public void testShortPasswordError() {
        // Данные для теста
        String shortPassword = "123j!";  // Пароль меньше 6 символов

        // Заполняем форму регистрации
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(shortPassword);
        registrationPage.clickRegisterButton();

        // Проверяем отображение ошибки
        String errorMessage = registrationPage.getErrorMessage();
        assertEquals("Ошибка не была отображена или неверное сообщение", "Некорректный пароль", errorMessage);
    }

    @After
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
