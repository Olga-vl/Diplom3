package api;

import api.pojo.User;
import api.pojo.Credentials;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static api.Specification.getBaseSpec;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class UserServices {

    private final String ROOT = "/auth";

    @Step("Получить токен")
    public String accessToken(ValidatableResponse response) {
        return response
                .extract()
                .path("accessToken");
    }

    @Step("Регистрация")
    public ValidatableResponse register(User user) {
        String REGISTER = ROOT + "/register";
        return getBaseSpec
                .body(user).log().all()
                .when()
                .post(REGISTER)
                .then().log().all();
    }

    @Step("Логин")
    public ValidatableResponse login(Credentials credentials) {
        String LOGIN = ROOT + "/login";
        return getBaseSpec
                .body(credentials).log().all()
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public String getToken(String token) {
        return token.substring(7);
    }
    @Step("Удалить пользователя по токену")
    public void deleteUser(String token) {
        String USER = ROOT + "/user";
        getBaseSpec
                .auth().oauth2(getToken(token))
                .when()
                .delete(USER)
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }
}