package pages.com.uitestingplayground;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import tables.TaskManagerTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTablePage extends BasePage {

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
    }

    public String getCpuWarning() {
        return getText(cpuWarning);
    }

    public List<TaskManagerTable> getTaskManagerList() {
        Map<String, Integer> headerMap = getHeaderMap();
        return tableRows.get(1).findElements(By.cssSelector("[role='row'] "))
                .stream()
                .map(r -> new TaskManagerTable(r, headerMap))
                .toList();
    }

    public TaskManagerTable getTaskManagerByName(String name) {
        return getTaskManagerList()
                .stream()
                .filter(r -> r.getName().equals(name))
                .findFirst().orElseThrow();
    }

    public Map<String, Integer> getHeaderMap() {
        Map<String, Integer> map = new HashMap<>();
        List<WebElement> headers = tableRows.get(0).findElements(By.cssSelector("[role='columnheader'] "));
        for (int i = 0; i < headers.size(); i++) {
            map.put(getText(headers.get(i)), i);
        }

        return map;
    }
}
