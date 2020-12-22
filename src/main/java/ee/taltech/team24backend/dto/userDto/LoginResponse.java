package ee.taltech.team24backend.dto.userDto;

import ee.taltech.team24backend.security.EnumRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String username;
    private String token;
    private EnumRole role;
}
