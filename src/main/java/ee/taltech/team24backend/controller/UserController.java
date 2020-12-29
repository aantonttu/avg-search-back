package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.UserDto;
import ee.taltech.team24backend.dto.authDto.LoginDto;
import ee.taltech.team24backend.dto.authDto.LoginResponse;
import ee.taltech.team24backend.dto.authDto.RegisterDto;
import ee.taltech.team24backend.model.User;
import ee.taltech.team24backend.security.UserSessionHolder;
import ee.taltech.team24backend.service.LoginService;
import ee.taltech.team24backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto){
        userService.saveUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

    @GetMapping("me")
    public Object getMe() {
        return UserSessionHolder.getLoggedInUser();
    }
}
