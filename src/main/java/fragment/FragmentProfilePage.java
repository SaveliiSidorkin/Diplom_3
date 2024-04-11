package fragment;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.BasePage;
import page.Pages;

public class FragmentProfilePage extends BasePage {
    private final By logoutButton = By.xpath("//button[@type='button' and text()='Выход']");
    private final By logoButton = By.xpath("//nav/div");
    public FragmentProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке Выход")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }

    @Step("Проверка отображения кнопки Выход")
    public boolean checkLogoutButton() {
        wait.until(ExpectedConditions.urlToBe(Pages.PROFILE_URL));
        return driver.findElement(logoutButton).isDisplayed();
    }

    @Step("Клик на логотип")
    public void clickLogoButton() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.querySelector('div.Modal_modal_overlay__x2ZCr').style.display='none';");
        driver.findElement(logoButton).click();
    }
}
