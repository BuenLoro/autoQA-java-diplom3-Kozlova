package diplom3.signin;
import diplom3.Browsers;
import diplom3.pageObject.MainPage;
import diplom3.pageObject.PersonalAccountPage;
import diplom3.user.CreateAndLoginUser;
import diplom3.user.CreateUser;
import diplom3.user.LoginUser;
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
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Objects;
import static org.hamcrest.CoreMatchers.is;
public class LogOutTest {
    private WebDriver driver;
    private CreateUser createUser;
    private String accessToken;
    private CreateAndLoginUser createAndLoginUser = new CreateAndLoginUser();
    private Browsers browsers = new Browsers();
    @Before
    @Step("Тестовые данные")
    public void setUp() {
        driver = browsers.get();
    }
    @Test
    @Step("выход по кнопке «Выйти» в личном кабинете")
    @Description("Тест проверяет выход по кнопке «Выйти» в личном кабинете")
    @DisplayName("Клик по кнопке «Выход» разлогинивает пользователя")
    public void clickToLogoutButtonPassedTest() {
        createAndLoginUser = new CreateAndLoginUser();
        createUser = new CreateUser("djk87fc75gds6eu@yandex.ru", "18k876yg", "Liana");
        ValidatableResponse responseCreate = createAndLoginUser.newUser(createUser);
        accessToken = responseCreate.extract().jsonPath().get("accessToken");
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonPersonalAccount();
        personalAccountPage.sendTheLoginForm("djk87fc75gds6eu@yandex.ru", "18k876yg");
        mainPage.clickButtonPersonalAccount();
        personalAccountPage.clickButtonExit();
        String result = personalAccountPage.checkSuccess();
        MatcherAssert.assertThat("Не получилось", result, is("Войти"));
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
