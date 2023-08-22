package diplom3.profile;
import diplom3.Browsers;
import diplom3.pageObject.MainPage;
import diplom3.pageObject.PersonalAccountPage;
import diplom3.user.CreateAndLoginUser;
import diplom3.user.CreateUser;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
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
public class EnterTheConstructor {
    private WebDriver driver;
    private CreateUser createUser;
    private String accessToken;
    private CreateAndLoginUser createAndLoginUser = new CreateAndLoginUser();
    private Browsers browsers = new Browsers();
    @Before
    public void setUp() {
        createAndLoginUser = new CreateAndLoginUser();
        createUser = new CreateUser("djk87fc75gds6eu@yandex.ru", "18k876yg", "Liana");
        ValidatableResponse responseCreate = createAndLoginUser.newUser(createUser);
        accessToken = responseCreate.extract().jsonPath().get("accessToken");
        ValidatableResponse responseLogin = createAndLoginUser.loginUser(createUser);
        accessToken = responseLogin.extract().jsonPath().get("accessToken");
        driver = browsers.get();
    }
    @Test
    @Step("переход по клику на «Конструктор»")
    @Description("Тест проверяет переход по клику на «Конструктор»")
    @DisplayName("Клик по кнопке «Конструктор» открывает Конструктор")
    public void enterTheConstructorViaProfile() {
        driver.get("https://stellarburgers.nomoreparties.site/account/profile");
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickButtonConstructor();
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.findBunButtonText();
        MatcherAssert.assertThat("кнопка Булки", result, is("Булки"));
    }
    @Test
    @Step("переход по клику на логотип Stellar Burgers")
    @Description("Тест проверяет переход по клику на логотип Stellar Burgers")
    @DisplayName("Клик по лого Stellar Burgers открывает Конструктор")
    public void enterTheConstructorViaLogo() {
        driver.get("https://stellarburgers.nomoreparties.site/account/profile");
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogoStellarBurgers();
        MainPage mainPage = new MainPage(driver);
        String result = mainPage.findBunButtonText();
        MatcherAssert.assertThat("кнопка Булки", result, is("Булки"));
    }
    @After
    @Step("Удаление тестовых данных")
    public void deleteUser() {
        if (!(Objects.equals(accessToken, null)) && !(Objects.equals(accessToken, ""))) {
            createAndLoginUser.userDelete(accessToken);
            System.out.println("DELETED");
        } else {
            System.out.println("NOT DELETED");
        }
        driver.quit();
    }
}
