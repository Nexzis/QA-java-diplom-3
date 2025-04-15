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
import praktikum.PageObject.AccountPage;
import praktikum.PageObject.ConstructorPage;
import praktikum.PageObject.HeaderPage;
import praktikum.PageObject.LoginPage;
import praktikum.User;

public class LogoutTest {

    /*
    * Проверь:
    * Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.
    * */

    @Rule
    public DriverRule factory = new DriverRule();

    private User user;
    private WebDriver driver;

    @Before
    public void setUp()  {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URI);

        user = User.random();
        Methods.createUser(user);

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        // Заполним поля данными и нажмём войти
        loginPage.loginUser(user.getPassword(), user.getEmail());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        //ожидаем загрузки страницы
        accountPage.waitForAccountPageLoad();
    }


    @Test
    @DisplayName("Тест из личного кабинета по кнопке \"Выход\" происходит выход из пользователя")
    @Description("Тест проверяет, что после клика по кнопке \"Выход\" происходит выход из пользователя из личного кабинета")
    public void checkLogoutClickOpenLoginPage()  {

        AccountPage accountPage = new AccountPage(driver);
        //кликаем на кнопку "Выход"
        accountPage.clickLogoutButton();

        LoginPage loginPage = new LoginPage(driver);
        //ожидаем загрузки страницы
        loginPage.waitForLoginPageLoad();
        // проверяем, что открылась страница конструктора
        loginPage.checkPageAvailable();

    }

}
