import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.Pages;

public class BaseTest {
    protected WebDriver driver;

    private WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Browser not supported");
        }
    }

    @Before

    public void setUp() {
        String browser = System.getenv("BROWSER");
        driver = getDriver(browser == null ? "chrome" : browser);
        driver.get(Pages.MAIN_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
