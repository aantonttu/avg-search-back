package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.dto.UserDto;
import ee.taltech.team24backend.dto.authDto.RegisterDto;
import ee.taltech.team24backend.exceptions.UserException;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.model.User;
import ee.taltech.team24backend.repository.UsersRepository;
import ee.taltech.team24backend.security.EnumRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if (usersRepository.findAllByUsername(registerDto.getUsername()).size() > 0){
            throw new UserException("current user is already registered.");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(EnumRole.USER);
        usersRepository.save(user);
        //email sent out to confirm it, not necessary fot iti0203
    }

    public List<UserDto> findAll() {
        return usersRepository.findAll()
                .stream().map(this::convertUser)
                .collect(Collectors.toList());
    }

    public UserDto convertUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User findById(Long id) throws RuntimeException {
        return usersRepository.findById(id).orElseThrow(UserException::new);
    }

    public void deleteUser(Long id){
        User dbUser = findById(id);
        if (dbUser != null) {
            usersRepository.delete(dbUser);
        }
    }

}
