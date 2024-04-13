package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseApiClient {
    @Step("Создание юзера")
    public ValidatableResponse userCreate(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("Удаление юзера")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }

    @Step("Авторизация юзера")
    public ValidatableResponse userLogin(UserAuth auth) {
        return given()
                .spec(getSpec())
                .body(auth)
                .when()
                .post("/api/auth/login")
                .then();
    }
}
