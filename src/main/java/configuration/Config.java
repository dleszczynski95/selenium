package configuration;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.Locale;

@Data
public class Config {
    private String url;
    private Browsers browser;
    private String username;
    private String password;
    private String logoutMessage;
    private String invalidMessage;

    public enum Browsers {
        CHROME, FIREFOX
    }

    public String getConfigLog() {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        String fieldName;
        sb.append("------------------ CONFIG ------------------").append("\n");
        for (Field field : fields) {
            fieldName = field.getName();
            field.setAccessible(true);
            try {
                sb
                        .append("          ")
                        .append(fieldName).append(" : ")
                        .append(fieldName.toLowerCase(Locale.ROOT).contains("pass") ? "####" : field.get(this))
                        .append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("------------------ CONFIG ------------------");

        return sb.toString();
    }
}