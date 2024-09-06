package com.providertechapi.cliente.infra.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public InMemoryUserDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        this.inMemoryUserDetailsManager = new InMemoryUserDetailsManager(Collections.singletonList(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return inMemoryUserDetailsManager.loadUserByUsername(username);
    }
}
