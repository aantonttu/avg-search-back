package ee.taltech.team24backend.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private int durationMin;

    public int getDurationMs() {
        return durationMin * 60 * 1000;
    }
}
