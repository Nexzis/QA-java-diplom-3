package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;


public class RegisterPage {
    private WebDriver driver;

    // Локатор поля "Имя"
    private static final By nameField = By.xpath("//label[text()='Имя']/parent::div//input");

    // Локатор поля "Пароль"
    private static final By passwordField = By.xpath("//label[text()='Пароль']/parent::div//input");;

    // Локатор поля "Email"
    private static final By emailField = By.xpath("//label[text()='Email']/parent::div//input");

    // Локатор кнопки "Зарегистрироваться"
    private static final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    // Локатор кнопки "Войти"
    private static final By loginButton = By.cssSelector("a[href='/login']");

    // Локатор текста "Некорректный пароль"
    private static final By errorText = By.className("input__error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка, что текст ошибки появился")
    public void checkErrorTextVisible(WebDriver driver) {
        assertTrue("Ошибка с некорректным паролем не отображается", driver.findElement(RegisterPage.errorText).isDisplayed());

    }

    @Step("Клик на кнопку \"Зарегистрироваться\"")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик на кнопку \"Войти\" на главной странице")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Заполним имя")
    public void setName(String value) {
        driver.findElement(nameField).sendKeys(value);
    }

    @Step("Заполним пароль")
    public void setPassword(String value) {
        driver.findElement(passwordField).sendKeys(value);
    }

    @Step("Заполним email")
    public void setEmail(String value) {
        driver.findElement(emailField).sendKeys(value);
    }
    @Step("Заполним name, password, email и нажмём кнопку \"Зарегистрироваться\"")
    public void registerUser(String name,String password, String email){
        setName(name);
        setPassword(password);
        setEmail(email);
        clickRegisterButton();
    }

}
