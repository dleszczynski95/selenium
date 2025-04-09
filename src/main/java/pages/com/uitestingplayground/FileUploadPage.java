package pages.com.uitestingplayground;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import java.util.List;

public class FileUploadPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FindBy(css = "#browse")
    private WebElement browseFilesInput;

    @FindBy(css = ".browse-btn")
    private WebElement browseFilesButton;

    @FindBy(css = "success-file")
    private WebElement successFileMessage;

    @FindBy(css = ".file-item")
    private List<WebElement> fileList;

    public FileUploadPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlToBe("http://www.uitestingplayground.com/upload"));
    }

    @Step("Getting file list")
    public List<String> getFileList() {
        return fileList.stream().map(WebElement::getText).toList();
    }

    @Step("Send file {0}")
    public FileUploadPage sendFile(String filePath) {
        browseFilesInput.sendKeys(filePath);
        return this;
    }
}
