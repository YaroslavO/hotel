package com.yaroslav.hotel.service;

/**
 * Created by PC on 21.06.2015.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yaroslav.hotel.dao.UserDao;
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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        com.yaroslav.hotel.entity.User domainUser = userDao.getUserByLogin(login);

        return new User(
                domainUser.getLogin(),
                domainUser.getPassword(),
                getAuthorities(domainUser)
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(com.yaroslav.hotel.entity.User user) {
        List<GrantedAuthority> authList = new ArrayList<>();

        for (Role role: user.getRoles()) {
            authList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authList;
    }
}
