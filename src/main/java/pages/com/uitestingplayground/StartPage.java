package pages.com.uitestingplayground;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import java.lang.reflect.Constructor;

public class StartPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public StartPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#overview .col-sm"), 24));
    }

    @SuppressWarnings("unchecked")
    @Step("Go to {0}")
    public <T> T goToTileObject(Tiles tile) {
        logger.info("Going to: {}", tile.getUiLabel());
        click(driver.findElement(By.xpath("//a[text()='" + tile.getUiLabel() + "']")));
        try {
            Constructor<?> constructor = tile.getClazz().getConstructor(WebDriver.class);
            return (T) constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Can't create instance: " + tile.getClazz().getName(), e);
        }
    }

    @AllArgsConstructor
    @Getter
    public enum Tiles {
        DYNAMIC_ID("Dynamic ID", null), CLASS_ATTRIBUTE("Class Attribute", null),
        HIDDEN_LAYERS("Hidden Layers", null), LOAD_DELAY("Load Delay", null),
        AJAX_DATA("AJAX Data", null), CLIENT_SIDE_DELAY("Client Side Delay", null),
        CLICK("Click", null), TEXT_INPUT("Text Input", null),
        SCROLLBARS("Scrollbars", null), DYNAMIC_TABLE("Dynamic Table", DynamicTablePage.class),
        VERIFY_TEXT("Verify Text", null), PROGRESS_BAR("Progress Bar", null),
        VISIBILITY("Visibility", null), SAMPLE_APP("Sample App", SampleAppPage.class),
        MOUSE_OVER("Mouse Over", null), NON_BREAKING_SPACE("Non-Breaking Space", null),
        OVERLAPPED_ELEMENT("Overlapped Element", null), SHADOW_DOM("Shadow DOM", null),
        ALERTS("Alerts", null), FILE_UPLOAD("File Upload", FileUploadPage.class),
        ANIMATED_BUTTON("Animated Button", null), DISABLED_INPUT("Disabled Input", null),
        AUTO_WAIT("Auto Wait", null), RESOURCES("Resources", null);

        private final String uiLabel;
        private final Class<?> clazz;
    }
}