import fragment.FragmentMainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Переход на таб Булки")
    public void checkBunsTab() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickIngredientsTab();
        mainPage.clickBunsTab();
        String actualActiveTab = mainPage.getNameActiveTab();
        //Проверяем, что активный таб - Булки
        assertEquals("Название таба отличается", "Булки",actualActiveTab);
    }

    @Test
    @DisplayName("Переход на таб Соусы")
    public void checkSauceTab() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickSauceTab();
        String actualActiveTab = mainPage.getNameActiveTab();
        //Проверяем, что активный таб - Соусы
        assertEquals("Название таба отличается", "Соусы",actualActiveTab);
    }

    @Test
    @DisplayName("Переход на таб Начинки")
    public void checkIngredientsTab() {
        FragmentMainPage mainPage = new FragmentMainPage(driver);
        mainPage.clickIngredientsTab();
        String actualActiveTab = mainPage.getNameActiveTab();
        //Проверяем, что активный таб - Начинки
        assertEquals("Название таба отличается", "Начинки",actualActiveTab);
    }
}
