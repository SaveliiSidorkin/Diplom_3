import api.User;
import api.UserAuth;
import api.UserClient;
import api.UserGenerator;
import fragment.FragmentLoginPage;
import fragment.FragmentMainPage;
import fragment.FragmentProfilePage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {
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
    @DisplayName("Выход из аккаунта")
    public void logoutTest() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickLoginButton();
        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickProfileButton();
        FragmentProfilePage profilePage = new FragmentProfilePage(driver);
        profilePage.clickLogoutButton();
        //Проверяем, что отображается кнопка Войти после логаута
        assertTrue("Кнопка Войти не отоборажается на странице входа",loginPage.isLoginPage());
    }
}
