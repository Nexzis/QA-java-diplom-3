package praktikum;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class EnvConfig {
    // Адрес тестового сайта
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    public static final String API_REGISTER = "/api/auth/register";
    public static final String API_DELETE = "/api/auth/user";
    public static final String API_LOGIN = "/api/auth/login";


    public static final String YANDEX_DRIVER_PATH = "C:/Program Files/Amazon Corretto/Webdrivers/yandexdriver.exe";
    public static final String YANDEX_BROWSER_PATH = "C:/Users/Roman/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";

    /*
    *  Описать эти 2 поля для настройки яндекс браузера в README.md
    * */



    @Step("Передача BASE_URI без токена")
    public static RequestSpecification getSpec() {
        return given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

    @Step("Передача BASE_URI с токеном")
    public static RequestSpecification getSpecWithToken(String token) {
        return given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth().oauth2(token.replace("Bearer ", ""));
    }
}
