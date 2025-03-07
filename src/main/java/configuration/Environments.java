package configuration;

import lombok.Data;

import java.util.Map;

@Data
public class Environments {
    private Map<String, Config> config;
    private String activeEnvironment;
}