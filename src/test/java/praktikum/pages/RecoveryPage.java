package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RecoveryPage {
    private WebDriver driver;

    // Локатор кнопки "Войти"
    private static final By loginButton = By.cssSelector("a[href='/login']");

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на кнопку \"Войти\"")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
