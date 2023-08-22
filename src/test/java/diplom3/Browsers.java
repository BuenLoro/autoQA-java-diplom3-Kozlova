package diplom3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
public class Browsers {
    private WebDriver driver;
    private ChromeOptions options;
    private String browserName = "chrome";
    public WebDriver get() {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://stellarburgers.nomoreparties.site");
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/olgakozlova/Webdriver/bin/chromedriver");
                options = new ChromeOptions();
                options.setBinary("/Users/olgakozlova/.yandex");
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://stellarburgers.nomoreparties.site");
                break;
            default:
                throw new RuntimeException("Неверное название браузера");
        }
        return driver;
    }
}

