package registration;

import diplom3.pageObject.RegistrationPage;
import diplom3.user.CreateAndLoginUser;
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
import diplom3.user.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;



import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class RegistrationPassedTest {
    private WebDriver driver;
    private String accessToken;

    private final CreateAndLoginUser createAndLoginUser = new CreateAndLoginUser();

    @Before
    public void setUp() {


        //Драйвер Chrome
        WebDriverManager.chromedriver().setup(); //для запуска в браузере Google Chrome
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

            /*Драйвер Yandex
            System.setProperty("webdriver.chrome.driver","path/to/yandex/browser");
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            this.driver = new ChromeDriver(service);*/
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    private final String name;
    private final String email;
    private final String password;

    public RegistrationPassedTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] dataForSuccessfulRegistration() {
        return new Object[][]{
                {"Lamara", "1fcmhgf5ndbs@yandex.ru", "123456"},
                {"Ламара", "1mckmnlvcs8d0jlam@gmail.com", "lama765ffd5"},
                {"Л", "19289870898765@mail.ru", "hshxcjk8hs-h7"},
                {"ЛияЛияЛияЛияЛияЛия", "1nbv7dndbxlk9hgs@yandex.ru", "ЛияЛияЛияЛияЛия"},
                {"Лия Мария", "1mck7ih7d9sjlam@gmail.com", "jdj ksjs9sjs"}
        };
    }

    @Test
    @Step("Регистрация пользователя")
    @Description("Тест проверяет успешную регистрацию пользователя")
    @DisplayName("Успешная регистрация пользователя")
    public void createNewUserPassedTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerNewUser(name, email, password);
        String result = registrationPage.findTextEnter();
        MatcherAssert.assertThat("Есть кнопка Войти", result, is("Войти"));
    }

    @After
    @Step("Удаление тестовых данных")
    public void deleteUser() {
        CreateUser loginUser = CreateAndLoginUser.getDataCreatedUser(email, password, name);
        accessToken = createAndLoginUser.loginUser(loginUser)
                .extract().jsonPath().get("accessToken");
        if (!(Objects.equals(accessToken, null)) && !(Objects.equals(accessToken, ""))) {
            createAndLoginUser.userDelete(accessToken);
            System.out.println("DELETED");
        } else {
            System.out.println("NOT DELETED");
        }
        driver.quit();
    }
}



