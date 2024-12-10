import api.User;
import api.UserClient;
import base.BaseSteps;
import base.BaseTest;
import constants.Path;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.RegistrationPage;

import pom.ForgotPasswordPage;
import pom.AuthenticationPage;
import pom.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class AuthenticationTests extends BaseTest {

    private UserClient userClient; // Класс для взаимодействия с API (отправка запросов к серверу)
    private User user; // Пользователь, которого будем создавать (объект для тестов)

    @Before
    @Override
    public void setUp() {
        super.setUp();
        user = utils.UserGen.generateRandomUser();
        userClient = new UserClient();
        userClient.createUser(user);

    }


    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginFromConstructorPage() {
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.isLoginButtonDisplayed();
        constructorPage.clickLoginButton(); // Нажать кнопку "Войти в аккаунт"

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        assertTrue("Страница входа не открылась", authenticationPage.isPageOpened());
        authenticationPage.login(user.getEmail(), user.getPassword());
        BaseSteps.waitForVisibility(driver, constructorPage.orderButton);
        assertTrue("Аутентификация не произошла. Кнопки оформить заказ нету.", constructorPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testLoginFromPersonalCabinet() {
        openPage(Path.CONSTRUCTOR_PATH);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.isLoginButtonDisplayed(); //главная страница открылась
        constructorPage.clickPersonalCabinetButton(); // Нажать кнопку "личный кабинет"

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        assertTrue("Страница входа не открылась", authenticationPage.isPageOpened());
        authenticationPage.login(user.getEmail(), user.getPassword());
        BaseSteps.waitForVisibility(driver, constructorPage.orderButton);
        assertTrue("Аутентификация не произошла. Кнопки оформить заказ нету.", constructorPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationPage() {
        openPage(Path.REGISTER_PATH);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.isLoginButtonDisplayed(); //страница регистрации открылась
        registrationPage.clickLoginLink(); //нажать на кнопку войти

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        assertTrue("Страница входа не открылась", authenticationPage.isPageOpened()); //страница логина открылась
        authenticationPage.login(user.getEmail(), user.getPassword());

        ConstructorPage constructorPage = new ConstructorPage(driver);

        BaseSteps.waitForVisibility(driver, constructorPage.orderButton);

        assertTrue("Аутентификация не произошла. Кнопки оформить заказ нету.", constructorPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordPage() {
        openPage(Path.FORGOT_PASSWORD_PATH);
        ForgotPasswordPage forgotPasswordPage= new ForgotPasswordPage(driver);
        BaseSteps.waitForVisibility(driver, forgotPasswordPage.loginButton);

        forgotPasswordPage.isLoginButtonDisplayed(); //страница восстановления пароля открылась
        forgotPasswordPage.clickLoginLink(); //нажать на кнопку войти

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        assertTrue("Страница входа не открылась", authenticationPage.isPageOpened()); //страница логина открылась
        authenticationPage.login(user.getEmail(), user.getPassword());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        BaseSteps.waitForVisibility(driver, constructorPage.orderButton);
        assertTrue("Аутентификация не произошла. Кнопки оформить заказ нету.", constructorPage.isOrderButtonDisplayed());
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