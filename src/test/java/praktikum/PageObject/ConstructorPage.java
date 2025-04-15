package praktikum.PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ConstructorPage {
    private WebDriver driver;

    // Локатор кнопки "Войти в аккаунт"
    private static final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    // Локатор текста "Соберите бургер"
    private static final By createBurgerText = By.xpath("//h1[text()='Соберите бургер']");

    // Локатор кнопки Булки
    private static final By bunButton = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    // Локатор кнопки Соусы
    private static final By sauceButton = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    // Локатор кнопки Начинки
    private static final By fillingsButton = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");


    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на кнопку Войти на главной странице")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    @Step("Ожидание загрузки страницы с конструктором")
    public void waitForConstructorPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerText));
    }

    @Step("Проверка, что страница с конструктором загрузилась")
    public void checkConstructorPageAvailable() {
        assertTrue("Не вернулись на страницу с конструктором", driver.findElement(createBurgerText).isDisplayed());

    }

    @Step("Ожидание загрузки Булки")
    public void waitBunLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(bunButton,"class", "current"));
    }

    @Step("Ожидание загрузки Соусы")
    public void waitSauceLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(sauceButton,"class", "current"));
    }

    @Step("Ожидание загрузки Начинки")
    public void waitFillingsLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(fillingsButton,"class", "current"));
    }

    @Step("Клик на кнопку Булки")
    public void clickBunButton() {
        driver.findElement(bunButton).click();
    }
    @Step("Клик на кнопку Соусы")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }
    @Step("Клик на кнопку Начинки")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Проверка, что кнопка Булки стала активной")
    public void checkBunButtonIsActive() {
        String classAttr = driver.findElement(bunButton).getAttribute("class");
        assertTrue("кнопка Булки не стала активной", classAttr.contains("current"));

    }
    @Step("Проверка, что кнопка Соусы стала активной")
    public void checkSauceButtonIsActive() {
        String classAttr = driver.findElement(sauceButton).getAttribute("class");
        assertTrue("кнопка Соусы не стала активной", classAttr.contains("current"));


    }
    @Step("Проверка, что кнопка Начинки стала активной")
    public void checkFillingsButtonIsActive() {
        String classAttr = driver.findElement(fillingsButton).getAttribute("class");
        assertTrue("кнопка Начинки не стала активной", classAttr.contains("current"));

    }
}
