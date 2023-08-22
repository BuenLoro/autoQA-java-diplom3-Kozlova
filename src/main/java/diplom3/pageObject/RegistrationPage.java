package diplom3.pageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class RegistrationPage {
    private final WebDriver driver;
    //Поле Имя
    private final By fieldName = By.xpath(".//fieldset[1]//div/input");
    //Поле Email
    private final By fieldEmail = By.xpath(".//fieldset[2]//div/input");
    //Поле Пароль
    private final By fieldPassword = By.xpath(".//div/input[@name = 'Пароль']");
    //Кнопка "Зарегистрироваться"
    private final By buttonRegister = By.xpath(".//button[text() = 'Зарегистрироваться']");
    //Кнопка(ссылка) Войти
    private final By buttonSignIn = By.xpath(".//p/a[text() = 'Войти']");
    //Ошибка - Некорректный пароль
    private final By incorrectPassword = By.xpath(".//p[text() = 'Некорректный пароль']");
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Взаимодействие (ввод данных) с поле Имя")
    public void setFieldName(String name) {
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).sendKeys(name);
    }
    @Step("Взаимодействие (ввод данных) с поле Почта")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Взаимодействие (ввод данных) с поле Пароль")
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }
    @Step("Клик по кнопке регистрации")
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }
    @Step("Клик по кнопке Вход")
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    @Step("Ожидание загрузки страницы регистации")
    public void waitForLoadingPage() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonRegister));
    }
    @Step("Регистрацичя нового пользователя")
    public void registerNewUser(String name, String email, String password) {
        waitForLoadingPage();
        setFieldName(name);
        setFieldEmail(email);
        setFieldPassword(password);
        clickButtonRegister();
    }
    @Step("Получить текст с кнопки Вход")
    public String findTextEnter() {
        return driver.findElement(buttonSignIn).getText();
    }
    @Step("Получить текст Некорректный пароль")
    public String findTextIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }
}

