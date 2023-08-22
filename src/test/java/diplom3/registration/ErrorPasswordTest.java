package diplom3.registration;
import diplom3.Browsers;
import diplom3.pageObject.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.hamcrest.CoreMatchers.is;
@RunWith(Parameterized.class)
public class ErrorPasswordTest {
    private WebDriver driver;
    private Browsers browsers = new Browsers();
    @Before
    public void setUp() {
        driver = browsers.get();
    }
    private final String name;
    private final String email;
    private final String password;
    public ErrorPasswordTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Parameterized.Parameters
    public static Object[][] dataForErrorPassword() {
        return new Object[][]{
                {"Lamara", "1fcmhgf5ndbs@yandex.ru", "12345"},
                {"Ламара", "1mckmnlvcs8d0jlam@gmail.com", "lama"},
                {"Л", "19289870898765@mail.ru", " "},
                {"ЛияЛияЛияЛияЛияЛия", "1nbv7dndbxlk9hgs@yandex.ru", "7"},
                {"Лия Мария", "1mck7ih7d9sjlam@gmail.com", "лии"}
        };
    }
    @Test
    @Step("Регистрируем пользователя с некорректным паролем")
    @Description("Тест проверяет, что нельзя зарегистрироваться, если пароль < 6 символов")
    @DisplayName("Невозможна регистрация пользователя с некорректным паролем")
    public void PasswordFailedTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerNewUser(name, email, password);
        String result = registrationPage.findTextIncorrectPassword();
        MatcherAssert.assertThat("Появляется сообщение об ошибке в поле Пароль", result, is("Некорректный пароль"));
    }
    @After
    public void teardown() {
        driver.quit();
    }
}

