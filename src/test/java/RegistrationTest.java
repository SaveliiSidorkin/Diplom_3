import api.User;
import api.UserAuth;
import api.UserClient;
import api.UserGenerator;
import fragment.FragmentLoginPage;
import fragment.FragmentMainPage;
import fragment.FragmentRegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static api.UserGenerator.faker;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;


public class RegistrationTest extends BaseTest {
    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void createTestData() {
        userClient = new UserClient();
        UserGenerator userGenerator = new UserGenerator();
        user = userGenerator.createNewRandomUser();
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickLoginButton();

        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.clickRegistrationButton();
    }

    @After
    public void deleteTestData() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        FragmentRegistrationPage registrationPage = new FragmentRegistrationPage(driver);
        registrationPage.setNameField(user.getName());
        registrationPage.setEmailField(user.getEmail());
        registrationPage.setPasswordField(user.getPassword());
        registrationPage.clickRegistrationButton();
        //проверяем, что пользователь создан и может авторизоваться
        ValidatableResponse login = userClient.userLogin(new UserAuth(user.getEmail(), user.getPassword()));
        accessToken = login.extract().path("accessToken");
        login.statusCode(SC_OK);
    }

    @Test
    @DisplayName("Ошибка при регистрации")
    public void failedRegistration() {
        FragmentRegistrationPage registrationPage = new FragmentRegistrationPage(driver);
        registrationPage.setNameField(user.getName());
        registrationPage.setEmailField(user.getEmail());

        String incorrectPassword = faker.regexify("[a-zA-Z0-9]{5}");
        user.setPassword(incorrectPassword);

        registrationPage.setPasswordField(incorrectPassword);
        registrationPage.clickRegistrationButton();
        //Проверяем текст ошибки
        String actualMessage = registrationPage.getErrorMessage();
        String expectedMessage = "Некорректный пароль";
        assertEquals("Текст об ошибке не отображается",expectedMessage, actualMessage);
    }

}
