import base.BaseSteps;
import base.BaseTest;
import constants.Path;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.RegistrationPage;
import api.User;
import api.UserClient;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class RegistrationTests extends BaseTest {


    private UserClient userClient;
    private User user;

    @Before
    @Override
    public void setUp() {
        relativePath = Path.REGISTER_PATH; // Путь к странице регистрации
        super.setUp();
        user = utils.UserGen.generateRandomUser();
    }

    @Test
    public void testSuccessfulRegistration() {
        // Инициализация страницы
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Действия на странице регистрации
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        //после теста удаляем пользователя
        BaseSteps.deleteUser(user);
    }

    @Test
    public void testShortPasswordError() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Данные для теста

        String shortPassword = "123j!";  // Пароль меньше 6 символов

        // Шаги
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(shortPassword);
        registrationPage.clickRegisterButton();

        // Проверка ошибки
        String errorMessage = registrationPage.getErrorMessage();
        assertEquals("Ошибка не была отображена или неверное сообщение", "Некорректный пароль", errorMessage);
    }

}
