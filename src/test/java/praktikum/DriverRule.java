package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() throws Throwable {
        initDriverBrowser();
    }

    @Override
    protected void after() {
        String token = Methods.getAccessToken(driver);
        if (token != null) {
            Methods.deleteUser(token);
        }
        driver.quit();
    }

    // Метод для запуска chrome
    public void startUpChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--disable-cache");
        driver = new ChromeDriver(options);
    }

    // Метод для запуска firefox
    public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        // драйвер для браузера
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--incognito", "--disable-cache");
        driver = new FirefoxDriver(options);
    }

    public void startUpYandex() {
        System.setProperty("webdriver.chrome.driver", EnvConfig.YANDEX_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(EnvConfig.YANDEX_BROWSER_PATH);
        options.addArguments("--incognito", "--disable-cache");
        driver = new ChromeDriver(options);
    }

    // Метод для выбора какой из браузеров запустить
    public void initDriverBrowser() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "firefox":
                startUpFirefox();
                break;
            case "yandex":
                startUpYandex();
                break;
            default:
                startUpChrome();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
