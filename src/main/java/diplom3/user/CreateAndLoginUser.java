package diplom3.user;
import diplom3.pageObject.RegistrationPage;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static diplom3.constants.ApiPath.*;
import static io.restassured.RestAssured.given;
public class CreateAndLoginUser {
    public static RequestSpecification getRequest() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site");
    }
    @Step("Создание пользователя для парамметризированного теста")
    public ValidatableResponse createNewUser(RegistrationPage registrationPage) {
        return getRequest()
                .header("Content-type", "application/json")
                .body(registrationPage)
                .when()
                .post(REGISTER_NEW_USER)
                .then();
    }
    @Step("Создание пользователя")
    public ValidatableResponse newUser(CreateUser createUser) {
        return getRequest()
                .header("Content-type", "application/json")
                .body(createUser)
                .when()
                .post(REGISTER_NEW_USER)
                .then();
    }
    @Step("Логин существующего пользователя")
    public ValidatableResponse loginUser(CreateUser loginUser){
        return getRequest()
                .header("Content-type", "application/json")
                .body(loginUser)
                .when()
                .post(LOGIN_USER)
                .then();
    }
    @Step("Выход из аккаунта")
    public ValidatableResponse logoutUser(String refreshToken, CreateUser createUser) {
        return getRequest()
                .header("application/json", refreshToken)
                .body(createUser)
                .when()
                .post(LOGOUT)
                .then();
    }
    @Step("Удаление пользователя")
    public ValidatableResponse userDelete(String accessToken){
        return getRequest()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth" + "/" + accessToken)
                .then();
    }
    public static CreateUser getDataCreatedUser(String email, String password, String name) {
        CreateUser createUser = new CreateUser(email,password, name);
        createUser.setEmail(email);
        createUser.setPassword(password);
        createUser.setName(name);
        return createUser;
    }
}
