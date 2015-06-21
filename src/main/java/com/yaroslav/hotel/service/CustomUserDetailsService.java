package com.yaroslav.hotel.service;

/**
 * Created by PC on 21.06.2015.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yaroslav.hotel.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        com.yaroslav.hotel.entity.User domainUser = userService.getUserByLogin(login);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                domainUser.getLogin(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser)
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(com.yaroslav.hotel.entity.User user) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        for (Role role: user.getRoles()) {
            authList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authList;
    }
}
