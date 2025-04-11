package pages.org.httpbin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.org.httpbin.objects.AuthUser;
import pages.org.httpbin.objects.BearerToken;

import static io.restassured.RestAssured.given;

public class RestAssuredBase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String URL = "https://httpbin.org/";

    public AuthUser getBasicAuth(String username, String password, int statusCode) {
        Response response = given()
                .auth().basic(username, password)
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .get(URL + "basic-auth/a/a")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();

        return statusCode == 200 ? response.body().as(AuthUser.class) : null;
    }

    public BearerToken getBearerToken(String token, int statusCode) {
        Response response = given()
                .auth().oauth2(token)
                .get(URL + "bearer")
                .then()
                .statusCode(statusCode)
                .extract().response();

        return statusCode == 200 ? response.body().as(BearerToken.class) : null;
    }

    public JsonNode getHttpMethods() {
        Response response = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .get(URL + "get")
                .then()
                .statusCode(200)
                .extract()
                .response();

        try {
            return new ObjectMapper().readTree(response.getBody().prettyPrint());
        } catch (JsonProcessingException e) {
            logger.error("Can't read response, {}", e.getMessage());
        }

        return null;
    }
}
