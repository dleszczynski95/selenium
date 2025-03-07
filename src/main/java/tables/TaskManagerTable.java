package tables;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

@Data
public class TaskManagerTable {
    private WebElement row;
    private Map<String, Integer> headers;

    @FindBy(css = "[role='cell']")
    public List<WebElement> cells;

    public TaskManagerTable(WebElement row, Map<String, Integer> headers) {
        PageFactory.initElements(row, this);
        this.row = row;
        this.headers = headers;
    }

    public String getName() {
        return cells.get(headers.get("Name")).getText();
    }

    public WebElement getNameElement() {
        return cells.get(headers.get("Name"));
    }

    public String getNetwork() {
        return cells.get(headers.get("Network")).getText();
    }

    public String getCPU() {
        return cells.get(headers.get("CPU")).getText();
    }

    public String getMemory() {
        return cells.get(headers.get("Memory")).getText();
    }

    public String getDisk() {
        return cells.get(headers.get("Disk")).getText();
    }

    @Override
    public String toString() {
        return "\nName: " + getName() + "\nNetwork: " + getNetwork() + "\nCPU: " + getCPU() + "\nMemory: " + getMemory()
                + "\nDisk: " + getDisk();
    }
}
