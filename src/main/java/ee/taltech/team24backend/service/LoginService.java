package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.userDto.LoginDto;
import ee.taltech.team24backend.dto.userDto.LoginResponse;
import ee.taltech.team24backend.exceptions.UserException;
import ee.taltech.team24backend.security.JwtTokenProvider;
import ee.taltech.team24backend.security.ThisUser;
import ee.taltech.team24backend.security.ThisUserDetailsService;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static org.apache.http.util.TextUtils.isBlank;


@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginDto loginDto) {
        if (isBlank(loginDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new UserException("missing password");
        }
        //this will validate that the password is correct (without us validating it explicitly)
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        ThisUser thisUser = (ThisUser) authenticate.getPrincipal(); //it is UserDetails and in our case it is MyUser
        String token = jwtTokenProvider.generateToken(thisUser);
        return LoginResponse.builder()
                .username(thisUser.getUsername())
                .token(token)
                .role(thisUser.getEnumRole())
                .build();

    }
}
