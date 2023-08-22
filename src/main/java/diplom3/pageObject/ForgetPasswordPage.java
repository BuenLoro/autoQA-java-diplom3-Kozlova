package diplom3.pageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ForgetPasswordPage {
    private final WebDriver driver;
    //Поле email
    private final By fieldEmail = By.xpath(".//label[text() = 'Email']");
    //Кнопка "Восстановить"
    private final By buttonRestore = By.xpath(".//button[text() = 'Восстановить']");
    //Кнопка "Войти"
    private final By buttonSignIn = By.xpath(".//div/p/a[text() = 'Войти']");
    public ForgetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Изенить данные в поле Пароль")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Клик на кнопку восстановления пароля")
    public void clickButtonRestore() {
        driver.findElement(buttonRestore).click();
    }
    @Step("Клик на кнопку Вход")
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    @Step("Ожидание загрузки страницы")
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonRestore));
    }
}
