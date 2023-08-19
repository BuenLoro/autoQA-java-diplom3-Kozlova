package signIn;

import diplom3.pageObject.ForgetPasswordPage;
import diplom3.pageObject.MainPage;
import diplom3.pageObject.RegistrationPage;
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
import diplom3.user.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;

public class SignInTest {
    private WebDriver driver;
    private CreateUser createUser;
    private String accessToken;
    private CreateAndLoginUser createAndLoginUser = new CreateAndLoginUser();


    @Before
    @Step("Тестовые данные")
    public void setUp() {

        createAndLoginUser = new CreateAndLoginUser();
        createUser = new CreateUser("djk87fc75gds6eu@yandex.ru", "18k876yg", "Liana");
        ValidatableResponse responseCreate = createAndLoginUser.newUser(createUser);
        accessToken = responseCreate.extract().jsonPath().get("accessToken");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

            //Драйвер Yandex
            /* System.setProperty("webdriver.chrome.driver","path/to/yandex/browser");
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            this.driver = new ChromeDriver(service); */

        driver.manage().window().maximize();
    }

    @Test
    @Step("вход по кнопке «Войти в аккаунт» на главной")
    @Description("Тест проверяет вход по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Клик по кнопке «Войти в аккаунт» ведет на страницу авторизации")
    public void clickSignInButtonFromMainPageTest() {
        driver.get("https://stellarburgers.nomoreparties.site");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonSignIn();
        String result = driver.getCurrentUrl();
        MatcherAssert.assertThat("Есть кнопка Войти", result, is("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @Step("вход через кнопку «Личный кабинет»")
    @Description("Тест проверяет вход через кнопку «Личный кабинет»")
    @DisplayName("Клик по кнопке «Личный кабинет» ведет на страницу авторизации")
    public void signInViaButtonPersonalAccount() {
        driver.get("https://stellarburgers.nomoreparties.site");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonPersonalAccount();
        String result = driver.getCurrentUrl();
        MatcherAssert.assertThat("Есть кнопка Войти", result, is("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @Step("вход через кнопку в форме регистрации")
    @Description("Тест проверяет вход через кнопку в форме регистрации")
    @DisplayName("Клик по кнопке «Войти» в форме регистрации ведет на страницу авторизации")
    public void signInViaSignOutFormTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickButtonSignIn();
        String result = driver.getCurrentUrl();
        MatcherAssert.assertThat("Есть кнопка Войти", result, is("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @Step("вход через форму восстановления пароля")
    @Description("Тест проверяет вход через форму восстановления пароля")
    @DisplayName("Клик по кнопке «Войти» в форме восстановления пароля ведет на страницу авторизации")
    public void signInViaForgetPasswordFormTest() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.clickButtonSignIn();
        String result = driver.getCurrentUrl();
        MatcherAssert.assertThat("Есть кнопка Войти", result, is("https://stellarburgers.nomoreparties.site/login"));
    }

    @After
    @Step("Закрыть браузер")
    public void teardown() {
        driver.quit();
    }
}
