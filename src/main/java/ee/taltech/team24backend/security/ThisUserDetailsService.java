package ee.taltech.team24backend.security;


import ee.taltech.team24backend.model.User;
import ee.taltech.team24backend.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;


@Service
@AllArgsConstructor
public class ThisUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = usersRepository.findAllByUsername(username);
        if (CollectionUtils.isEmpty(users)){
            throw new UsernameNotFoundException(format("username not found: %s", username));
        }
        User user = users.get(0); //this application doesn't protect against duplicate users
        return new ThisUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getId(), user.getRole());
    }


    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return getRoles(user)
                .map(EnumRole::toStringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    private Stream<EnumRole> getRoles(User user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(EnumRole.values());
        }
        return Stream.of(user.getRole());
    }
}
