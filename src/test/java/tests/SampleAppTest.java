package tests;

import configuration.TestBase;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.uitestingplayground.SampleAppPage;
import pages.com.uitestingplayground.StartPage;

public class SampleAppTest extends TestBase {
    protected SampleAppPage sampleAppPage;

    @BeforeMethod
    public void goToDynamicTable() {
        sampleAppPage = new StartPage(driver).goToTileObject(StartPage.Tiles.SAMPLE_APP);
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo(config.getLogoutMessage());
    }

    @Test
    public void emptyLogin() {
        sampleAppPage.clickLogin();
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo(config.getInvalidMessage());
    }

    @Test
    public void validLogin() {
        sampleAppPage
                .fillUsername(config.getUsername())
                .fillPassword(config.getPassword())
                .clickLogin();
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo("Welcome, " + config.getUsername() + "!");
    }

    @Test
    public void validLogOut() {
        sampleAppPage
                .fillUsername(config.getUsername())
                .fillPassword(config.getPassword())
                .clickLogin();
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo("Welcome, " + config.getUsername() + "!");
        sampleAppPage.clickLogOut();
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo(config.getLogoutMessage());
    }

    @Test
    public void wrongPassword() {
        sampleAppPage
                .fillUsername(config.getUsername())
                .fillPassword("wrong")
                .clickLogin();
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo(config.getInvalidMessage());
    }

    @Test
    public void inputShouldNotBeEditableAfterLogin() {
        sampleAppPage
                .fillUsername(config.getUsername())
                .fillPassword(config.getPassword())
                .clickLogin();
        Assertions.assertThat(sampleAppPage.getLoginStatus()).isEqualTo("Welcome, " + config.getUsername() + "!");
        sampleAppPage
                .fillUsername("new")
                .fillPassword("newPass");
        Assertions.assertThat(sampleAppPage.getUsername()).isEqualTo(config.getUsername());
        Assertions.assertThat(sampleAppPage.getPassword()).isEqualTo(config.getPassword());
    }
}