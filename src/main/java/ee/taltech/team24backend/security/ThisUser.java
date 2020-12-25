package ee.taltech.team24backend.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class ThisUser extends User {

    private Long id;
    private EnumRole enumRole;


    public ThisUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, EnumRole enumRole) {
        super(username, password, authorities);
        this.id = id;
        this.enumRole = enumRole;
    }

    public ThisUser(String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                    Long id, EnumRole enumRole) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.enumRole = enumRole;
    }
}
