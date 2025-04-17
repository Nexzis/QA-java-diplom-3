package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
    private WebDriver driver;

    // Локатор кнопки "Личный кабинет"
    private static final By accountButton = By.xpath("//p[text()='Личный Кабинет']");
    // Локатор лого "Stellar Burgers"
    private static final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    // Локатор кнопки "Конструктор"
    private static final By constructorButton = By.className("AppHeader_header__link__3D_hX");


    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на кнопку Личный кабинет на главной странице")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Клик на логотип сайта")
    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    @Step("Клик на кнопку Конструктор на главной странице")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

}
