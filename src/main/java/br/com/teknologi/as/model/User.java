package br.com.teknologi.as.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true, callSuper = false)
@RequiredArgsConstructor
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
public class User implements UserDetails {

    @NonNull
    @EqualsAndHashCode.Include
    private String email;

    @NonNull
    private String password;

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
