package pages.org.httpbin.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BearerToken {
    @JsonProperty("authenticated")
    private boolean authenticated;

    @JsonProperty("token")
    private String token;

    @Override
    public String toString() {
        return "Authenticated: " + authenticated + "\nToken: " + token;
    }
}
