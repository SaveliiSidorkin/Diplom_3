package fragment;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.BasePage;
import page.Pages;

public class FragmentLoginPage extends BasePage {

    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By enterButton = By.xpath("//button[text()='Войти']");
    private final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By resetPasswordButton = By.xpath("//a[text()='Восстановить пароль']");

    public FragmentLoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод логина")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Клик по кнопке зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void clickResetPasswordButton() {
        driver.findElement(resetPasswordButton).click();
    }

    @Step("Проверка отображения кнопки Войти на странице авторизации")
    public boolean isLoginPage() {
        wait.until(ExpectedConditions.urlToBe(Pages.LOGIN_URL));
        return driver.findElement(enterButton).isDisplayed();
    }
}
