package ee.taltech.team24backend.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "users")
public class UsersConfig {
    private String userName;
    private String userPassword;
    private String adminName;
    private String adminPassword;
}
