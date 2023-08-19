package diplom3.pageObject;

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
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }
    public void clickButtonRestore() {
        driver.findElement(buttonRestore).click();
    }
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonRestore));
    }
    public void restorePassword(String email) {
        waitLoadPage();
        setFieldEmail(email);
        clickButtonRestore();
    }

}
