package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AccountPage {
    private WebDriver driver;

    // Локатор текста "Профиль"
    private static final By profileText = By.cssSelector("a.Account_link_active__2opc9");
    // Локатор кнопки "Выход"
    private static final By logoutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на кнопку \"Выход\"")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Ожидание загрузки страницы личного кабинета")
    public void waitForAccountPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileText));
    }

    @Step("Проверка, что открылась страница с данными профиля")
    public void checkAccountPageAvailable() {
        assertTrue("Не открылась страница с данными профиля", driver.findElement(profileText).isDisplayed());

    }

}