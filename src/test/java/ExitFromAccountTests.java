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

public class ExitFromAccountTests extends BaseTest {

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

        //предварительно логинимся
        openPage(Path.LOGIN_PATH);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.login(user.getEmail(), user.getPassword());
        //происходит перенаправление на страницу конструктора
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void exitFromAccount() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        BaseSteps.waitForVisibility(driver, constructorPage.orderButton);

        constructorPage.clickPersonalCabinetButton();

        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        //ждем пока загрузится личный кабинет
        BaseSteps.waitForVisibility(driver, personalCabinetPage.profileText);

        //нажимаем кнопку выход
        personalCabinetPage.clickExitButton();
        //произошло перенаправление на страницу аутентификации
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        assertTrue("Страница аутентификации не открылась",authenticationPage.isPageOpened());
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
