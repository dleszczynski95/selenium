package tests.org.httpbin;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.org.httpbin.objects.AuthUser;
import pages.org.httpbin.objects.BearerToken;

public class AuthTest extends RestBase {

    @Test(dataProvider = "authUsers")
    public void checkBasicAuth(String username, String password, int statusCode) {
        AuthUser basicAuth = rest.getBasicAuth(username, password, statusCode);
        if (statusCode == 201) {
            Assertions.assertThat(basicAuth.getUser()).isEqualTo(username);
            Assertions.assertThat(basicAuth.isAuthenticated()).isTrue();
        }
    }

    @Test
    public void checkValidBearerAuth() {
        String bearerToken = "token1123";
        BearerToken token = rest.getBearerToken(bearerToken, 200);
        Assertions.assertThat(token.getToken()).isEqualTo(bearerToken);
        Assertions.assertThat(token.isAuthenticated()).isTrue();
    }

    @Test
    public void checkEmptyBearerAuth() {
        Assertions.assertThat(rest.getBearerToken("", 401)).isNull();
        Assertions.assertThat(rest.getBearerToken("  ", 401)).isNull();
    }

    @DataProvider(name = "authUsers")
    public Object[][] authUsers() {
        return new Object[][]{
                {"a", "a", 200},
                {"a", "b", 401},
                {"b", "b", 401},
                {"b", "a", 401},
        };
    }
}