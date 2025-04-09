package pages.com.uitestingplayground;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import tables.TaskManagerTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTablePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FindBy(css = ".container h3")
    private WebElement title;

    @FindBy(css = ".bg-warning")
    private WebElement cpuWarning;

    @FindBy(css = "[role='rowgroup']")
    private List<WebElement> tableRows;

    public DynamicTablePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlToBe("http://www.uitestingplayground.com/dynamictable"));
        waitForVisibility(title);
        waitForEqualsText(title, StartPage.Tiles.DYNAMIC_TABLE.getUiLabel());
        logger.info("User is on Dynamic Table Page");
    }

    public String getCpuWarning() {
        return getText(cpuWarning);
    }

    @Step("Getting task manager list")
    public List<TaskManagerTable> getTaskManagerList() {
        Map<String, Integer> headerMap = getHeaderMap();
        return tableRows.get(1).findElements(By.cssSelector("[role='row'] "))
                .stream()
                .map(r -> new TaskManagerTable(r, headerMap))
                .toList();
    }

    @Step("Getting task manager list by name {0}")

    public TaskManagerTable getTaskManagerByName(String name) {
        return getTaskManagerList()
                .stream()
                .filter(r -> r.getName().equals(name))
                .findFirst().orElseThrow();
    }

    @Step("Getting header map")
    public Map<String, Integer> getHeaderMap() {
        Map<String, Integer> map = new HashMap<>();
        List<WebElement> headers = tableRows.get(0).findElements(By.cssSelector("[role='columnheader'] "));
        for (int i = 0; i < headers.size(); i++) {
            map.put(getText(headers.get(i)), i);
        }

        return map;
    }
}
