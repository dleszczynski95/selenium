package pages.com.uitestingplayground;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class SampleAppPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FindBy(css = "button#login")
    private WebElement loginButton;

    @FindBy(css = "input[name='UserName']")
    private WebElement userNameInput;

    @FindBy(css = "input[name='Password']")
    private WebElement passwordInput;

    @FindBy(css = "label#loginstatus")
    private WebElement loginStatus;

    public SampleAppPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlToBe("http://www.uitestingplayground.com/sampleapp"));
        waitForVisibility(loginButton);
        logger.info("User is on Sample App Page");
    }

    @Step("Clicking on Login Button")
    public SampleAppPage clickLogin() {
        click(loginButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(loginStatus, "class", "text-info")));
        logger.info("Clicked on Login Button");
        return this;
    }

    @Step("Clicking on Log out Button")
    public SampleAppPage clickLogOut() {
        click(loginButton);
        wait.until(ExpectedConditions.attributeToBe(loginStatus, "class", "text-info"));
        logger.info("Clicked on Log out Button");
        return this;
    }

    @Step("Filling username")
    public SampleAppPage fillUsername(String username) {
        sendKeys(userNameInput, username);
        logger.info("Filled username");
        return this;
    }

    @Step("Filling password")
    public SampleAppPage fillPassword(String password) {
        sendKeys(passwordInput, password);
        logger.info("Filled password");
        return this;
    }

    @Step("Getting login status")
    public String getLoginStatus() {
        return getText(loginStatus);
    }

    @Step("Getting username")
    public String getUsername() {
        return getTextByJS(userNameInput);
    }

    @Step("Getting password")
    public String getPassword() {
        return getTextByJS(passwordInput);
    }
}