package base;

import api.User;
import api.UserClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public abstract class BaseSteps {

    @Step("Получить accessToken")
    public static String getAccessToken(Response response) {
        String bearerToken = response.then()
                .contentType("application/json") // Указываем ожидаемый тип ответа
                .extract()
                .path("accessToken"); // Извлекаем значение accessToken

        // Убираем префикс "Bearer ", если он есть
        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Возвращаем токен без "Bearer "
        }
        return bearerToken; // Если префикса нет, возвращаем токен как есть
    }

    @Step
    public static void deleteUser(User user) {
        // Удаляем пользователя, если он был создан
        UserClient userClient = new UserClient();
        Response responseLogin = userClient.loginUser(user); // Логинимся для получения токена
        String accessToken = BaseSteps.getAccessToken(responseLogin); // Получаем токен доступа
        Response responseDelete = userClient.deleteUser(accessToken); // Отправляем запрос на удаление пользователя
        responseDelete.then().body("message", equalTo("User successfully removed"));
    }
}
