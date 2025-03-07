package tests;

import configuration.TestBase;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.uitestingplayground.SampleAppPage;
import pages.com.uitestingplayground.StartPage;

public class SampleAppTest extends TestBase {
    protected SampleAppPage sampleAppPage;

    @BeforeMethod
    public void goToDynamicTable() {
        sampleAppPage = new StartPage(driver).goToTileObject(StartPage.Tiles.SAMPLE_APP);
        AssertJUnit.assertEquals("User logged out.", sampleAppPage.getLoginStatus());
    }

    @Test
    public void emptyLogin() {
        sampleAppPage.clickLogin();
        AssertJUnit.assertEquals("Invalid username/password", sampleAppPage.getLoginStatus());
    }

    @Test
    public void validLogin() {
        sampleAppPage
                .fillUsername(config.getUsername())
                .fillPassword(config.getPassword())
                .clickLogin();
        AssertJUnit.assertEquals("Welcome, " + config.getUsername() + "!", sampleAppPage.getLoginStatus());
    }

    @Test
    public void validLogOut() {
        sampleAppPage
                .fillUsername(config.getUsername())
                .fillPassword(config.getPassword())
                .clickLogin();
        AssertJUnit.assertEquals("Welcome, " + config.getUsername() + "!", sampleAppPage.getLoginStatus());
        sampleAppPage.clickLogOut();
        AssertJUnit.assertEquals("User logged out.", sampleAppPage.getLoginStatus());
    }
}
