package praktikum.Tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.EnvConfig;
import praktikum.PageObject.HeaderPage;
import praktikum.PageObject.LoginPage;
import praktikum.PageObject.ConstructorPage;
import praktikum.PageObject.RegisterPage;
import praktikum.User;


public class RegistrationTest {
    public static final String WRONG_PASSWORD = "qwr";

    @Rule
    public DriverRule factory = new DriverRule();

    private User user;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URI);

        user = User.random();
    }

    @Test
    @DisplayName("Тест регистрации через кнопку \"Войти в аккаунт\"")
    @Description("Тест проверяет, что регистрация пользователя прошла успешно")
    public void assertRegistrationSuccessfullyByLoginBtn()  {

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        //кликаем на кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();


        RegisterPage registerPage = new RegisterPage(driver);
        // Заполним поля данными и нажмём на кнопку "Зарегистрироваться"
        registerPage.registerUser(user.getName(),user.getPassword(),user.getEmail());

        //ожидаем загрузки страницы
        loginPage.waitForLoginPageLoad();

        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());

        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();

        // проверяем, что открылась главная страница
        constructorPage.checkConstructorPageAvailable();

    }



    @Test
    @DisplayName("Тест регистрации через кнопку \"Личный Кабинет\"")
    @Description("Тест проверяет, что регистрация пользователя прошла успешно")
    public void assertRegistrationSuccessfullyByAccountBtn() throws InterruptedException {

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();


        LoginPage loginPage = new LoginPage(driver);
        //кликаем на кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);
        // Заполним поля данными и нажмём на кнопку "Зарегистрироваться"
        registerPage.registerUser(user.getName(),user.getPassword(),user.getEmail());

        //ожидаем загрузки страницы
        loginPage.waitForLoginPageLoad();

        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();

        // проверяем, что открылась главная страница
        constructorPage.checkConstructorPageAvailable();

    }

    @Test
    @DisplayName("Тест ошибки при регистрации с паролем менее 6 символов")
    @Description("Тест проверяет, что при регистрации пользователя ошибка для некорректного пароля (< 6 символов)")
    public void assertRegistrationFailedIfPasswordIncorrectLength() throws InterruptedException {

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        //кликаем на кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();


        RegisterPage registerPage = new RegisterPage(driver);
        //заполним пароль неверными данными
        registerPage.setPassword(WRONG_PASSWORD);
        //кликаем на кнопку "Войти" чтобы сбросить фокус
        registerPage.clickLoginButton();
        //Проверяем, что рамка выделена красным
        registerPage.checkErrorTextVisible(driver);

    }

}
