package diplom3.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;

    /*Локаторы*/

    //Кнопка "Войти в аккаунт" на главной странице
    private final By buttonSignIn = By.xpath(".//button[text() = 'Войти в аккаунт']");
    //Кнопка "Личный кабинет"
    private final By buttonPersonalAccount = By.xpath(".//a/p[text() = 'Личный Кабинет']");
    //Раздел "булки"
    private final By fieldBuns = By.xpath(".//span[text()='Булки']");
    //Раздел "соусы"
    private final By fieldSauce = By.xpath(".//div/span[text() = 'Соусы']");
    //Раздел "Начинки"
    private final By fieldFilling = By.xpath(".//div/span[text() = 'Начинки']");
    //Выбран раздел "булки"
    private final By selectedFieldBuns = By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[text()='Флюоресцентная булка R2-D3']");
    //Выбран раздел "Соусы"
    private final By selectedFieldSauce = By.xpath(".//a/p[text()='Соус традиционный галактический']");
    //Выбран раздел "Начинки"
    private final By selectedFieldFilling = By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[text()='Говяжий метеорит (отбивная)']");

    private final By checkButtonSignIn = By.xpath(".//p/a[text() = 'Войти']");
    /*Конструктор*/
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickButtonSignIn() {

        driver.findElement(buttonSignIn).click();
    }

    public void clickButtonPersonalAccount() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonPersonalAccount));

        driver.findElement(buttonPersonalAccount).click();
    }
    public String clickFieldBuns() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldSauce));
        driver.findElement(fieldSauce).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldBuns));
        driver.findElement(fieldBuns).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectedFieldBuns));
        return driver.findElement(selectedFieldBuns).getText();
    }
    public String clickFieldSauce() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldSauce));
        driver.findElement(fieldSauce).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectedFieldSauce));
        return driver.findElement(selectedFieldSauce).getText();
    }
    public String clickFieldFilling() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldFilling));
        driver.findElement(fieldFilling).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectedFieldFilling));
        return driver.findElement(selectedFieldFilling).getText();
    }
    public String findBunButtonText() {
        return driver.findElement(fieldBuns).getText();
    }

}
