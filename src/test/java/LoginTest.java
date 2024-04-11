import api.User;
import api.UserAuth;
import api.UserClient;
import api.UserGenerator;
import fragment.FragmentLoginPage;
import fragment.FragmentMainPage;
import fragment.FragmentRegistrationPage;
import fragment.FragmentResetPasswordPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    UserClient userClient = new UserClient();

    private User user;
    private String accessToken;

    @Before
    public void createTestData() {
        UserGenerator userGenerator = new UserGenerator();
        user = userGenerator.createNewRandomUser();
        userClient.userCreate(user);
        ValidatableResponse response = userClient.userLogin(new UserAuth(user.getEmail(), user.getPassword()));
        accessToken = response.extract().path("accessToken");
    }

    @After
    public void deleteTestData() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void mainPageAuthButton() {

        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickLoginButton();
        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickEnterButton();
        //проверяем отображение кнопки "Оформить заказ на главной"
        assertTrue("Кнопка Оформить заказ не отображается",mainPage.checkOrderButton());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void profilePageAuthButton() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickProfileButton();
        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickEnterButton();
        //проверяем отображение кнопки "Оформить заказ" на главной
        assertTrue("Кнопка Оформить заказ не отображается",mainPage.checkOrderButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void registrationPageAuthButton() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickLoginButton();
        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.clickRegistrationButton();
        FragmentRegistrationPage registrationPage = new FragmentRegistrationPage(driver);
        registrationPage.clickEnterButton();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickEnterButton();
        //проверяем отображение кнопки "Оформить заказ" на главной
        assertTrue("Кнопка Оформить заказ не отображается",mainPage.checkOrderButton());
    }

    @Test
    @DisplayName("Вод через кнопку в форме восстановления пароля")
    public void resetPasswordPageAuthButton() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickLoginButton();
        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.clickResetPasswordButton();
        FragmentResetPasswordPage resetPasswordPage = new FragmentResetPasswordPage(driver);
        resetPasswordPage.clickEnterButton();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickEnterButton();
        //проверяем отображение кнопки "Оформить заказ" на главной
        assertTrue("Кнопка Оформить заказ не отображается",mainPage.checkOrderButton());
    }
}
