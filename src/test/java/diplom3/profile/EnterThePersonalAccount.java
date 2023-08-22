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
public class EnterThePersonalAccount {
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
    @Step("переход по клику на «Личный кабинет»")
    @Description("Тест проверяет переход по клику на «Личный кабинет» залогиненого пользователя")
    @DisplayName("Клик по кнопке «Личный кабинет» открывает новую страницу")
    public void clickToLogoutButtonPassedTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonPersonalAccount();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        String result = personalAccountPage.findButtonExit();
        MatcherAssert.assertThat("Есть кнопка Выйти", result, is("Выход"));
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

