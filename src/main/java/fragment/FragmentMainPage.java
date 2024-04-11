package fragment;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.BasePage;
import page.Pages;

public class FragmentMainPage extends BasePage {
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructButton = By.xpath("//p[text()='Конструктор']");
    private final By bunsTab = By.xpath("//span[text()='Булки']");

    private final By sauceTab = By.xpath("//span[text()='Соусы']");

    private final By ingredientTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
    private final By activeNavBar = By.xpath("//a[contains(@class, 'AppHeader_header__link_active')]");

    public FragmentMainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка отображения кнопки Оформить заказ")
    public boolean checkOrderButton() {
        wait.until(ExpectedConditions.urlToBe(Pages.MAIN_URL));
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Клик по табу Булки")
    public void clickBunsTab() {
        WebElement element = driver.findElement(bunsTab);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("Клик по табу Соусы")
    public void clickSauceTab() {
        WebElement element = driver.findElement(sauceTab);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("Клик по табу Ингредиенты")
    public void clickIngredientsTab() {
        WebElement element = driver.findElement(ingredientTab);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("Получение названия активного таба")
    public String getNameActiveTab() {
        return driver.findElement(activeTab).getText();
    }
    @Step("")
    public String getNameActiveNavBarButton(){return driver.findElement(activeNavBar).getText();}

    @Step("Клик на кнопку Войти в аккаунт")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickProfileButton() {
        WebElement element = driver.findElement(profileButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructButton() {
        WebElement element = driver.findElement(constructButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
