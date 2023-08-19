package diplom3.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private final WebDriver driver;

    private final By buttonExit = By.xpath(".//button[text()='Выход']");
    private final By buttonConstructor = By.xpath(".//nav//a/p[text() = 'Конструктор']");
    private final By logoStellarBurgers = By.xpath(".//nav/div[contains(@class,'AppHeader_header__logo')]");
    private final By email = By.xpath(".//fieldset[1]/div/div/input");
    private final By password = By.xpath(".//fieldset[2]/div/div/input");
    private final By singInMasterButton = By.xpath("//*[@id='root']/div/main/div/form/button");


    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }



    @Step("Проверяем что после успешной регистрации мы попадаем на форму авторизации")
    public String checkSuccess(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(singInMasterButton));
        return driver.findElement(singInMasterButton).getText();
    }

    @Step("Заполняем и отправляем форму авторизации через главную кнопку Войти")
    public void sendTheLoginForm(String setEmail, String setPassword){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(singInMasterButton));
        driver.findElement(email).sendKeys(setEmail);
        driver.findElement(password).sendKeys(setPassword);
        driver.findElement(singInMasterButton).click();
    }

    public void clickButtonExit() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonExit));
        driver.findElement(buttonExit).click();
    }
    public void clickButtonConstructor() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonConstructor));
        driver.findElement(buttonConstructor).click();
    }
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }
    public String findButtonExit() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonExit));
        return driver.findElement(buttonExit).getText();
    }
}

