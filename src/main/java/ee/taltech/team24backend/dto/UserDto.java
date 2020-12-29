package ee.taltech.team24backend.dto;

import ee.taltech.team24backend.security.EnumRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private EnumRole role;
}
