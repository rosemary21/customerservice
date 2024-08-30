package interswitch.assessment.customerservice.auth.filter.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Setter
@Getter
public class UserDetails {
    private String password;
    private boolean enabled;
    private boolean accountNonLocked;
    private String username;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    Collection<? extends GrantedAuthority> authorities;
}
