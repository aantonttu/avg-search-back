package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.UserDto;
import ee.taltech.team24backend.dto.authDto.LoginDto;
import ee.taltech.team24backend.dto.authDto.LoginResponse;
import ee.taltech.team24backend.dto.authDto.RegisterDto;
import ee.taltech.team24backend.model.User;
import ee.taltech.team24backend.security.Roles;
import ee.taltech.team24backend.security.UserSessionHolder;
import ee.taltech.team24backend.service.CommentService;
import ee.taltech.team24backend.service.LoginService;
import ee.taltech.team24backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;
    private final CommentService commentService;

    @Secured(Roles.ADMIN)
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        userService.saveUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @GetMapping("me")
    public Object getMe() {
        return UserSessionHolder.getLoggedInUser();
    }

    @Secured(Roles.ADMIN)
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        User dbUser = userService.findById(id);
        String username = dbUser.getUsername();
        commentService.findAll().stream()
                .filter(commentDto -> commentDto.getUserName().equals(username))
                .forEach(commentDto -> commentService.deleteComment(commentDto.getId()));
        userService.deleteUser(id);
    }
}
