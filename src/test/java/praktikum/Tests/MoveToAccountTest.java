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
import praktikum.PageObject.HeaderPage;
import praktikum.PageObject.LoginPage;
import praktikum.PageObject.ConstructorPage;
import praktikum.User;

public class MoveToAccountTest {

    /*
    * Проверь:
    * Проверь переход по клику на «Личный кабинет».
    * */

    @Rule
    public DriverRule factory = new DriverRule();

    private User user;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URI);

        //Thread.sleep(1000);
    }


    @Test
    @DisplayName("Тест перехода по кнопке \"Личный кабинет\" открывает форму входа")
    @Description("Тест проверяет, что после клика на \"Личный кабинет\" отображается форма входа")
    public void checkAccountButtonOpensLoginForm()  {

        //ConstructorPage constructorPage = new ConstructorPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Личный Кабинет"
        headerPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        //ожидаем загрузки страницы
        loginPage.waitForLoginPageLoad();
        // проверяем, что открылась страница входа
        loginPage.checkPageAvailable();

    }

    @Test
    @DisplayName("Тест перехода по кнопке \"Личный кабинет\" открывает страницу аккаунта у залогиненного пользователя")
    @Description("Тест проверяет, что после клика на \"Личный кабинет\" отображается страница аккаунта у залогиненного пользователя")
    public void checkAccountButtonOpensAccountForm()  {
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
        // проверяем, что открылась страница профиля
        accountPage.checkAccountPageAvailable();

    }

}
