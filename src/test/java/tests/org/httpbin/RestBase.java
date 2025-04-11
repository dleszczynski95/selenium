package tests.org.httpbin;

import org.testng.annotations.BeforeTest;
import pages.org.httpbin.RestAssuredBase;

public class RestBase {
    protected RestAssuredBase rest;

    @BeforeTest
    public void setup() {
        rest = new RestAssuredBase();
    }
}
