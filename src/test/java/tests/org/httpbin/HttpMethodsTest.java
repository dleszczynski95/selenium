package tests.org.httpbin;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.org.httpbin.RestAssuredBase;

public class HttpMethodsTest extends RestBase {

    @Test
    public void checkHttpMethods() {
        JsonNode httpMethods = rest.getHttpMethods();

        Assertions
                .assertThat(httpMethods.get("headers").get("Content-Type").asText())
                .isEqualTo(ContentType.JSON.getContentTypeStrings()[0]);
        Assertions.assertThat(httpMethods.get("headers").get("Accept-Encoding").asText()).isEqualTo("gzip,deflate");
        Assertions.assertThat(httpMethods.get("headers").get("Host").asText()).isEqualTo("httpbin.org");
        Assertions.assertThat(httpMethods.get("url").asText()).isEqualTo(RestAssuredBase.URL + "get");
    }
}
