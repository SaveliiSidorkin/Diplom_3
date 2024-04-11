package fragment;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.BasePage;

public class FragmentRegistrationPage extends BasePage {
    private final By nameField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p");
    private final By enterLoginButton = By.xpath("//a[text()='Войти']");

    public FragmentRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterButton() {
        driver.findElement(enterLoginButton).click();
    }

    @Step("Ввод email")
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод имени")
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод пароля")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Сообщение об ошибке")
    public String getErrorMessage() {
        WebElement element = driver.findElement(errorMessage);
        return element.getText();
    }
}
