package ee.taltech.team24backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Team24backendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team24backendApplication.class, args);
	}

}
