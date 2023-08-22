package diplom3.profile;
import diplom3.Browsers;
import diplom3.pageObject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Objects;
import static org.hamcrest.CoreMatchers.is;
public class ConstructorTest {
    private WebDriver driver;
    private Browsers browsers = new Browsers();
    @Before
    public void setUp() {
        driver = browsers.get();
    }
    @Test
    @Step("работает переход к разделу Булки")
    @Description("Тест проверяет корректный переход к разделу Булки")
    @DisplayName("Клик по разделу Булки открывает список булочек")
    public void bunPassedTest() {
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.clickFieldBuns();
        MatcherAssert.assertThat("кнопка Булки", result, is("Флюоресцентная булка R2-D3"));
    }
    @Test
    @Step("работает переход к разделу Начинки")
    @Description("Тест проверяет корректный переход к разделу Начинки")
    @DisplayName("Клик по разделу Булки открывает список начинок")
    public void fillingPassedTest() {
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.clickFieldFilling();
        MatcherAssert.assertThat("кнопка Начинки", result, is("Говяжий метеорит (отбивная)"));
    }
    @Test
    @Step("работает переход к разделу Соусы")
    @Description("Тест проверяет корректный переход к разделу Соусы")
    @DisplayName("Клик по разделу Булки открывает список соусов")
    public void saucePassedTest() {
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.clickFieldSauce();
        MatcherAssert.assertThat("кнопка Соусы", result, is("Соус традиционный галактический"));
    }
    @After
    @Step("Удаление тестовых данных")
    public void deleteUser() {
        driver.quit();
    }
}
