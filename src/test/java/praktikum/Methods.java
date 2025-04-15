package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;


public class Methods {


    @Step("Получение токена авторизации")
    public static String getAccessToken(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String token = (String) js.executeScript("return window.localStorage.getItem('accessToken');");
        System.out.println("Токен: " + token);
        return token;
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        EnvConfig.getSpecWithToken(accessToken)
                .when()
                .delete(EnvConfig.API_DELETE)
                .then().log().status().log().body()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }

    @Step("Создание пользователя")
    public static Response createUser(User uniqueUser) {
        return EnvConfig.getSpec()
                .body(uniqueUser)
                .when()
                .post(EnvConfig.API_REGISTER)
                .then().log().status().log().body()
                .extract().response();
    }


    @Step("Логин пользователя")
    public static Response loginUser(User uniqueUser) {
        return EnvConfig.getSpec()
                .body(Map.of(
                        "email", uniqueUser.getEmail(),
                        "password", uniqueUser.getPassword()
                ))
                .when()
                .post(EnvConfig.API_LOGIN)
                .then().log().status().log().body()
                .extract().response();

    }


}
