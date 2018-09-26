package ru.murtazali.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.murtazali.service.dto.user.UserDTO;

import java.util.Collection;

@Getter
public class UserPrincipal extends User {
    final private UserDTO userDTO;

    public UserPrincipal(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.userDTO = user;
    }

}

