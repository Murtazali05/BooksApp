package ru.murtazali.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.murtazali.persistense.entity.RoleEntity;
import ru.murtazali.service.dto.user.UserDTO;
import ru.murtazali.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByEmail(email);
        RoleEntity role = userService.getRole(user.getIdRole());


        return new UserPrincipal(
                user,
                buildUserAuthorities(role)
        );
    }

    private Set<GrantedAuthority> buildUserAuthorities(RoleEntity role) {
        Set<GrantedAuthority> auth = new HashSet<GrantedAuthority>();

//        TODO: add multiple roles!

        auth.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName())));
        return auth;
    }
}
