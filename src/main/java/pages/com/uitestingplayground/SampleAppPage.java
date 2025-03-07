package pages.com.uitestingplayground;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class SampleAppPage extends BasePage {

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
    }

    public SampleAppPage clickLogin() {
        click(loginButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(loginStatus, "class", "text-info")));
        return this;
    }

    public SampleAppPage clickLogOut() {
        click(loginButton);
        wait.until(ExpectedConditions.attributeToBe(loginStatus, "class", "text-info"));
        return this;
    }

    public SampleAppPage fillUsername(String username) {
        sendKeys(userNameInput, username);
        return this;
    }

    public SampleAppPage fillPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }

    public String getLoginStatus() {
        return getText(loginStatus);
    }

    public String getUsername() {
        return getText(userNameInput);
    }

    public String getPassword() {
        return getText(passwordInput);
    }
}
