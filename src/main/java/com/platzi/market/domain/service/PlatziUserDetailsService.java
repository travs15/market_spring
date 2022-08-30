package com.platzi.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlatziUserDetailsService implements UserDetailsService {
//    es un usuario meramente para probar sin tener que ir a la BD u o0tro lugar para ver los usuarios
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("alejo", "{noop}platzi", new ArrayList<>());
    }
}
