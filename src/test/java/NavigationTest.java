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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NavigationTest extends BaseTest {
    UserClient userClient = new UserClient();
    private User user;
    private String accessToken;

    @Before
    public void createTestData() {
        userClient = new UserClient();
        UserGenerator userGenerator = new UserGenerator();
        user = userGenerator.createNewRandomUser();
        userClient.userCreate(user);
        ValidatableResponse response = userClient.userLogin(new UserAuth(user.getEmail(), user.getPassword()));
        accessToken = response.extract().path("accessToken");
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickLoginButton();
        FragmentLoginPage loginPage = new FragmentLoginPage(driver);
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickEnterButton();
    }

    @After
    public void deleteTestData() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void accountButtonTest() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickProfileButton();
        FragmentProfilePage profilePage = new FragmentProfilePage(driver);
        String activeNavBarName = mainPage.getNameActiveNavBarButton();
        //Проверяем название активного заголовка
        assertEquals("Неверное название заголовка в навбаре","Личный Кабинет",activeNavBarName);
        //Проверяем наличие кнопки "Выход"
        assertTrue("На главной странице отображается кнопка Оформить заказ",profilePage.checkLogoutButton());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void constructorButtonTest() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickProfileButton();
        mainPage.clickConstructButton();
        String activeNavBarName = mainPage.getNameActiveNavBarButton();
        //Проверяем название активного заголовка
        assertEquals("Неверное название заголовка в навбаре","Конструктор",activeNavBarName);
        //Проверяем наличие кнопки "Оформить заказ"
        assertTrue("На главной странице отображается кнопка Оформить заказ",mainPage.checkOrderButton());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип")
    public void logoButtonTest() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickProfileButton();
        FragmentProfilePage profilePage = new FragmentProfilePage(driver);
        profilePage.clickLogoButton();
        String activeNavBarName = mainPage.getNameActiveNavBarButton();
        //Проверяем название активного заголовка
        assertEquals("Неверное название заголовка в навбаре","Конструктор",activeNavBarName);
        //Проверяем наличие кнопки "Оформить заказ"
        assertTrue("На главной странице отображается кнопка Оформить заказ",mainPage.checkOrderButton());
    }
}
