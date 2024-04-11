package fragment;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.BasePage;

public class FragmentResetPasswordPage extends BasePage {
    private final By enterButton = By.xpath("//a[text()='Войти']");

    public FragmentResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Кликл на кнопку Войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
