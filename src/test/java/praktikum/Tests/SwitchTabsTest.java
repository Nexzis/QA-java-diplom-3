package praktikum.Tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.EnvConfig;
import praktikum.PageObject.ConstructorPage;

public class SwitchTabsTest {
    @Rule
    public DriverRule factory = new DriverRule();

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URI);
    }


    @Test
    @DisplayName("Тест перехода к разделу \"Соусы\" кнопку ")
    @Description("Тест проверяет, что переход к разделу \"Соусы\" прошел успешно")
    public void assertMoveToSauceDoneSuccessfully()  {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //кликаем на кнопку "Соусы"
        constructorPage.clickSauceButton();
        constructorPage.waitSauceLoad();
        constructorPage.checkSauceButtonIsActive();
    }

    @Test
    @DisplayName("Тест перехода к разделу \"Начинки\" кнопку ")
    @Description("Тест проверяет, что переход к разделу \"Начинки\" прошел успешно")
    public void assertMoveToFillingsDoneSuccessfully()  {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //кликаем на кнопку "Начинки"
        constructorPage.clickFillingsButton();
        constructorPage.waitFillingsLoad();
        constructorPage.checkFillingsButtonIsActive();
    }

    @Test
    @DisplayName("Тест перехода к разделу \"Булки>\" кнопку ")
    @Description("Тест проверяет, что переход к разделу \"Булки\" прошел успешно")
    public void assertMoveToBunDoneSuccessfully()  {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        //кликаем на кнопку "Начинки"
        constructorPage.clickFillingsButton();
        constructorPage.waitFillingsLoad();
        //кликаем на кнопку "Булки"
        constructorPage.clickBunButton();
        constructorPage.waitBunLoad();
        constructorPage.checkBunButtonIsActive();
    }

}
