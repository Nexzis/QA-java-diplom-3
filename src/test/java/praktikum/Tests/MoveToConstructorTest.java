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

public class MoveToConstructorTest {

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
    @DisplayName("Тест из личного кабинета по кнопке \"Конструктор\" открывается страница конструктора")
    @Description("Тест проверяет, что после клика на \"Конструктор\" из личного кабинета открывается страница конструктора")
    public void checkConstructorClickOpenConstructorPage()  {

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на кнопку "Конструктор"
        headerPage.clickConstructorButton();
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();
        // проверяем, что открылась страница конструктора
        constructorPage.checkConstructorPageAvailable();
    }


    @Test
    @DisplayName("Тест из личного кабинета по логотипу \"Stellar Burgers\" открывается страница конструктора")
    @Description("Тест проверяет, что после клика на логотип \"Stellar Burgers\" из личного кабинета открывается страница конструктора")
    public void checkLogoClickOpenConstructorPage(){

        HeaderPage headerPage = new HeaderPage(driver);
        //кликаем на логотип
        headerPage.clickLogo();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        //ожидаем загрузки страницы
        constructorPage.waitForConstructorPageLoad();
        // проверяем, что открылась страница конструктора
        constructorPage.checkConstructorPageAvailable();


    }

}
