package tests;

import configuration.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.uitestingplayground.DynamicTablePage;
import pages.com.uitestingplayground.StartPage;
import tables.TaskManagerTable;

public class DynamicTableTest extends TestBase {
    protected DynamicTablePage dynamicTablePage;

    @BeforeMethod
    public void goToDynamicTable() {
        dynamicTablePage = new StartPage(driver).goToTileObject(StartPage.Tiles.DYNAMIC_TABLE);
    }

    @Test
    public void isChromeCPUWarningDisplayedProperly() {
        String warningPrefix = "Chrome CPU: ";
        String name = "Chrome";
        String cpuWarning = dynamicTablePage.getCpuWarning();
        TaskManagerTable chrome = dynamicTablePage.getTaskManagerByName(name);

        AssertJUnit.assertEquals(warningPrefix + chrome.getCPU(), cpuWarning);

        driver.navigate().refresh();
        dynamicTablePage
                .getWait()
                .until(ExpectedConditions
                        .not(ExpectedConditions.textToBePresentInElement(dynamicTablePage.getTaskManagerByName(name).getNameElement(), cpuWarning)));
        chrome = dynamicTablePage.getTaskManagerByName(name);

        AssertJUnit.assertNotSame(warningPrefix + chrome.getCPU(), cpuWarning);
        AssertJUnit.assertEquals(warningPrefix + chrome.getCPU(), dynamicTablePage.getCpuWarning());
    }
}