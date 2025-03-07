package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.uitestingplayground.com/");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
