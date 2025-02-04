import api.User;
import api.UserClient;
import base.BaseSteps;
import base.BaseTest;
import constants.Path;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


import pom.*;

import static org.junit.Assert.assertTrue;

public class PersonalCabinetNavigationTests extends BaseTest {

    private UserClient userClient; // Класс для взаимодействия с API
    private User user; // Пользователь для тестов

    @Before
    @Override
    public void setUp() {
        super.setUp();
        // Генерация и создание пользователя через API
        user = utils.UserGen.generateRandomUser();
        userClient = new UserClient();
        userClient.createUser(user);

        openPage(Path.LOGIN_PATH);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.login(user.getEmail(), user.getPassword());
        ConstructorPage constructorPage = new ConstructorPage(driver);

        BaseSteps.waitForVisibility(driver, constructorPage.orderButton);

    }


    @Test
    @DisplayName("Переход в личный кабинет со страницы конструктора")
    public void navigateToPersonalCabinetFromConstructorPage() {
        // Открываем страницу конструктора
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickPersonalCabinetButton(); // Нажать на "Личный кабинет"
        //открылась страница личного кабинета
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        // Проверяем, что личный кабинет открылся
        assertTrue("Личный кабинет не открылся со страницы конструктора", personalCabinetPage.isPersonalCabinetPageOpened());
    }

    @Test
    @DisplayName("Переход в личный кабинет со страницы регистрации")
    public void navigateToPersonalCabinetFromRegistrationPage() {
        openPage(Path.REGISTER_PATH); // Открываем страницу регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickPersonalCabinetButton(); // Нажать на "Личный кабинет"
        //открылась страница личного кабинета
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        // Проверяем, что личный кабинет открылся
        assertTrue("Личный кабинет не открылся со страницы регистрации", personalCabinetPage.isPersonalCabinetPageOpened());
    }

    @Test
    @DisplayName("Переход в личный кабинет со страницы забытого пароля")
    public void navigateToPersonalCabinetFromForgotPasswordPage() {
        openPage(Path.FORGOT_PASSWORD_PATH); // Открываем страницу восстановления пароля
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickPersonalCabinetButton(); // Нажать на "Личный кабинет"
        //открылась страница личного кабинета
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        // Проверяем, что личный кабинет открылся
        assertTrue("Личный кабинет не открылся со страницы восстановления пароля", personalCabinetPage.isPersonalCabinetPageOpened());
    }



    @After
    @Override
    public void tearDown() {
        super.tearDown();

        // Проверяем, что пользователь был создан перед его удалением
        if (user != null) {
            // Удаляем пользователя через API после каждого теста
            BaseSteps.deleteUser(user);
        }
    }
}
