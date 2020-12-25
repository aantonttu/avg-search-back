package ee.taltech.team24backend;

import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.model.User;
import ee.taltech.team24backend.repository.UsersRepository;
import ee.taltech.team24backend.security.EnumRole;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsersApplicationInit implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<String> usernames = usersRepository.findAll().stream().map(User::getUsername).collect(Collectors.toList());
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRole(EnumRole.USER);

        if (!usernames.contains(user.getUsername())) {
            usersRepository.save(user);
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(EnumRole.ADMIN);

        if (!usernames.contains(admin.getUsername())) {
            usersRepository.save(admin);
        }
    }

}
