package praktikum.PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginPage {
    private WebDriver driver;

    // Локатор кнопки "Зарегистрироваться"
    private static final By registerButton = By.cssSelector("a[href='/register']");

    // Локатор поля "Пароль"
    private  static final By passwordField = By.xpath("//input[@type='password' and @name='Пароль']");

    // Локатор поля "Email"
    private  static final By emailField = By.xpath("//input[@type='text' and @name='name']");

    // Локатор кнопки "Войти"
    private static final By loginButton = By.xpath("//button[text()='Войти']");

    // Локатор кнопки "Восстановить пароль"
    private static final By recoveryButton = By.cssSelector("a[href='/forgot-password']");

    // Локатор текста "Вход"
    private static final By entranceText = By.xpath("//h2[text()='Вход']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Клик на кнопку  \"Зарегистрироваться\"")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик на кнопку  \"Войти\" ")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на кнопку  \"Восстановить пароль\"")
    public void clickRecoveryButton() {
        driver.findElement(recoveryButton).click();
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoginPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Проверка, что страница входа загрузилась")
    public void checkPageAvailable() {
        assertTrue("Не загрузилась страница входа", driver.findElement(entranceText).isDisplayed());

    }

    @Step("Заполним пароль")
    public void setPassword(String value) {
        driver.findElement(passwordField).sendKeys(value);
    }

    @Step("Заполним email")
    public void setEmail(String value) {
        driver.findElement(emailField).sendKeys(value);
    }
    @Step("Заполним password, email и нажмём кнопку \"Войти\"")
    public void loginUser(String password, String email) {
        setPassword(password);
        setEmail(email);
        clickLoginButton();
    }
}