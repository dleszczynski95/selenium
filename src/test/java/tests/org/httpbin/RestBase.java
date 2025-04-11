package tests.org.httpbin;

import configuration.Listener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import pages.org.httpbin.RestAssuredBase;

@Listeners(Listener.class)
public class RestBase {
    protected RestAssuredBase rest;

    @BeforeTest
    public void setup() {
        rest = new RestAssuredBase();
    }
}
