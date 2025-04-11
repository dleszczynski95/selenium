package pages.org.httpbin.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    @JsonProperty("authenticated")
    private boolean authenticated;

    @JsonProperty("user")
    private String user;

    @Override
    public String toString() {
        return "Authenticated: " + authenticated + "\nUser: " + user;
    }
}
