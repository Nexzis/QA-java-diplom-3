package praktikum.Tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.EnvConfig;
import praktikum.Methods;
import praktikum.PageObject.*;
import praktikum.User;

public class EntranceTest {

    /*
    * Проверь:
    * вход по кнопке «Войти в аккаунт» на главной,
    * вход через кнопку «Личный кабинет»,
    * вход через кнопку в форме регистрации,
    * вход через кнопку в форме восстановления пароля.
    * */

    @Rule
    public DriverRule factory = new DriverRule();

    private User user;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URI);

        user = User.random();
        Methods.createUser(user);
    }


    @Test
    @DisplayName("Тест логина через кнопку \"Войти в аккаунт\"")
    @Description("Тест проверяет, что логин пользователя прошел успешно")
    public void assertLoginSuccessfullyByLoginBtn() throws InterruptedException {

        ConstructorPage constructorPage = new ConstructorPage(driver);
        //кликаем на кнопку "Войти в аккаунт"
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());

        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();
        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный кабинет"
        headerPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        //ожидаем загрузки страницы
        accountPage.waitForAccountPageLoad();
        // Проверяем, что открылась страница с профилем
        accountPage.checkAccountPageAvailable();

    }

    @Test
    @DisplayName("Тест логина через кнопку \"Личный Кабинет\"")
    @Description("Тест проверяет, что логин пользователя прошел успешно")
    public void assertLoginSuccessfullyByAccountBtn()  {

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();

        //кликаем на кнопку "Личный кабинет"
        headerPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        //ожидаем загрузки страницы
        accountPage.waitForAccountPageLoad();
        // Проверяем, что открылась страница с профилем
        accountPage.checkAccountPageAvailable();


    }


    @Test
    @DisplayName("Тест логина через кнопку \"Войти\" в форме регистрации")
    @Description("Тест проверяет, что логин пользователя прошел успешно")
    public void assertLoginSuccessfullyFromRegistrationForm()  {

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        //кликаем на кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);
        //кликаем на кнопку "Войти"
        registerPage.clickLoginButton();

        //ожидаем загрузки страницы
        loginPage.waitForLoginPageLoad();
        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();

        //кликаем на кнопку "Личный кабинет"
        headerPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        //ожидаем загрузки страницы
        accountPage.waitForAccountPageLoad();
        // Проверяем, что открылась страница с профилем
        accountPage.checkAccountPageAvailable();


    }

    @Test
    @DisplayName("Тест логина через кнопку \"Войти\" в форме восстановления пароля")
    @Description("Тест проверяет, что логин пользователя прошел успешно")
    public void assertLoginSuccessfullyByPasswordRecoveryForm()  {

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        //кликаем на кнопку "Восстановить пароль"
        loginPage.clickRecoveryButton();

        RecoveryPage recoveryPage = new RecoveryPage(driver);
        //кликаем на кнопку "Войти"
        recoveryPage.clickLoginButton();

        //ожидаем загрузки страницы
        loginPage.waitForLoginPageLoad();
        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();

        //кликаем на кнопку "Личный кабинет"
        headerPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        //ожидаем загрузки страницы
        accountPage.waitForAccountPageLoad();
        // Проверяем, что открылась страница с профилем
        accountPage.checkAccountPageAvailable();


    }


}
