package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(Listener.class)
public class TestBase {
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;
    protected Config config;

    @BeforeTest
    public void setUp() {
        YamlReader yamlReader = new YamlReader("config.yaml");
        config = yamlReader.getActiveConfig();
        logger.info(config.getConfigLog());
    }

    @BeforeMethod
    public void setDriver() {
        initializeDriver();
        DriverManager.setDriver(driver);
        logger.info("Going to: {}", config.getUrl());
        driver.get(config.getUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
    }

    private void initializeDriver() {
        logger.info("Initializing driver: {}", config.getBrowser());
        switch (config.getBrowser()) {
            case CHROME -> {
                System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                System.setProperty("webdriver.firefox.driver", "src/main/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }
    }
}