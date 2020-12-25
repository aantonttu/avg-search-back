package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.userDto.RegisterDto;
import ee.taltech.team24backend.exceptions.UserException;
import ee.taltech.team24backend.model.User;
import ee.taltech.team24backend.repository.UsersRepository;
import ee.taltech.team24backend.security.EnumRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.apache.http.util.TextUtils.isBlank;

@Service
@AllArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(RegisterDto registerDto) {
        if (isBlank(registerDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(registerDto.getPassword())) {
            throw new UserException("missing password");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(EnumRole.USER);
        usersRepository.save(user);
        //email sent out to confirm it, not necessary fot iti0203
    }
}
