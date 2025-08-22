package ee.ruslan.event_board.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "admin")
public class AdminProperties {
    private String email;
    private String password;
}
