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

public class FromPersonalCabinetToConstructorTests extends BaseTest {

    private UserClient userClient; // Класс для взаимодействия с API
    private User user; // Пользователь для тестов

    @Before
    @Override
    public void setUp() {
        super.setUp(); // Инициализация драйвера и базовых настроек

        // Генерация случайного пользователя и создание через API
        user = utils.UserGen.generateRandomUser(); // Создаем пользователя с случайными данными
        userClient = new UserClient(); // Инициализируем клиент для работы с API
        userClient.createUser(user);// Отправляем запрос на создание пользователя



        // Авторизация пользователя
        openPage(Path.LOGIN_PATH); // Открываем страницу авторизации
        AuthenticationPage authenticationPage = new AuthenticationPage(driver); // Создаем объект страницы авторизации
        authenticationPage.login(user.getEmail(), user.getPassword()); // Вводим логин и пароль, выполняем вход

        // Переход в личный кабинет

        ConstructorPage constructorPage = new ConstructorPage(driver);        // Создаем объект страницы конструктора

        BaseSteps.waitForVisibility(driver, authenticationPage.loginButton);
        assertTrue("Не открылась страница конструктора",constructorPage.isOrderButtonDisplayed());
        constructorPage.clickPersonalCabinetButton(); // Кликаем по кнопке перехода в личный кабинет
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver); // Создаем объект страницы личного кабинета
        // Проверяем, что личный кабинет открылся
        assertTrue("Личный кабинет не открылся", personalCabinetPage.isPersonalCabinetPageOpened());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void navigateToConstructorViaConstructorButton() {
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver); // Создаем объект страницы личного кабинета

        // Клик по кнопке "Конструктор"
        personalCabinetPage.clickConstructorButton(); // Переходим в раздел "Конструктор"

        // Проверка, что открылась страница конструктора
        ConstructorPage constructorPage = new ConstructorPage(driver); // Создаем объект страницы конструктора
        // Убеждаемся, что кнопка оформления заказа отображается, что подтверждает успешное открытие страницы конструктора
        assertTrue("Страница конструктора не открылась", constructorPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на лого")
    public void navigateToConstructorViaLogo() {
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver); // Создаем объект страницы личного кабинета

        // Клик по логотипу Stellar Burgers
        personalCabinetPage.clickStellarLogo(); // Кликаем на логотип для перехода в конструктор

        // Проверка, что открылась страница конструктора
        ConstructorPage constructorPage = new ConstructorPage(driver); // Создаем объект страницы конструктора
        // Убеждаемся, что кнопка оформления заказа отображается
        assertTrue("Страница конструктора не открылась", constructorPage.isOrderButtonDisplayed());

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
